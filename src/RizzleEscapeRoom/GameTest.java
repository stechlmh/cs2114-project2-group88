package RizzleEscapeRoom;

// -------------------------------------------------------------------------
/**
 * Tests Game class
 * 
 *  @author Ruhaan
 *  @version Oct 15, 2026
 */
public class GameTest extends student.TestCase
{
    private Room first;
    private Room second;
    private LinkedChain<Room> rooms;
    private LinkedChain<FunFact> facts;
    private Game game;

    /**
     * Sets up a game with two rooms and one fun fact.
     */
    public void setUp()
    {
        LinkedChain<String> firstAnswers = new LinkedChain<String>();
        firstAnswers.addToEnd("one");
        firstAnswers.addToEnd("1");
        first = new Room("First question?", firstAnswers, 0);
        LinkedChain<String> secondAnswers = new LinkedChain<String>();
        secondAnswers.addToEnd("two");
        secondAnswers.addToEnd("2");
        second = new Room("Second question?", secondAnswers, 0);
        rooms = new LinkedChain<Room>();
        rooms.addToEnd(first);
        rooms.addToEnd(second);
        facts = new LinkedChain<FunFact>();
        facts.addToEnd(new FunFact("A fun fact.", 0));
        game = new Game(rooms, facts, "Welcome!", 0, 3);
    }

    /**
     * Normal case: the constructor stores game data and starts at room zero.
     */
    public void testConstructor()
    {
        assertEquals("Welcome!", game.getIntro());
        assertEquals(3, game.getLives());
        assertEquals(0, game.getRoomIndex());
        assertSame(first, game.getCurrentRoom());
    }

    /**
     * Normal case: nextRoom moves to the room at the current index.
     */
    public void testNextRoom()
    {
        game.plusRoomIndex();
        game.nextRoom();
        assertSame(second, game.getCurrentRoom());
    }

    /**
     * Boundary case: moving past the last room clears currentRoom without
     * trying to read outside the chain.
     */
    public void testNextRoomPastEnd()
    {
        game.plusRoomIndex();
        game.plusRoomIndex();
        game.nextRoom();
        assertNull(game.getCurrentRoom());
    }

    /**
     * Normal case: losing a life reduces the lives count.
     */
    public void testLoseLife()
    {
        game.loseLife();
        assertEquals(2, game.getLives());
    }

    /**
     * Normal case: checkWin is false until every room is cleared.
     */
    public void testCheckWin()
    {
        assertFalse(game.checkWin());
        game.plusRoomIndex();
        game.plusRoomIndex();
        assertTrue(game.checkWin());
    }

    /**
     * Normal case: checkLoss becomes true when no lives remain.
     */
    public void testCheckLoss()
    {
        assertFalse(game.checkLoss());
        game.loseLife();
        game.loseLife();
        game.loseLife();
        assertTrue(game.checkLoss());
    }

    /**
     * Normal case: getRandomFunFact returns and removes a fact.
     */
    public void testGetRandomFunFact()
    {
        assertEquals("A fun fact.", game.getRandomFunFact().getFunFact());
        assertNull(game.getRandomFunFact());
    }

    /**
     * Bad-input case: an empty fact chain returns null.
     */
    public void testGetRandomFunFactEmptyPool()
    {
        game.getRandomFunFact();
        assertNull(game.getRandomFunFact());
    }
    /**
     * Checks that Game displays its current room.
     */
    public void testToString()
    {
        assertEquals(game.getCurrentRoom().toString(), game.toString());
    }

    /**
     * Checks score tracking and rounded percentage in score mode.
     */
    public void testScoreMode()
    {
        Game scoreGame = new Game(rooms, facts, "Welcome!", 0, 3, true);
        scoreGame.recordAnswer(true);
        scoreGame.recordAnswer(false);
        assertEquals(2, scoreGame.getAnsweredQuestions());
        assertEquals(1, scoreGame.getCorrectAnswers());
        assertEquals(50, scoreGame.getPercentageCorrect());
        scoreGame.loseLife();
        scoreGame.loseLife();
        scoreGame.loseLife();
        assertFalse(scoreGame.checkLoss());
    }
}
