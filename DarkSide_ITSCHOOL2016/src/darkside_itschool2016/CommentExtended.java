/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkside_itschool2016;

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
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Comment ");        sb.append(id);
        sb.append(": {user: ");       sb.append(user_id);
        sb.append(", username: ");    sb.append(author_name);       
        sb.append(", idea: ");        sb.append(idea_id);
        sb.append(", timestamp: ");   sb.append(timestamp);
        sb.append(", text: ");        sb.append(text);
        sb.append("}");
        return sb.toString();
    }

}
