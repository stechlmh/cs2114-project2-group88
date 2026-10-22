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
        assertEquals(27, game.getRoomPool().size());
    }

    /**
     * Normal case: getUserInput trims whitespace from an answer.
     */
    public void testGetUserInputTrims()
    {
        Scanner scanner = new Scanner("  answer  \n");
        assertEquals("answer", Main.getUserInput(scanner));
        scanner.close();
    }

    /**
     * Bad-input case: getUserInput skips blank lines and returns null at EOF.
     */
    public void testGetUserInputBlankAndEof()
    {
        Scanner scanner = new Scanner("   \n");
        assertNull(Main.getUserInput(scanner));
        scanner.close();
    }

    /**
     * Normal case: answering every room wins without advancing past the end.
     */
    public void testRunGameWins()
    {
        LinkedChain<Room> rooms = new LinkedChain<Room>();
        rooms.addToEnd(new Room("First?", new String[] { "piano" }, 0));
        rooms.addToEnd(new Room("Second?", new String[] { "towel" }, 0));
        rooms.addToEnd(new Room("Third?", new String[] { "12" }, 0));
        Game game = new Game(rooms, new LinkedChain<FunFact>(), "Welcome!",
            0, 3);
        Scanner scanner = new Scanner("piano\ntowel\n12\n");
        Main.runGame(game, scanner);
        assertTrue(game.checkWin());
        assertFalse(game.checkLoss());
        assertEquals(3, game.getRoomIndex());
        scanner.close();
    }

    /**
     * Normal case: three wrong answers end the game as a loss.
     */
    public void testRunGameLoses()
    {
        LinkedChain<Room> rooms = new LinkedChain<Room>();
        rooms.addToEnd(new Room("First?", new String[] { "yes" }, 0));
        Game game = new Game(rooms, new LinkedChain<FunFact>(), "Welcome!",
            0, 3);
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
        Scanner scanner = new Scanner("YES\n");
        assertTrue(Main.askReplay(scanner));
        scanner.close();
    }

    /**
     * Bad-input case: asking to replay rejects an invalid answer and then
     * accepts no.
     */
    public void testAskReplayInvalidThenNo()
    {
        Scanner scanner = new Scanner("maybe\nno\n");
        assertFalse(Main.askReplay(scanner));
        scanner.close();
    }
}
