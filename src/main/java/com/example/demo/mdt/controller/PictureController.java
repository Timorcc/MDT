package com.example.demo.mdt.controller;


import com.example.demo.background.entity.Doctor;
import com.example.demo.background.entity.SmallSecretary;
import com.example.demo.background.service.DoctorService;
import com.example.demo.background.service.SmallSecretaryService;
import com.example.demo.mdt.service.MessageService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Controller
@RequestMapping("mdt/image")
public class PictureController {
    @Value("${image-dir}")
    String imageDir;
    @Autowired
    MessageService messageService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    SmallSecretaryService smallSecretaryService;

    @GetMapping
    public void getPicture(HttpServletResponse response,
                           @RequestParam("name") String fileName) {
        File file = new File(imageDir + fileName);
        System.out.println(fileName);
        InputStream is;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            file = Math.random() > 0.5 ?
                    new File(imageDir + "not_found.jpg") :
                    new File(imageDir + "not_found2.jpg");
            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                return;
            }
        }
        try {
            byte[] out = new byte[(int) file.length()];
            is.read(out);
            is.close();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getOutputStream().write(out);
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/upload")
    public String upLoad(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam("name") String name
    ) {
        File file = new File(imageDir + name);
        return "";
    }

    //    @CrossOrigin
    @PostMapping("/upload_file")
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("type") String type,
                           @RequestParam("fileName") String name,
                           @RequestParam("chatRoomId") String chatRoomId,
                           @RequestParam("userId") String userId,
                           HttpServletResponse response) throws UnknownHostException {
//      首先先拿到uerId对应的名字，
        String username;
        if (Long.valueOf(userId) < 9000) {
            SmallSecretary s = smallSecretaryService.findById(Long.valueOf(userId));
            username = s.getUsername();
        } else {
            Doctor d = doctorService.findById(Long.valueOf(userId));
            username = d.getUsername();
        }


        //拿到文件的类型为formatType
        String[] split = name.split("\\.");
        String formatType = split[split.length - 1];
        //当前时间戳
        long time = System.currentTimeMillis();
        //重新定义图片的名字：聊天室id-发送者id-时间戳.类型
        String formatName = chatRoomId + "-" + userId + "-" + time + "." + formatType;
        //fileName：全路径名
        String fileName = imageDir + formatName;

        try {
            Date date = new Date();
            uploadFile(file, new File(fileName), Long.valueOf(chatRoomId), Long.valueOf(userId), date, fileName,username,"1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = InetAddress.getLocalHost().getHostAddress();
        String res = host + ":" + 9527 + "/image?name=" + name;
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getOutputStream().write(res.getBytes());
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(MultipartFile file, File target, Long chatRoomId, Long userId, Date date,String fileName, String username, String type) throws IOException {
        String fullName = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        byte[] bytes = file.getBytes();
        OutputStream os = new FileOutputStream(target);
        os.write(bytes);
        os.close();
        messageService.insertMessage(chatRoomId,userId,date,fileName,username,type);

    }

    public static String getUserRealIP(HttpServletRequest request) throws UnknownHostException {
        String ip = "";
        // 有的user可能使用代理，为处理用户使用代理的情况，使用x-forwarded-for
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        if ("127.0.0.1".equals(ip)) {
            // 获取本机真正的ip地址
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        return ip;
    }
}
