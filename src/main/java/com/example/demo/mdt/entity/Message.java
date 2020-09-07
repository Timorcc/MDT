package com.example.demo.mdt.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Long id;
    private Long chatroom_id;
    private Long sender;
    private Date send_date;
    private String content;

}
