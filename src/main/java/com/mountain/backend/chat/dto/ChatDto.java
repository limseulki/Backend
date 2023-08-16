package com.mountain.backend.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatDto {
    public enum MessageType{
        TEXT,   // 일반 텍스트 메세지
        INVITE, // 채팅방 초대하기 기능
        LEAVE_ROOM, // 방 나가기 기능
        KICK_OUT;   // 강제 퇴장 기능
    }

    private String chatRoomId;  // 채팅방 아이디
    private String senderName;  // 작성자 명
    private String message; // 채팅 내용
    private MessageType type;   // 메세지 타입
    private String invitedUserName; // 초대된 사용자 명
}
