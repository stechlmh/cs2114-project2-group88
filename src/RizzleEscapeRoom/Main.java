package RizzleEscapeRoom;

import java.util.Scanner;
import java.util.Random;

/**
 * Main class for Rizzle Escape Room. Handles player interaction: reading
 * input, creating the game, and running the game loop.
 *  @author ruhaansingh
 *  @version Oct 14, 2026

 */
public class Main
{
    private static final int STARTING_LIVES = 3;
    private static final String INTRO =
        "Welcome to Rizzle Escape Room!\n"
        + "Answer each riddle to move to the next room.\n"
        + "A wrong answer costs a life. Lose them all and you lose.";
    /**
     * Gets one line of input from the player. Blank lines are skipped and
     * the player is asked again, so the caller never has to handle empty
     * input.
     *
     * @param scanner the scanner to read from
     * @return the trimmed input, or null if the input stream has closed
     * @throws IllegalArgumentException if scanner is null
     */
    public static String getUserInput(Scanner scanner)
    {
        if (scanner == null)
        {
            throw new IllegalArgumentException("Scanner must not be null");
        }
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            line = line.trim();
            if (line.isEmpty())
            {
                System.out.println("Please type an answer.");
            }
            else
            {
                return line;
            }
        }
        return null;
    }
    
    /**
     * Builds the chain of rooms the player will go through. More questions
     * get added here until there are 25-30.
     *
     * @return a chain holding every room, in order
     */
    public static LinkedChain<Room> buildRooms()
    {
        LinkedChain<Room> rooms = new LinkedChain<Room>();
        LinkedChain<String> answers = new LinkedChain<String>();
        answers.addToEnd("piano");
        answers.addToEnd("a piano");
        answers.addToEnd("keyboard");
        answers.addToEnd("a keyboard");
        rooms.addToEnd(new Room("What has keys but can't open locks?",
            answers, 0));
        LinkedChain<String> answers2 = new LinkedChain<String>();
        answers2.addToEnd("towel");
        answers2.addToEnd("a towel");
        answers2.addToEnd("keyboard");
        answers2.addToEnd("a keyboard");
        rooms.addToEnd(new Room("What gets wetter the more it dries?",
            answers2, 0));
        LinkedChain<String> answers3 = new LinkedChain<String>();
        answers3.addToEnd("12");
        answers3.addToEnd("twelve");
        answers3.addToEnd("all");
        answers3.addToEnd("all of them");
        rooms.addToEnd(new Room("How many months have 28 days?",
            answers3, 0));
        LinkedChain<String> answers4 = new LinkedChain<String>();
        answers4.addToEnd("newspaper");
        answers4.addToEnd("a newspaper");
        rooms.addToEnd(new Room("What's black, white, and red all over?",
            answers4, 0));
        LinkedChain<String> answers5 = new LinkedChain<String>();
        answers5.addToEnd("m");
        answers5.addToEnd("the letter m");
        rooms.addToEnd(new Room("What comes once in a minute, twice in a moment, but never in a thousand years?",
            answers4, 0));
        return rooms;
    }

    /**
     * Builds the chain of fun facts that can show up between rooms.
     *
     * @return a chain holding every fun fact
     */
    public static LinkedChain<FunFact> buildFunFacts()
    {
        LinkedChain<FunFact> facts = new LinkedChain<FunFact>();
        facts.add(new FunFact("Honey never spoils.", 0));
        facts.add(new FunFact("Octopuses have three hearts.", 0));
        return facts;
    }

    /**
     * Creates a brand new game with a fresh set of rooms, fun facts, and
     * lives.
     *
     * @return the new game
     */
    public static Game buildGame()
    {
        return new Game(buildRooms(), buildFunFacts(), INTRO, 0, STARTING_LIVES);
    }

    /**
     * Runs one game from the first room until the player wins, loses, or
     * the input stream closes.
     *
     * @param game the game to run
     * @param scanner the scanner to read answers from
     */
    public static void runGame(Game game, Scanner scanner)
    {
        Random random = new Random();
        System.out.print(game.getIntro());
        while (!game.checkWin() && !game.checkLoss())
        {
            Room room = game.getCurrentRoom();
            System.out.println();
            System.out.println(room);
            String answer = getUserInput(scanner);
            if (answer == null)
            {
                System.out.println("Input closed. Goodbye!");
                return;
            }
            if (room.checkAnswer(answer))
            {
                System.out.println("Correct!");
                game.plusRoomIndex();
                if (!game.checkWin()) {
                    game.nextRoom();
                }
                else {
                    game.nextRoom();
                }
                if (random.nextInt(3) == 0)
                {
                    FunFact fact = game.getRandomFunFact();
                    if (fact != null)
                    {
                        System.out.println("Fun fact: " + fact);
                    }
                }
            }
            else
            {
                game.loseLife();
                System.out.println("Wrong! Lives left: " + game.getLives());
            }
        }
        if (game.checkWin())
        {
            System.out.println("You escaped! You win!");
        }
        else
        {
            System.out.println("You ran out of lives. Game over.");
        }
    }

    /**
     * Asks the player whether they want to play again. Anything other than
     * a yes or no gets asked again.
     *
     * @param scanner the scanner to read from
     * @return true if the player wants to replay, false for no or if the
     *         input stream has closed
     */
    public static boolean askReplay(Scanner scanner)
    {
        while (true)
        {
            System.out.println();
            System.out.println("Play again? (yes/no)");
            String answer = getUserInput(scanner);
            if (answer == null)
            {
                return false;
            }
            answer = answer.toLowerCase();
            if (answer.equals("yes") || answer.equals("y"))
            {
                return true;
            }
            if (answer.equals("no") || answer.equals("n"))
            {
                return false;
            }
            System.out.println("Please type yes or no.");
        }
    }

    /**
     * Starts the program. Keeps replaying new games until the player says no.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;
        while (playing)
        {
            Game game = buildGame();
            runGame(game, scanner);
            playing = askReplay(scanner);
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
