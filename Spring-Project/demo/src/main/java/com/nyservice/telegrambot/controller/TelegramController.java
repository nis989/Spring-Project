package com.nyservice.telegrambot.controller;

import com.nyservice.telegrambot.model.TelegramRequest;
import com.nyservice.telegrambot.service.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TelegramController {

    private static final Logger logger = LoggerFactory.getLogger(TelegramController.class);

    private final TelegramService telegramService;

    public TelegramController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody TelegramRequest request) {
        logger.info("Received request to send message to chatId: {}", request.getChatId());
        try {
            String response = telegramService.sendMessage(request.getChatId(), request.getMessage());
            logger.info("Telegram API response: {}", response);
            return ResponseEntity.ok("Message sent to Telegram!");
        } catch (Exception e) {
            logger.error("Error sending message to Telegram: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error sending message: " + e.getMessage());
        }
    }
}