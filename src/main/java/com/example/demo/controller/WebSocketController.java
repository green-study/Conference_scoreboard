package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;


import java.util.List;

@Controller
public class WebSocketController {

    private final ScoreMapper scoreMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketController(ScoreMapper scoreMapper, SimpMessagingTemplate messagingTemplate) {
        this.scoreMapper = scoreMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/admin-message")
    @SendTo("/topic/message-updates")
    public MessageDto handleMessage(MessageDto message) {
        int score = scoreMapper.getLatestScore();
        if (isTop3(score)) {
            playMp3Sound("top3");
        } else if (isTop10(score)) {
            playMp3Sound("top10");
        } else {
            playMp3Sound("success");
        }

        return message;
    }

    private boolean isTop3(int score) {
        List<Integer> top3Scores = scoreMapper.getTop3ScoresFromDB();

        return top3Scores.contains(score);
    }

    private boolean isTop10(int score) {
        List<Integer> top10Scores = scoreMapper.getTop10ScoresFromDB();

        return top10Scores.contains(score);
    }

    private void playMp3Sound(String music) {
        try {
            Resource resource = new ClassPathResource("static/sound/" + music + ".mp3");
            InputStream inputStream = resource.getInputStream();
            AdvancedPlayer player = new AdvancedPlayer(inputStream);

            player.play();

        } catch (IOException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}