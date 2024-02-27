package com.boardproject.projectboard.controller;

import com.boardproject.projectboard.dto.Chatmessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    // 메시지 전송
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Chatmessage sendMessage(Chatmessage chatMessage, Principal principal) {
        // 인증된 사용자의 이름을 메시지에 추가
        String username = principal.getName();
        chatMessage.setSender(username);
        chatMessage.setContent(username + ": " + chatMessage.getContent());
        return chatMessage;
    }

    // 사용자 추가 (사용자 입장 메시지 처리)
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Chatmessage addUser(Chatmessage chatMessage, Principal principal) {
        // 인증된 사용자의 이름을 메시지에 추가
        String username = principal.getName();
        chatMessage.setSender(username);
        chatMessage.setContent(username + " 님이 입장하셨습니다.");
        return chatMessage;
    }
}