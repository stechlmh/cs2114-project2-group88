package RizzleEscapeRoom;
import java.util.Scanner;
import student.TestCase;

/**
 * Tests Main class and game flow.
 *
 * @author Ruhaan Singh
 * @version Oct 2026
 */
public class MainTest extends TestCase
{
    /**
     * Normal case: buildGame starts with its first room ready.
     */
    public void testBuildGameStartsAtFirstRoom()
    {
        Game game = Main.buildGame();
        assertNotNull(game.getCurrentRoom());
        assertEquals("What has keys but can't open locks?",
            game.getCurrentRoom().getQuestion());
    }

    /**
     * Normal case: getUserInput trims whitespace from an answer.
     */
    public void testGetUserInputTrims()
    {
        assertEquals(3, game.getLives());
        assertEquals(3, game.getHearts());
        assertEquals("Welcome!", game.getIntro());
        Scanner scanner = new Scanner("  answer  \n");
        assertEquals("answer", Main.getUserInput(scanner));
        scanner.close();
    }

    /**
     * Bad-input case: getUserInput skips blank lines and returns null at EOF.
     */
    public void testGetUserInputBlankAndEof()
    {
        assertNotNull(game.toString());
        assertFalse(game.toString().equals(""));
        Scanner scanner = new Scanner("   \n");
        assertNull(Main.getUserInput(scanner));
        scanner.close();
        assertTrue(game.toString().length() > 0);
    }

    /**
     * Normal case: answering every room wins the game without advancing
     * beyond the last room.
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
        assertFalse(Main.loss);
=======
    public void testRunGameWins()
    {
        Game game = Main.buildGame();
        Scanner scanner = new Scanner("piano\ntowel\n12\n");
        Main.runGame(game, scanner);
        assertTrue(game.checkWin());
        assertFalse(game.checkLoss());
        assertEquals(3, game.getRoomIndex());
        scanner.close();
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
     * Normal case: three wrong answers end the game as a loss.
     */
    public void testRunGameLoses()
    {
        Game game = Main.buildGame();
        Scanner scanner = new Scanner("wrong\nwrong\nwrong\n");
        Main.runGame(game, scanner);
        assertTrue(game.checkLoss());
        assertFalse(game.checkWin());
        assertEquals(0, game.getRoomIndex());
        scanner.close();
    }

    /**
     * Normal case: asking to replay accepts yes without regard to case.
     */
    public void testAskReplayYes()
    {
        game.loseLife();
        game.loseLife();
        assertFalse(Main.loss);
        Scanner scanner = new Scanner("YES\n");
        assertTrue(Main.askReplay(scanner));
        scanner.close();
        game.setHearts(1);
        assertFalse(game.checkLoss());
    }

    /**
     * Bad-input case: asking to replay rejects an invalid answer and then
     * accepts no.
     */
    public void testAskReplayInvalidThenNo()
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
        Scanner scanner = new Scanner("maybe\nno\n");
        assertFalse(Main.askReplay(scanner));
        scanner.close();
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
