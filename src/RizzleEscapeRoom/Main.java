package RizzleEscapeRoom;

import java.util.Scanner;

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
        rooms.addToEnd(new Room("What has keys but can't open locks?",
            new String[] { "piano", "a piano", "keyboard" }, 0));
        rooms.addToEnd(new Room("What gets wetter the more it dries?",
            new String[] { "towel", "a towel" }, 0));
        rooms.addToEnd(new Room("How many months have 28 days?",
            new String[] { "12", "twelve", "all of them" }, 0));
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
     * Starts the program.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Game game = buildGame();
        game.printIntro();
        scanner.close();
    }
}