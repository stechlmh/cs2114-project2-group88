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
        assertEquals(3, game.getHearts());
        assertEquals("Welcome!", game.getIntro());
    }

    /**
     * Normal case: toString gives back something printable.
     */
    public void testToString()
    {
        assertNotNull(game.toString());
        assertTrue(game.toString().length() > 0);
    }

    /**
     * Normal case: setIntro changes what getIntro returns.
     */
    public void testSetIntro()
    {
        game.setIntro("New intro");
        assertEquals("New intro", game.getIntro());
    }

    /**
     * Normal case: printIntro prints the intro text.
     */
    public void testPrintIntro()
    {
        game.printIntro();
        assertTrue(systemOut().getHistory().contains("Welcome!"));
    }

    /**
     * Bad-input case: an empty intro prints without crashing.
     */
    public void testPrintIntroEmpty()
    {
        game.setIntro("");
        game.printIntro();
        assertEquals("", game.getIntro());
    }

    /**
     * Normal case: setHearts changes what getHearts returns.
     */
    public void testSetHearts()
    {
        game.setHearts(5);
        assertEquals(5, game.getHearts());
    }

    /**
     * Normal case: loseHeart takes one life away.
     */
    public void testLoseHeart()
    {
        game.loseHeart();
        assertEquals(2, game.getHearts());
    }

    /**
     * Bad-input case: lives never go below 0 no matter how many times
     * loseHeart is called.
     */
    public void testLoseHeartNeverNegative()
    {
        game.setHearts(0);
        game.loseHeart();
        assertEquals(0, game.getHearts());
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
        game.nextRoom();
        assertSame(second, game.getCurrentRoom());
    }

    /**
     * Normal case: getRooms gives back the chain the game was built with.
     */
    public void testGetRooms()
    {
        assertEquals(2, game.getRooms().size());
    }

    /**
     * Normal case: setRooms swaps in a new chain of rooms.
     */
    public void testSetRooms()
    {
        LinkedChain<Room> other = new LinkedChain<Room>();
        other.addToEnd(new Room("Only one?", new String[] { "gamma" }, 0));
        game.setRooms(other);
        assertEquals(1, game.getRooms().size());
    }

    /**
     * Normal case: checkWin is false while rooms are left.
     */
    public void testCheckWinFalseAtStart()
    {
        assertFalse(game.checkWin());
    }

    /**
     * Normal case: checkWin is true after every room is cleared.
     */
    public void testCheckWinAfterAllRooms()
    {
        game.nextRoom();
        game.nextRoom();
        assertTrue(game.checkWin());
    }

    /**
     * Bad-input case: checkWin is still false when only some rooms have
     * been cleared.
     */
    public void testCheckWinPartway()
    {
        game.nextRoom();
        assertFalse(game.checkWin());
    }

    /**
     * Normal case: checkLoss is false while there are lives left.
     */
    public void testCheckLossFalseAtStart()
    {
        assertFalse(game.checkLoss());
    }

    /**
     * Normal case: checkLoss is true once lives hit 0.
     */
    public void testCheckLossAtZero()
    {
        game.setHearts(0);
        assertTrue(game.checkLoss());
    }

    /**
     * Bad-input case: checkLoss is false with exactly 1 life left.
     */
    public void testCheckLossOneLifeLeft()
    {
        game.setHearts(1);
        assertFalse(game.checkLoss());
    }

    /**
     * Normal case: getRandomFunFact gives back a fun fact and removes it
     * from the pool.
     */
    public void testGetRandomFunFact()
    {
        FunFact fact = game.getRandomFunFact();
        assertNotNull(fact);
        assertEquals(1, game.getFunFact().size());
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

    /**
     * Normal case: setFunFact swaps in a new chain of fun facts.
     */
    public void testSetFunFact()
    {
        LinkedChain<FunFact> other = new LinkedChain<FunFact>();
        other.add(new FunFact("Bananas are berries.", 0));
        game.setFunFact(other);
        assertEquals(1, game.getFunFact().size());
    }
}
