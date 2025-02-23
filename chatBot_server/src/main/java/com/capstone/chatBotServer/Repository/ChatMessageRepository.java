package com.capstone.chatBotServer.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.capstone.chatBotServer.App.Dto.ChatMessageDto;

public interface ChatMessageRepository extends MongoRepository<ChatMessageDto, String> {
	List<ChatMessageDto> findByUserId(String userId);
}
