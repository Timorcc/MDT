package com.example.demo.mdt.server;

import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.SmallSecretary;
import com.example.demo.background.service.DoctorService;
import com.example.demo.background.service.SmallSecretaryService;
import com.example.demo.mdt.config.MyApplicationContextAware;
import com.example.demo.mdt.entity.Message;
import com.example.demo.mdt.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@ServerEndpoint("/webSocket/chat/{roomName}/{userId}")
@Controller
public class WebSocketServer {

    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    private static final Map<String, String> userNameList = new ConcurrentHashMap();
    private MessageService messageServiceImpl = (MessageService) MyApplicationContextAware.getApplicationContext().getBean("messageServiceImpl");
    private DoctorService doctorServiceImpl = (DoctorService) MyApplicationContextAware.getApplicationContext().getBean("doctorServiceImpl");
    private SmallSecretaryService smallSecretaryServiceImpl = (SmallSecretaryService) MyApplicationContextAware.getApplicationContext().getBean("smallSecretaryServiceImpl");

    @OnOpen
    public void connect(@PathParam("roomName") String roomName, @PathParam("userId") String userId, Session session) throws Exception {
        System.out.println("连接成功");
        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<Session>();
            // 添加用户
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.err.println("userId:" + userId);
        System.out.println("a client has connected!");
        System.out.println("-----------------------");
        List<Message> messageList = messageServiceImpl.findMessageByChatRoomId(Long.valueOf(roomName));

        for (Message message : messageList
        ) {
            String history = message.getSender() + "-" + message.getUsername() + ":" + message.getContent();
            broadcast(roomName, history);
        }

    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, @PathParam("userId") String userId, Session session) {
        rooms.get(roomName).remove(session);
        System.out.println("a client has disconnected!");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName, @PathParam("userId") String userId,
                           String msg, Session session) throws Exception {
        Date data = new Date();
        String username;
        //判断是秘书还是医生
        if (Long.valueOf(userId) < 9000) {
            SmallSecretary s = smallSecretaryServiceImpl.findById(Long.valueOf(userId));
            username = s.getUsername();
        } else {
            Doctor d = doctorServiceImpl.findById(Long.valueOf(userId));
            username = d.getUsername();
        }
        System.out.println("username is" + username);
        boolean re = messageServiceImpl.insertMessage(Long.valueOf(roomName), Long.valueOf(userId), data, msg, username);
        msg = userId + "-" + username + ":" + msg;
        // 接收到信息后进行广播
        broadcast(roomName, msg);
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
            session.getBasicRemote().sendText(msg);
        }
    }
}