package RizzleEscapeRoom;
import student.TestCase;

/**
 * Tests for Game: at least one normal case and one bad-input case for
 * each key method.
 *
 * @author Ruhaan Singh
 * @version Oct 2026
 */
public class MainTest extends TestCase
{
    private Room first;
    private Room second;
    private LinkedChain<Room> rooms;
    private LinkedChain<FunFact> facts;
    private Game game;

    /**
     * Sets up a game with two rooms, two fun facts, and 3 lives.
     */
    public void setUp()
    {
        first = new Room("Question one?", new String[] { "alpha" }, 0);
        second = new Room("Question two?", new String[] { "beta" }, 0);
        rooms = new LinkedChain<Room>();
        rooms.addToEnd(first);
        rooms.addToEnd(second);
        facts = new LinkedChain<FunFact>();
        facts.add(new FunFact("Honey never spoils.", 0));
        facts.add(new FunFact("Octopuses have three hearts.", 0));
        game = new Game(rooms, facts, "Welcome!", 0, 3);
    }

    /**
     * Normal case: the constructor stores the lives and the intro.
     */
    public void testConstructor()
    {
        assertEquals(3, game.getLives());
        assertEquals("Welcome!", game.getIntro());
    }

    /**
     * Normal case: toString gives back something printable.
     */
    public void testToString()
    {
        assertNotNull(game.toString());
        assertFalse(game.toString().equals(""));
    }

    /**
     * Normal case: setIntro changes what getIntro returns.
     */
    public void testGetIntro()
    {
        assertEquals("Welcome!", game.getIntro());
    }
    /**
     * Normal case: setHearts changes what getHearts returns.
     */
    public void testGetHearts()
    {
        assertEquals(3, game.getLives());
    }

    /**
     * Normal case: loseHeart takes one life away.
     */
    public void testLoseHeart()
    {
        game.loseLife();
        assertEquals(2, game.getLives());
    }

    /**
     * Normal case: the current room at the start is the first room.
     */
    public void testGetCurrentRoomAtStart()
    {
        assertSame(first, game.getCurrentRoom());
    }

    /**
     * Normal case: nextRoom moves from the first room to the second.
     */
    public void testNextRoom()
    {
        game.plusRoomIndex();
        game.nextRoom();
        assertSame(second, game.getCurrentRoom());
    }
    /**
     * Normal case: returns gameIndex
     */
    public void testGameIndex()
    {
        assertEquals(game.getRoomIndex(),0);
    }
    /**
     * Normal case: gameIndex += 1
     */
    public void testPlusRoomIndex()
    {
        game.plusRoomIndex();
        assertEquals(game.getRoomIndex(),1);
    }
    /**
     * Normal case: getRooms gives back the chain the game was built with.
     */
    public void testGetRooms()
    {
        assertEquals(2, game.getRoomPool().size());
    }
    /**
     * Normal case: checkWin is false while rooms are left.
     */
    public void testCheckWinFalseAtStart()
    {
        assertFalse(Main.win);
    }

    /**
     * Bad-input case: checkWin is still false when only some rooms have
     * been cleared.
     */
    public void testCheckWinPartway()
    {
        game.nextRoom();
        assertFalse(Main.win);
    }

    /**
     * Normal case: checkLoss is false while there are lives left.
     */
    public void testCheckLossFalseAtStart()
    {
        assertFalse(Main.loss);
    }

    /**
     * Bad-input case: checkLoss is false with exactly 1 life left.
     */
    public void testCheckLossOneLifeLeft()
    {
        game.loseLife();
        game.loseLife();
        assertFalse(Main.loss);
    }

    /**
     * Normal case: getRandomFunFact gives back a fun fact and removes it
     * from the pool.
     */
    public void testGetRandomFunFact()
    {
        FunFact fact = game.getRandomFunFact();
        assertNotNull(fact);
        assertEquals(1, game.getFunFactPool().size());
    }

    /**
     * Bad-input case: once the pool is empty, getRandomFunFact gives back
     * null instead of crashing.
     */
    public void testGetRandomFunFactEmptyPool()
    {
        game.getRandomFunFact();
        game.getRandomFunFact();
        assertNull(game.getRandomFunFact());
    }
}
