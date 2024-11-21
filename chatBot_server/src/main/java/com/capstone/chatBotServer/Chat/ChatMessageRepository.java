package com.capstone.chatBotServer.Chat;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.capstone.chatBotServer.App.Dto.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
	List<ChatMessage> findByUserId(String userId);
}
