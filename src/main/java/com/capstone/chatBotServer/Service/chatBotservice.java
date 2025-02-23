package com.capstone.chatBotServer.Service;

import java.util.List;

import com.capstone.chatBotServer.App.Dto.ChatMessageDto;

public interface chatBotservice {

	String sendToPythonMessage(String message) throws Exception;
	void saveChatMessage(String userId, String userMessage, String AIMessage, String timestemp) throws Exception;
	List<ChatMessageDto> loadChatMessageByUserId(String userId) throws Exception;
	void deleteMessgae(String userId, String timestamp) throws Exception;
}
