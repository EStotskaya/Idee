/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkside_itschool2016;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Neo
 */
public class DataManagerTest {
    
    public DataManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("Set Up\n");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readComment method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testReadComment() {
        System.out.println("readComment");
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        CommentExtended expResult = null;
        CommentExtended result = instance.readComment(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of readIdea method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testReadIdea() {
        System.out.println("readIdea");
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        IdeaExtended expResult = null;
        IdeaExtended result = instance.readIdea(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of writeComment method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testWriteComment() {
        System.out.println("writeComment");
        Comment c = null;
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        instance.writeComment(c);
    }

    /**
     * Test of writeIdea method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testWriteIdea() {
        System.out.println("writeIdea");
        Idea i = null;
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        instance.writeIdea(i);
    }

    /**
     * Test of updateComment method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testUpdateComment() {
        System.out.println("updateComment");
        Comment c = null;
        int id = 0;
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        instance.updateComment(c, id);
    }

    /**
     * Test of updateIdea method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testUpdateIdea() {
        System.out.println("updateIdea");
        Idea i = null;
        int id = 0;
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        instance.updateIdea(i, id);
    }

    /**
     * Test of extractComment method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testExtractComment() {
        System.out.println("extractComment");
        String json = "";
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        Comment expResult = null;
        Comment result = null;
        try {
            result = instance.extractComment(json);
        } catch (IOException ex) {
            Logger.getLogger(DataManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of extractIdea method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testExtractIdea() {
        System.out.println("extractIdea");
        String json = "";
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        Idea expResult = null;
        Idea result = null;
        try {
            result = instance.extractIdea(json);
        } catch (IOException ex) {
            Logger.getLogger(DataManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of extractCommentExtended method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testExtractCommentExtended() {
        System.out.println("extractCommentExtended");
        String json = "";
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        CommentExtended expResult = null;
        CommentExtended result = null;
        try {
            result = instance.extractCommentExtended(json);
        } catch (IOException ex) {
            Logger.getLogger(DataManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of extractIdeaExtended method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testExtractIdeaExtended() {
        System.out.println("extractIdeaExtended");
        String json = "";
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        IdeaExtended expResult = null;
        IdeaExtended result = null;
        try {
            result = instance.extractIdeaExtended(json);
        } catch (IOException ex) {
            Logger.getLogger(DataManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of insertComment method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testInsertComment() {
        System.out.println("insertComment");
        Comment c = null;
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        String expResult = "";
        String result = null;
        try {
            result = instance.insertComment(c);
        } catch (IOException ex) {
            Logger.getLogger(DataManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of insertIdea method, of class DataManager.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testInsertIdea() {
        System.out.println("insertIdea");
        Idea i = null;
        DataManager instance = new DataManager(new Dummy_DBManager(), new Dummy_InternetManager());
        String expResult = "";
        String result = null;
        try{
            result = instance.insertIdea(i);
        }catch(IOException exception){
            
        }
        assertEquals(expResult, result);
    }
    
}
