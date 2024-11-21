package com.capstone.chatBotServer.Service;

import java.util.List;

import com.capstone.chatBotServer.App.Dto.ChatMessage;

public interface chatBotservice {

	public String sendToPythonMessage(String message) throws Exception;
	public void saveChatMessage(String userId, String userMessage, String AIMessage, String timestemp) throws Exception;
	public List<ChatMessage> loadChatMessageByUserId(String userId) throws Exception;
}
