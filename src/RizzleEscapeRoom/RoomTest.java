package RizzleEscapeRoom;

import RizzleEscapeRoom.Room;

// -------------------------------------------------------------------------
/**
 *  Tests Room method
 * 
 *  @author Katie Smith
 *  @version Oct 8, 2026
 */
public class RoomTest extends student.TestCase
{
    private Room room;
    /**
     * array of possible correct answers
     */
    LinkedChain<String> answers = new LinkedChain<String>();
    
    public void setUp()
    {
        answers.addToEnd("18");
        answers.addToEnd("Eighteen");
        room = new Room("How old are you?", answers, 0);
    }
    
    
    
   /**
    * tests getQuestion()
    */
    public void testGetQuestion()
    {
        assertEquals("How old are you?", room.getQuestion());
    }
    
    
    /**
     * tests getAnswers()
     */
    public void testGetAnswers()
    {
        assertEquals(answers, room.getAnswers());
    }
    
    
    /**
     * tests checkAnswer()
     */
    public void testCheckAnswer()
    {
        assertTrue(room.checkAnswer("EIGHTEEN"));
        assertTrue(room.checkAnswer(" eighteen "));
        assertFalse(room.checkAnswer(null));
    }
    
    // ----------------------------------------------------------
    /**
     * tests toString
     */
    public void testToString() {
        assertEquals(room.toString(), room.getQuestion());
    }
}
