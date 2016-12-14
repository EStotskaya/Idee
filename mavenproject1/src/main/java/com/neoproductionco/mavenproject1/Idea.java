package com.neoproductionco.mavenproject1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Timestamp;

/**
 *
 * @author Neo
 */
public class Idea {
    private final int user_id;
    
    private String link;
    private String text;
    private byte[] file;
    private Timestamp timestamp;

    public Idea(int user_id){
        this.user_id = user_id;
    }
    
    public Idea(int user_id, String link, String text, byte[] file){
        this.user_id = user_id;
        this.link = link;
        this.text = text;
        this.file = file;
    }

    public String getLink() {
        return link;
    }

    public Idea setLink(String link) {
        this.link = link;
        return this;
    }

    public String getText() {
        return text;
    }

    public Idea setText(String text) {
        this.text = text;
        return this;
    }

    public byte[] getFile() {
        return file;
    }

    public Idea setFile(byte[] file) {
        this.file = file;
        return this;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Idea setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
