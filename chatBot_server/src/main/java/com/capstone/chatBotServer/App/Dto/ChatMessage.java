package com.capstone.chatBotServer.App.Dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "userRecord") // 실제 몽고 DB 컬렉션 이름
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    @Id
    private String id;
    
    @Field("user_id")
    private String userId;
    
    @Field("user_message")
    private String userMessage;
    
    @Field("ai_message")
    private String AIMessage;
    
    @Field("timestamp")
    private String timestamp;
    
    public ChatMessage(String userId, String userMessage, String AIMessage, String timestamp) {
        this.userId = userId;
        this.userMessage = userMessage;
        this.AIMessage = AIMessage;
        this.timestamp = timestamp;
    }
}
