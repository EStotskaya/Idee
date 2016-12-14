/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkside_itschool2016;

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
public class JacksonManagerTest {
    
    public JacksonManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of extractComment method, of class JacksonManager.
     */
    @Test
    public void testExtractComment() throws Exception {
        System.out.println("extractComment");
        String json = "{\"user_id\":1,\"idea_id\":4,\"text\":\"This is a text\"}";
        JacksonManager instance = new JacksonManager();
        Comment expResult = null;
        Comment result = instance.extractComment(json);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of extractIdea method, of class JacksonManager.
     */
    @Test
    public void testExtractIdea() throws Exception {
        System.out.println("extractIdea");
        String json = "";
        JacksonManager instance = new JacksonManager();
        Idea expResult = null;
        Idea result = instance.extractIdea(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractCommentExtended method, of class JacksonManager.
     */
    @Test
    public void testExtractCommentExtended() throws Exception {
        System.out.println("extractCommentExtended");
        String json = "";
        JacksonManager instance = new JacksonManager();
        CommentExtended expResult = null;
        CommentExtended result = instance.extractCommentExtended(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractIdeaExtended method, of class JacksonManager.
     */
    @Test
    public void testExtractIdeaExtended() throws Exception {
        System.out.println("extractIdeaExtended");
        String json = "";
        JacksonManager instance = new JacksonManager();
        IdeaExtended expResult = null;
        IdeaExtended result = instance.extractIdeaExtended(json);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertComment method, of class JacksonManager.
     */
    @Test
    public void testInsertComment() throws Exception {
        System.out.println("insertComment");
        Comment c = null;
        JacksonManager instance = new JacksonManager();
        String expResult = "";
        String result = instance.insertComment(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertIdea method, of class JacksonManager.
     */
    @Test
    public void testInsertIdea() throws Exception {
        System.out.println("insertIdea");
        Idea i = null;
        JacksonManager instance = new JacksonManager();
        String expResult = "";
        String result = instance.insertIdea(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
