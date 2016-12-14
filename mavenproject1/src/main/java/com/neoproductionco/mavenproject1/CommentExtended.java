package com.neoproductionco.mavenproject1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Neo
 */
public class CommentExtended extends Comment{
    private int id;
    private String author_name;
    
    public CommentExtended(int id, int user_id, int idea_id, String author) {
        super(user_id, idea_id);
        this.id = id;
        this.author_name = author;
    }
}
