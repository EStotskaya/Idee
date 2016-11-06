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
class IdeaExtended extends Idea{
    private final int id;
    private String author_name;
    private int rating = -1;

    public IdeaExtended(int id, int user_id, String author) {
        super(user_id);
        this.id = id;
        this.author_name = author;
    }
    
}
