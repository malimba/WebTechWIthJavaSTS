package com.example.musicplayer.ui.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/music")
public class RestfulMusicPlayerController{
    
    //endpoint to serve and play specific song
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

    //endpoint to list songs stored on the server: used with search feature
    @GetMapping("/list")
    public List<Map<String, String>> listSongs(@RequestParam(value="search", required=false) String query){
        File folder = new File("src/main/resources/static/music");
        File[] files = folder.listFiles(); //retrieving all files and sub-folders
        List<Map<String, String>> foundSongs = new ArrayList<>();
        //check if files exists first
        if(files != null){
            for (File file: files){
                //retrieve filename(or path)
                String name = file.getName();
                //check for mp3 files only tho
                if (name.endsWith(".mp3")){
                    if (query == null || name.toLowerCase().contains(query.toLowerCase())){
                        Map<String, String> song = new HashMap<>();
                        song.put("name", name.replace(".mp3", "")); //retrieving just the name without the trailing .mp3
                        song.put("url", "/api/music/play/" + name);
                        foundSongs.add(song);
                    }
                }
            }
        }
        return foundSongs; //returning the found song list
    }
}