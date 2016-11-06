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
public interface InternetManager {
    public Comment extractComment(String json);
    public Idea extractIdea(String json);
    public CommentExtended extractCommentExtended(String json);
    public IdeaExtended extractIdeaExtended(String json);    
    public String insertComment(Comment c);
    public String insertIdea(Idea i);
}
