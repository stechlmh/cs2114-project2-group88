package RizzleEscapeRoom;

import java.util.Random;

// -------------------------------------------------------------------------
/**
 * Class that stores a quiz and prepares rooms for a game.
 *
 * @author Ruhaan
 * @version Oct 22, 2026
 */
public class Quiz
{
    public static final int ALL = -1;
    public static final int SEQUENTIAL = 0;
    public static final int RANDOM = 1;
    public static final int PROGRESSIVE = 2;

    private String name;
    private LinkedChain<Room> rooms;
    private LinkedChain<FunFact> facts;

    /**
     * Creates a quiz with its rooms and fun facts.
     * @param name the quiz name
     * @param rooms the rooms to use
     * @param facts the fun facts to use
     */
    public Quiz(String name, LinkedChain<Room> rooms,
        LinkedChain<FunFact> facts)
    {
        if (name == null || rooms == null || facts == null)
        {
            throw new IllegalArgumentException("Quiz values must not be null");
        }
        this.name = name;
        this.rooms = rooms;
        this.facts = facts;
    }

    /**
     * Returns the name of this quiz.
     * @return quiz name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the rooms stored in this quiz.
     * @return room chain
     */
    public LinkedChain<Room> getRooms()
    {
        return rooms;
    }

    /**
     * Returns the fun facts stored in this quiz.
     * @return fun fact chain
     */
    public LinkedChain<FunFact> getFacts()
    {
        return facts;
    }

    /**
     * Builds a game with the requested category, order, length, and mode.
     * @param category question category, or ALL
     * @param length number of rooms, or 0 for all available rooms
     * @param order room order setting
     * @param lives starting number of lives
     * @param noLivesMode true for score mode without lives
     * @return a new game
     */
    public Game createGame(int category, int length, int order, int lives,
        boolean noLivesMode)
    {
        LinkedChain<Room> selectedRooms = selectRooms(category, length, order);
        LinkedChain<FunFact> selectedFacts = copyFacts();
        String intro = "Welcome to " + name + "!\n"
            + "Answer each question to move to the next room.\n"
            + (noLivesMode ? "Your score is based on your answers."
                : "A wrong answer costs a life. Lose them all and you lose.");
        return new Game(selectedRooms, selectedFacts, intro, category, lives,
            noLivesMode);
    }

    /**
     * Adds a room to this quiz.
     * @param room the room to add
     */
    public void addRoom(Room room)
    {
        rooms.addToEnd(room);
    }

    /**
     * Adds a fun fact to this quiz.
     * @param fact the fact to add
     */
    public void addFunFact(FunFact fact)
    {
        facts.addToEnd(fact);
    }

    /**
     * Selects matching rooms and puts them in the requested order.
     * @param category question category, or ALL
     * @param length requested number of rooms
     * @param order room order setting
     * @return selected rooms
     */
    public LinkedChain<Room> selectRooms(int category, int length, int order)
    {
        LinkedChain<Room> matching = new LinkedChain<Room>();
        for (int i = 0; i < rooms.size(); i += 1)
        {
            Room room = rooms.get(i);
            if (category == ALL || room.getQuestionType() == category)
            {
                matching.addToEnd(room);
            }
        }

        Room[] selected = matching.toArray(new Room[matching.size()]);
        int selectedCount = length <= 0 ? selected.length
            : Math.min(length, selected.length);
        if (order == RANDOM)
        {
            shuffle(selected);
        }
        else if (order == PROGRESSIVE)
        {
            sortByDifficulty(selected);
        }

        LinkedChain<Room> result = new LinkedChain<Room>();
        for (int i = 0; i < selectedCount; i += 1)
        {
            result.addToEnd(selected[i]);
        }
        return result;
    }

    /**
     * Copies facts so a game can remove them without changing the quiz.
     * @return copied fun fact chain
     */
    private LinkedChain<FunFact> copyFacts()
    {
        LinkedChain<FunFact> copy = new LinkedChain<FunFact>();
        for (int i = 0; i < facts.size(); i += 1)
        {
            FunFact fact = facts.get(i);
            copy.addToEnd(new FunFact(fact.getFunFact(), fact.getQuestionType()));
        }
        return copy;
    }

    /**
     * Randomizes the order of a room array.
     * @param selected rooms to shuffle
     */
    private void shuffle(Room[] selected)
    {
        Random random = new Random();
        for (int i = selected.length - 1; i > 0; i -= 1)
        {
            int swapIndex = random.nextInt(i + 1);
            Room temp = selected[i];
            selected[i] = selected[swapIndex];
            selected[swapIndex] = temp;
        }
    }

    /**
     * Orders rooms from easiest to hardest.
     * @param selected rooms to sort
     */
    private void sortByDifficulty(Room[] selected)
    {
        for (int i = 0; i < selected.length - 1; i += 1)
        {
            int lowest = i;
            for (int j = i + 1; j < selected.length; j += 1)
            {
                if (selected[j].getDifficulty()
                    < selected[lowest].getDifficulty())
                {
                    lowest = j;
                }
            }
            Room temp = selected[i];
            selected[i] = selected[lowest];
            selected[lowest] = temp;
        }
    }
}
