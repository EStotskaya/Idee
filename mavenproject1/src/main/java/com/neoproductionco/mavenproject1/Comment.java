package com.neoproductionco.mavenproject1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neo
 */
public class Comment {
    private int user_id; //finals
    private int idea_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIdea_id() {
        return idea_id;
    }

    public void setIdea_id(int idea_id) {
        this.idea_id = idea_id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    private String text;
    private Timestamp timestamp;
    private List<Tag> tags;
    
    public Comment(){
        this.user_id = user_id;
        this.idea_id = idea_id;
    }
    
    public Comment(int user_id, int idea_id){
        this.user_id = user_id;
        this.idea_id = idea_id;
    }
    
    public Comment(int user_id, int idea_id, String s){
        this.user_id = user_id;
        this.idea_id = idea_id;
        this.text = s;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}