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
public class DarkSide_ITSCHOOL2016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //DataManager manager = new DataManager(new MySQL_DBManager(), new JacksonManager());
        MySQL_DBManager bManager = new MySQL_DBManager();
        MySQL_DBManager.main(null);
    }
    
}
