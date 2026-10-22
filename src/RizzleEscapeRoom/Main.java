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
     * Builds the chain of rooms the player can choose from.
     *
     * @return a chain holding every room, in order
     */
    public static LinkedChain<Room> buildRooms()
    {
        LinkedChain<Room> rooms = new LinkedChain<Room>();
        addRoom(rooms, "What has keys but can't open locks?",
            Room.RIDDLE, 1, "piano", "a piano", "keyboard", "a keyboard");
        addRoom(rooms, "What gets wetter the more it dries?",
            Room.RIDDLE, 1, "towel", "a towel");
        addRoom(rooms, "How many months have 28 days?",
            Room.RIDDLE, 1, "12", "twelve", "all", "all of them");
        addRoom(rooms, "What has hands but cannot clap?",
            Room.RIDDLE, 1, "clock", "a clock");
        addRoom(rooms, "What has a head and a tail but no body?",
            Room.RIDDLE, 2, "coin", "a coin");
        addRoom(rooms, "The more you take, the more you leave behind. What are they?",
            Room.RIDDLE, 2, "footsteps", "steps");
        addRoom(rooms, "What has one eye but cannot see?",
            Room.RIDDLE, 2, "needle", "a needle");
        addRoom(rooms, "What's black, white, and red all over?",
            Room.RIDDLE, 1, "newspaper", "a newspaper");
        addRoom(rooms, "What comes once in a minute, twice in a moment, but never in a thousand years?",
            Room.RIDDLE, 2, "m", "the letter m");

        addRoom(rooms, "What is the capital of France?",
            Room.TRIVIA, 1, "paris");
        addRoom(rooms, "Which planet is known as the Red Planet?",
            Room.TRIVIA, 1, "mars");
        addRoom(rooms, "What is the largest ocean on Earth?",
            Room.TRIVIA, 2, "pacific", "pacific ocean");
        addRoom(rooms, "How many legs does an insect have?",
            Room.TRIVIA, 2, "6", "six");
        addRoom(rooms, "What is the chemical formula for water?",
            Room.TRIVIA, 3, "h2o");
        addRoom(rooms, "How many teeth does a typical adult human have?",
            Room.TRIVIA, 3, "32", "thirty two", "thirty-two");

        addRoom(rooms, "What is 9 multiplied by 7?",
            Room.MATH, 1, "63", "sixty three", "sixty-three");
        addRoom(rooms, "What is 144 divided by 12?",
            Room.MATH, 1, "12", "twelve");
        addRoom(rooms, "What is 25 percent of 80?",
            Room.MATH, 2, "20", "twenty");
        addRoom(rooms, "What is 15 plus 27?",
            Room.MATH, 2, "42", "forty two", "forty-two");
        addRoom(rooms, "What is the square root of 81?",
            Room.MATH, 3, "9", "nine");
        addRoom(rooms, "What is 100 minus 37?",
            Room.MATH, 3, "63", "sixty three", "sixty-three");

        addRoom(rooms, "How many sides does a circle have?",
            Room.TROLL, 1, "0", "zero", "none");
        addRoom(rooms, "If you overtake the person in second place, what place are you in?",
            Room.TROLL, 2, "second", "2nd");
        addRoom(rooms, "Which weighs more: a kilogram of feathers or a kilogram of steel?",
            Room.TROLL, 2, "they weigh the same", "same", "equal");
        addRoom(rooms, "How many months have at least 28 days?",
            Room.TROLL, 3, "12", "twelve", "all of them");
        addRoom(rooms, "A farmer has 17 sheep and all but 9 run away. How many remain?",
            Room.TROLL, 3, "9", "nine");
        addRoom(rooms, "Spell 'sit' using the letters S, I, and T, but say the letters aloud.",
            Room.TROLL, 4, "s i t", "s-i-t", "sit");
        return rooms;
    }

    /**
     * Adds a room and its answers to a room chain.
     * @param rooms chain to add the room to
     * @param question the question text
     * @param questionType the category for this question
     * @param difficulty the difficulty from 1 to 5
     * @param answers accepted answers
     */
    private static void addRoom(LinkedChain<Room> rooms, String question,
        int questionType, int difficulty, String... answers)
    {
        LinkedChain<String> answerChain = new LinkedChain<String>();
        for (int i = 0; i < answers.length; i += 1)
        {
            answerChain.addToEnd(answers[i]);
        }
        rooms.addToEnd(new Room(question, answerChain, questionType, difficulty));
    }

    /**
     * Builds the chain of fun facts that can show up between rooms.
     *
     * @return a chain holding every fun fact
     */
    public static LinkedChain<FunFact> buildFunFacts()
    {
        LinkedChain<FunFact> facts = new LinkedChain<FunFact>();
        facts.add(new FunFact("Honey can remain edible for a very long time.",
            FunFact.FACT));
        facts.add(new FunFact("Octopuses have three hearts.", FunFact.FACT));
        facts.add(new FunFact("Bananas are berries, but strawberries are not.",
            FunFact.FACT));
        facts.add(new FunFact("A group of flamingos is called a flamboyance.",
            FunFact.FACT));
        facts.add(new FunFact("The Eiffel Tower can grow taller in hot weather.",
            FunFact.FACT));
        facts.add(new FunFact("Wombat droppings are cube-shaped.", FunFact.FACT));
        facts.add(new FunFact("Hint card: read the wording of each question carefully.",
            FunFact.HINT));
        facts.add(new FunFact("Troll card: the obvious answer may be a trick.",
            FunFact.TROLL));
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
        return buildQuiz().createGame(Quiz.ALL, 0, Quiz.SEQUENTIAL,
            STARTING_LIVES, false);
    }

    /**
     * Builds the standard quiz used by the game.
     * @return the standard quiz
     */
    public static Quiz buildQuiz()
    {
        return new Quiz("Rizzle Escape Room", buildRooms(), buildFunFacts());
    }

    /**
     * Creates a custom quiz by asking for questions and answers.
     * @param scanner input source
     * @return the custom quiz, or null if input closes
     */
    public static Quiz buildCustomQuiz(Scanner scanner)
    {
        System.out.println("\nHow many questions should the custom quiz have?");
        int count = readNumber(scanner, 1, 30, 3);
        LinkedChain<Room> rooms = new LinkedChain<Room>();
        for (int i = 0; i < count; i += 1)
        {
            System.out.println("\nEnter question " + (i + 1) + ":");
            String question = getUserInput(scanner);
            if (question == null)
            {
                return null;
            }
            System.out.println("\nEnter accepted answers separated by commas:");
            String answerLine = getUserInput(scanner);
            if (answerLine == null)
            {
                return null;
            }
            LinkedChain<String> answers = new LinkedChain<String>();
            String[] parts = answerLine.split(",");
            for (int j = 0; j < parts.length; j += 1)
            {
                String answer = parts[j].trim();
                if (!answer.isEmpty())
                {
                    answers.addToEnd(answer);
                }
            }
            while (answers.isEmpty())
            {
                System.out.println("\nEnter at least one accepted answer:");
                answerLine = getUserInput(scanner);
                if (answerLine == null)
                {
                    return null;
                }
                parts = answerLine.split(",");
                for (int j = 0; j < parts.length; j += 1)
                {
                    String answer = parts[j].trim();
                    if (!answer.isEmpty())
                    {
                        answers.addToEnd(answer);
                    }
                }
            }
            System.out.println("\nDifficulty from 1 (easy) to 5 (hard):");
            int difficulty = readNumber(scanner, 1, 5, 1);
            rooms.addToEnd(new Room(question, answers, Room.RIDDLE, difficulty));
        }
        System.out.println("\nEnter the name of your quiz:");
        String name = getUserInput(scanner);
        if (name == null)
        {
            return null;
        }
        System.out.println("\nThat name is amazing!");
        return new Quiz(name, rooms, buildFunFacts());
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
                System.out.println("\nInput closed. Goodbye!");
                return;
            }
            if (room.checkAnswer(answer))
            {
                System.out.println("\nCorrect!");
                game.recordAnswer(true);
                game.plusRoomIndex();
                if (!game.checkWin()) {
                    game.nextRoom();
                }
                if (random.nextInt(3) == 0)
                {
                    FunFact fact = game.getRandomFunFact();
                    if (fact != null)
                    {
                        System.out.println("\n" + fact);
                    }
                }
            }
            else
            {
                game.recordAnswer(false);
                if (!game.isNoLivesMode())
                {
                    game.loseLife();
                    System.out.println("\nWrong! Lives left: " + game.getLives());
                }
                else
                {
                    System.out.println("\nIncorrect!");
                    game.plusRoomIndex();
                    if (!game.checkWin()) {
                        game.nextRoom();
                    }
                    if (random.nextInt(3) == 0)
                    {
                        FunFact fact = game.getRandomFunFact();
                        if (fact != null)
                        {
                            System.out.println("\n" + fact);
                        }
                    }
                }
            }
        }
        if (game.checkWin())
        {
            if(game.isNoLivesMode()) {
                System.out.println("\nYou have completed the quiz!");
            }
            else{
                System.out.println("\nYou escaped! You win!");
            }
        }
        else
        {
            System.out.println("\nYou ran out of lives. Game over.");
        }
        if (game.isNoLivesMode())
        {
            System.out.println("Score: " + game.getCorrectAnswers() + "/"
                + game.getAnsweredQuestions() + " ("
                + game.getPercentageCorrect()
                + "% correct)");
        }
    }

    /**
     * Asks the player for the quiz and game settings.
     * @param scanner input source
     * @return a configured game, or null if input closes
     */
    public static Game chooseGame(Scanner scanner)
    {
        System.out.println("\nChoose a quiz: 1 All, 2 Riddle, 3 Trivia, "
            + "4 Math, 5 Troll, 6 Custom");
        int quizChoice = readNumber(scanner, 1, 6, 1);
        Quiz quiz;
        int category = Quiz.ALL;
        if (quizChoice == 6)
        {
            quiz = buildCustomQuiz(scanner);
            if (quiz == null)
            {
                return null;
            }
        }
        else
        {
            quiz = buildQuiz();
            if (quizChoice == 2)
            {
                category = Room.RIDDLE;
            }
            else if (quizChoice == 3)
            {
                category = Room.TRIVIA;
            }
            else if (quizChoice == 4)
            {
                category = Room.MATH;
            }
            else if (quizChoice == 5)
            {
                category = Room.TROLL;
            }
        }
        int maximum = quiz.selectRooms(category, 0,
            Quiz.SEQUENTIAL).size();
        if (maximum == 0)
        {
            return null;
        }
        System.out.println("\nHow many rooms? Enter a number or all (1-"
            + maximum + "):");
        int length = readLength(scanner, maximum);
        System.out.println("\nOrder: 1 Sequential, 2 Random, 3 Progressive:");
        int order = readNumber(scanner, 1, 3, 1) - 1;
        System.out.println("\nMode: 1 Lives, 2 No lives with percentage score:");
        boolean noLives = readNumber(scanner, 1, 2, 1) == 2;
        return quiz.createGame(category, length, order, STARTING_LIVES,
            noLives);
    }

    /**
     * Reads an integer in the given range.
     * @param scanner input source
     * @param minimum smallest accepted number
     * @param maximum largest accepted number
     * @param defaultValue value used when input closes
     * @return accepted number
     */
    private static int readNumber(Scanner scanner, int minimum, int maximum,
        int defaultValue)
    {
        while (true)
        {
            String input = getUserInput(scanner);
            if (input == null)
            {
                return defaultValue;
            }
            try
            {
                int value = Integer.parseInt(input);
                if (value >= minimum && value <= maximum)
                {
                    return value;
                }
            }
            catch (NumberFormatException e)
            {
                // The shared message below handles invalid input.
            }
            System.out.println("\nEnter a number from " + minimum + " to "
                + maximum + ".");
        }
    }

    /**
     * Reads a requested room length, accepting all for the full quiz.
     * @param scanner input source
     * @param maximum number of available rooms
     * @return selected number of rooms
     */
    private static int readLength(Scanner scanner, int maximum)
    {
        while (true)
        {
            String input = getUserInput(scanner);
            if (input == null || input.equalsIgnoreCase("all"))
            {
                return 0;
            }
            try
            {
                int length = Integer.parseInt(input);
                if (length > 0 && length <= maximum)
                {
                    return length;
                }
            }
            catch (NumberFormatException e)
            {
                // The shared message below handles invalid input.
            }
            System.out.println("\nEnter a room count from 1 to " + maximum
                + " or all.");
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
            System.out.println("\nPlease type yes or no.");
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
            Game game = chooseGame(scanner);
            if (game == null)
            {
                break;
            }
            runGame(game, scanner);
            playing = askReplay(scanner);
        }
        System.out.println("\nThanks for playing!");
        scanner.close();
    }
}
