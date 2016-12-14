/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoproductionco.mavenproject1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neo
 */
public class Main {
    public static void main(String args[]){
        DataManager manager = new DataManager(new Dummy_DBManager(), new JacksonManager());
        String json = "{\"user_id\":1,\"idea_id\":4,\"text\":\"This is a text\"}";
        Comment result = null;
        try {
            result = manager.extractComment(json);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("User = "+result.getUser_id() +"\nIdea = "+result.getIdea_id()+"\nText = "+result.getText());
    }
    
}
