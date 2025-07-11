package com.example.musicplayer.ui.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/music")
public class RestfulMusicPlayerController{
    
    @GetMapping(value="/play/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> playSong(@PathVariable String filename) throws IOException{
        Resource file = new ClassPathResource("static/music/" + filename);
        if(!file.exists()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(file);
    }
}