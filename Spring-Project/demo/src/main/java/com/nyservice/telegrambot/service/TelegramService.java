package com.nyservice.telegrambot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramService {

    @Value("${telegram.bot.token}")
    private String botToken;

    public String sendMessage(String chatId, String message) {
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        Map<String, String> body = new HashMap<>();
        body.put("chat_id", chatId);
        body.put("text", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Error sending message: " + e.getMessage(), e);
        }
    }
}