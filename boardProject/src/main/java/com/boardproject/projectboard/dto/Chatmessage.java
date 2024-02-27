package com.boardproject.projectboard.dto;

import lombok.Data;

@Data
public class Chatmessage {

    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    // Getters and Setters
}

