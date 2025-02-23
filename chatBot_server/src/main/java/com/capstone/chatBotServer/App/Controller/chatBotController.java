package com.capstone.chatBotServer.App.Controller;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.chatBotServer.App.Dto.CMRespDto;
import com.capstone.chatBotServer.App.Dto.ChatMessageDto;
import com.capstone.chatBotServer.Domain.Chat;
import com.capstone.chatBotServer.Service.ChatBotserviceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/c1/chatBot")
public class chatBotController {

	private final ChatBotserviceImpl chatBotserviceImpl;

	// 1. 플러터에서 메세지 받기
	// 2. 스프링에서 메세지 가공 후 전송
	// 3. 파이썬 API 호출
	@PostMapping("/send")
	public ResponseEntity<?> sendMessage(@RequestBody Chat chat){
		boolean status = true;
		
		try {
			//System.out.println("userMessage: " + chat.getMessage());
			String message = "{\r\n"
					+ "    \"message\": \""+ chat.getMessage() +"\"\r\n"
					+ "}\r\n"
					+ "";
			String AIMessage = chatBotserviceImpl.sendToPythonMessage(message);
			try {
				LocalDateTime now = LocalDateTime.now();
				
				// 포맷
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		        String formattedTimestamp = now.format(formatter);
		        
				chatBotserviceImpl.saveChatMessage(chat.getUserId(), chat.getMessage(), AIMessage, formattedTimestamp);
				// System.out.println("데이터 저장 성공");
			}catch (Exception e) {
				status = false;
				return ResponseEntity.ok().body(new CMRespDto<>(1, "데이터 저장 실패", status));
			}
			// 보내기 전에 인코딩
			String encodeMessage = URLEncoder.encode(AIMessage);
			// 해당 코드에서 MongoDB에 질문과 대답 정리
			// 질문과 대답 스키마를 공백으로 구분
			
			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 성공",encodeMessage));
		} catch (Exception e) {
			status = false;
			return ResponseEntity.ok().body(new CMRespDto<>(-1,"메세지 전송 실패",status));
		}

	}

	@GetMapping("/load")
	public ResponseEntity<?> loadMessage(@RequestParam("userId") String userId) {
		boolean status = true;
		
		try {
			List<ChatMessageDto> chatMessage = chatBotserviceImpl.loadChatMessageByUserId(userId);
			// System.out.println(userId);
			// System.out.println(chatMessage);
			return ResponseEntity.ok().body(new CMRespDto<>(1,"대화 기록 불러오기 성공",chatMessage));
		} catch (Exception e) {
			status = false;
			return ResponseEntity.ok().body(new CMRespDto<>(-1,"대화 기록 불러오기 실패",status));
		}

	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteMessage(@RequestBody Chat chat){
		boolean status = true;
		try {
			chatBotserviceImpl.deleteMessgae(chat.getUserId(), chat.getTimeStamp());
			return ResponseEntity.ok().body(new CMRespDto<>(1, "대화 기록 삭제 완료", status));
		}catch(Exception e) {
			status = false;
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "대화 기록 삭제 실패", status));
		}
	}
}
