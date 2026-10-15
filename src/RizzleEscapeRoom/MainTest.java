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
<<<<<<< HEAD
        assertEquals(3, game.getLives());
        assertEquals("Welcome!", game.getIntro());
=======
        Scanner scanner = new Scanner("  answer  \n");
        assertEquals("answer", Main.getUserInput(scanner));
        scanner.close();
>>>>>>> e2adbe9b0f508db0e1cb451fe44581d31b0c4046
    }

    /**
     * Bad-input case: getUserInput skips blank lines and returns null at EOF.
     */
    public void testGetUserInputBlankAndEof()
    {
<<<<<<< HEAD
        assertNotNull(game.toString());
        assertFalse(game.toString().equals(""));
=======
        Scanner scanner = new Scanner("   \n");
        assertNull(Main.getUserInput(scanner));
        scanner.close();
>>>>>>> e2adbe9b0f508db0e1cb451fe44581d31b0c4046
    }

    /**
     * Normal case: answering every room wins the game without advancing
     * beyond the last room.
     */
<<<<<<< HEAD
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
>>>>>>> e2adbe9b0f508db0e1cb451fe44581d31b0c4046
    }

    /**
     * Normal case: asking to replay accepts yes without regard to case.
     */
    public void testAskReplayYes()
    {
<<<<<<< HEAD
        game.loseLife();
        game.loseLife();
        assertFalse(Main.loss);
=======
        Scanner scanner = new Scanner("YES\n");
        assertTrue(Main.askReplay(scanner));
        scanner.close();
>>>>>>> e2adbe9b0f508db0e1cb451fe44581d31b0c4046
    }

    /**
     * Bad-input case: asking to replay rejects an invalid answer and then
     * accepts no.
     */
    public void testAskReplayInvalidThenNo()
    {
<<<<<<< HEAD
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
=======
        Scanner scanner = new Scanner("maybe\nno\n");
        assertFalse(Main.askReplay(scanner));
        scanner.close();
>>>>>>> e2adbe9b0f508db0e1cb451fe44581d31b0c4046
    }
}
