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
public interface DBManager {
    public CommentExtended readComment();
    public IdeaExtended readIdea();
    public void writeComment(Comment c);
    public void writeIdea(Idea i);
    public void updateComment(Comment c, int id);
    public void updateIdea(Idea i, int id);   
}
