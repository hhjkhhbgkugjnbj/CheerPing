package com.capstone.chatBotServer;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class ChatBotServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotServerApplication.class, args);
	}

}
