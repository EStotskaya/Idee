/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkside_itschool2016;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 *
 * @author Neo
 */
public class JacksonManager implements InternetManager{
    private ObjectMapper mapper = new ObjectMapper();
    
    public JacksonManager(){}
    
    @Override
    public Comment extractComment(String json) throws IOException{
        return mapper.readValue(json, Comment.class);
    }

    @Override
    public Idea extractIdea(String json) throws IOException{
        return mapper.readValue(json, Idea.class);
    }

    @Override
    public CommentExtended extractCommentExtended(String json) throws IOException{
        return mapper.readValue(json, CommentExtended.class);
    }

    @Override
    public IdeaExtended extractIdeaExtended(String json) throws IOException{
        return mapper.readValue(json, IdeaExtended.class);
    }

    @Override
    public String insertComment(Comment c) throws IOException{
        return mapper.writeValueAsString(c);
    }

    @Override
    public String insertIdea(Idea i) throws IOException{
        return mapper.writeValueAsString(i);
    }
    
}
