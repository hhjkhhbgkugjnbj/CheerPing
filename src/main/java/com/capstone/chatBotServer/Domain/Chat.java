package com.capstone.chatBotServer.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
	// 실제 입력받은 값
	private String userId;
	private String Message;
	private String timeStamp;
}
