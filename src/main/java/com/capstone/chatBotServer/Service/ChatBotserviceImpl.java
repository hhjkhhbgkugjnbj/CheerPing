package com.capstone.chatBotServer.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.capstone.chatBotServer.App.Dto.ChatMessageDto;
import com.capstone.chatBotServer.Repository.ChatMessageRepository;
import com.mongodb.MongoWriteException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatBotserviceImpl implements chatBotservice {

	private final ChatMessageRepository chatMessageRepository;
	private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ChatBotserviceImpl.class);
	
	@Override
	public String sendToPythonMessage(String message) throws Exception {
		//System.out.println("message : " + message);
		// 메세지를 받음
		// 파이썬 API 호출
		//URL url = new URL("https://f541-128-2-204-6.ngrok-free.app/chat");
		URL url = new URL("http://localhost:5001/chat");
		//URL url = new URL("http://128.2.204.6:8077/chat");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = message.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		String jsonResponse = response.toString();	
		JSONObject jsonObject = new JSONObject(jsonResponse);
		String value = jsonObject.getString("data");
		
		// System.out.println(value);
			return value;
		}
	}

	@Override
	public void saveChatMessage(String userId, String userMessage, String AIMessage, String timestemp) throws Exception {
		ChatMessageDto chatMessage = new ChatMessageDto(userId, userMessage, AIMessage, timestemp);
	    
        logger.debug("Created chat message object: {}", chatMessage);
        
        try {
            // MongoDB 연결 상태 및 데이터베이스 정보 확인
            Document dbStats = mongoTemplate.getDb().runCommand(new Document("dbStats", 1));
            logger.info("Connected to database: {}", mongoTemplate.getDb().getName());
            logger.debug("Database stats: {}", dbStats.toJson());

            // 저장 시도
            ChatMessageDto savedMessage = mongoTemplate.save(chatMessage, "userRecord");
            logger.info("Message saved successfully with ID: {}", savedMessage.getId());
            
            // 저장된 데이터 확인
            ChatMessageDto retrievedMessage = mongoTemplate.findById(savedMessage.getId(), ChatMessageDto.class, "userRecord");
            if (retrievedMessage != null) {
                logger.info("Successfully verified saved message: {}", retrievedMessage);
            } else {
                logger.error("Failed to retrieve saved message!");
            }
            
        } catch (MongoWriteException e) {
            logger.error("MongoDB write error: {}", e.getMessage());
            throw new Exception("데이터베이스에 저장하는 중 오류 발생: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("General error while saving: {}", e.getMessage());
            throw new Exception("데이터 저장 중 오류 발생: " + e.getMessage(), e);
        }
	}

	@Override
	public List<ChatMessageDto> loadChatMessageByUserId(String userId) throws Exception {
		// System.out.println(chatMessageRepository.findByUserId(userId));
		return chatMessageRepository.findByUserId(userId);
	}

	@Override
	public void deleteMessgae(String userId, String timestamp) throws Exception {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(userId).and("timestamp").is(timestamp));
            
            mongoTemplate.remove(query, ChatMessageDto.class, "userRecord");
            
            logger.info("Message deleted successfully for userId: {} at timestamp: {}", userId, timestamp);
        } catch (Exception e) {
            logger.error("Error while deleting message: {}", e.getMessage());
            throw new Exception("메시지 삭제 중 오류 발생: " + e.getMessage(), e);
        }
	}
}
