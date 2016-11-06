/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkside_itschool2016;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neo
 */
public class Comment {
    private final int user_id;
    private final int idea_id;
    
    private String text;
    private Timestamp timestamp;
    private List<Tag> tags;
    
    public Comment(int user_id, int idea_id){
        this.user_id = user_id;
        this.idea_id = idea_id;
    }

    public void setTags(ArrayList<Tag> tags){
        this.tags = new ArrayList<Tag>(tags);
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