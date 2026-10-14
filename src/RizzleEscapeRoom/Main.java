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
     * Starts the program. Runs the escape room using keyboard input.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Rizzle Escape Room!");
        scanner.close();
    }
}