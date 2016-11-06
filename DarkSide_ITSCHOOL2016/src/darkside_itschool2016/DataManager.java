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
public class DataManager implements DBManager, InternetManager{
    private DBManager database;
    private InternetManager internet;
    
    public DataManager(DBManager dbmanager, InternetManager imanager){
        database = dbmanager;
        internet = imanager;
    }
    
    public void setDBManager(DBManager manager){
        database = manager;
    }

    public void setInternetManager(InternetManager manager){
        internet = manager;
    }

    @Override
    public CommentExtended readComment() {
        return database.readComment();
    }

    @Override
    public IdeaExtended readIdea() {
        return database.readIdea();
    }

    @Override
    public void writeComment(Comment c) {
        database.writeComment(c);
    }

    @Override
    public void writeIdea(Idea i) {
        database.writeIdea(i);
    }

    @Override
    public void updateComment(Comment c, int id) {
        database.updateComment(c, id);
    }

    @Override
    public void updateIdea(Idea i, int id) {
        database.updateIdea(i, id);
    }

    @Override
    public Comment extractComment(String json) {
        return internet.extractComment(json);
    }

    @Override
    public Idea extractIdea(String json) {
        return internet.extractIdea(json);
    }

    @Override
    public CommentExtended extractCommentExtended(String json) {
        return internet.extractCommentExtended(json);
    }

    @Override
    public IdeaExtended extractIdeaExtended(String json) {
        return internet.extractIdeaExtended(json);
    }

    @Override
    public String insertComment(Comment c) {
        return internet.insertComment(c);
    }

    @Override
    public String insertIdea(Idea i) {
        return internet.insertIdea(i);
    }

}
