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
    String[] answers = {"18", "Eighteen"};
    
    
    public void setUp()
    {
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
        assertFalse(room.checkAnswer(null));
    }
    
}
