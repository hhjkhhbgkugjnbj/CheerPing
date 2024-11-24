package com.capstone.chatBotServer.App.Controller;

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
import com.capstone.chatBotServer.App.Dto.ChatMessage;
import com.capstone.chatBotServer.Chat.Chat;
import com.capstone.chatBotServer.Service.ChatBotserviceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/c1/chatBot")
public class chatBotController {

	private final ChatBotserviceImpl chatBotserviceImpl;
	// 플러터에서 메세지 받기 확인
	// 스프링에서 메세지 가공 후 전송
	// 파이썬에게 API 호출
	@PostMapping("/send")
	public ResponseEntity<?> sendMessage(@RequestBody Chat chat){
		boolean status = true;
		
		try {
			System.out.println("userMessage: " + chat.getMessage());
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
				System.out.println("데이터 저장 성공");
			}catch (Exception e) {
				status = false;
				return ResponseEntity.ok().body(new CMRespDto<>(1, "데이터 저장 실패", status));
			}
			// 보내기 전에 인코딩
			// String encodeMessage = URLEncoder.encode(AIMessage);
			// 해당 코드에서 MongoDB에 질문과 대답 정리
			// 질문과 대답 스키마를 공백으로 구분
			
			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 성공",AIMessage));
		} catch (Exception e) {
			status = false;
			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 실패",status));
		}

	}

//	@PostMapping("/send")
//	public ResponseEntity<?> sendMessage(@RequestBody String message){
//		boolean status = true;
//		
//		try {
//			System.out.println("message : " + message);
//			String AIMessage = chatBotserviceImpl.sendToPythonMessage(message);
//			try {
//				LocalDateTime now = LocalDateTime.now();
//				
//				// 포맷
//		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		        String formattedTimestamp = now.format(formatter);
//		        
//				//chatBotserviceImpl.saveChatMessage(chat.getUserId(), chat.getMessage(), AIMessage, formattedTimestamp);
//				System.out.println("데이터 저장 성공");
//			}catch (Exception e) {
//				status = false;
//				return ResponseEntity.ok().body(new CMRespDto<>(1, "데이터 저장 실패", status));
//			}
//			// 보내기 전에 인코딩
//			// String encodeMessage = URLEncoder.encode(AIMessage);
//			// 해당 코드에서 MongoDB에 질문과 대답 정리
//			// 질문과 대답 스키마를 공백으로 구분
//			
//			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 성공",AIMessage));
//		} catch (Exception e) {
//			status = false;
//			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 실패",status));
//		}
//
//	}

	
	// 데이터베이스에서 해당 기기의 ID를 구분하여 대화 기록을 전부 Load
	// @RequestParam은 api 경로에 x
	// @Pathvariable는 api 경로에 작성
	@GetMapping("/load")
	public ResponseEntity<?> loadMessage(@RequestParam("userId") String userId) {
		boolean status = true;
		
		try {
			List<ChatMessage> chatMessage = chatBotserviceImpl.loadChatMessageByUserId(userId);
			System.out.println(userId);
			System.out.println(chatMessage);
			return ResponseEntity.ok().body(new CMRespDto<>(1,"대화 기록 불러오기 성공",chatMessage));
		} catch (Exception e) {
			status = false;
			return ResponseEntity.ok().body(new CMRespDto<>(1,"대화 기록 불러오기 실패",status));
		}

	}
	
//	@PostMapping("/send")
//	public ResponseEntity<?> sendMessage(@RequestBody String userMessage){
//		boolean status = true;
//		
//		try {
//			System.out.println("userMessage: " + userMessage);
//			String AIMessage = chatBotserviceImpl.sendToPythonMessage(userMessage);
//			// 보내기 전에 인코딩
//			// String encodeMessage = URLEncoder.encode(AIMessage);
//			// 해당 코드에서 MongoDB에 질문과 대답 정리
//			// 질문과 대답 스키마를 공백으로 구분
//			
//			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 성공",AIMessage));
//		} catch (Exception e) {
//			status = false;
//			return ResponseEntity.ok().body(new CMRespDto<>(1,"메세지 전송 실패",status));
//		}
//
//	}
	
//	@PostMapping("/save")
//	public ResponseEntity<?> saveRecord(@RequestBody ChatMessage chatMessage){
//		boolean status = true;
//		System.out.println("controller :" + chatMessage);
//		try {
//			
//			chatBotserviceImpl.saveChatMessage(chatMessage.getUser_id(), chatMessage.getUser_message(), chatMessage.getAI_message(), chatMessage.getTimestemp());
//			
//			return ResponseEntity.ok().body(new CMRespDto<>(1, "데이터 저장 성공", status));
//		}catch (Exception e) {
//			status = false;
//			return ResponseEntity.ok().body(new CMRespDto<>(1, "데이터 저장 실패", status));
//		}
//	}
}
