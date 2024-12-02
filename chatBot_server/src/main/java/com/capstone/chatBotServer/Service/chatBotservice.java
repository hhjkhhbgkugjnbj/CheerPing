package com.capstone.chatBotServer.Service;

import java.util.List;

import com.capstone.chatBotServer.App.Dto.ChatMessageDto;

public interface chatBotservice {

	public String sendToPythonMessage(String message) throws Exception;
	public void saveChatMessage(String userId, String userMessage, String AIMessage, String timestemp) throws Exception;
	public List<ChatMessageDto> loadChatMessageByUserId(String userId) throws Exception;
	public void deleteMessgae(String userId, String timestamp) throws Exception;
}
