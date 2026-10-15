package RizzleEscapeRoom;

// -------------------------------------------------------------------------
/**
 *  Class that runs game
 * 
 *  @author maple
 *  @version Oct 8, 2026
 */
import java.util.Scanner;
import java.lang.Math;

// -------------------------------------------------------------------------
/**
 *  Class that manages lives, picks rooms and fun facts out of custom chains, 
 *  tracks win/loss, validates the input, displays in console
 * 
 *  @author maple
 *  @version Oct 14, 2026
 */
public class Game
{
    //~ Fields ................................................................
    /**
     * Stores introduction to escape room to explain rules and such
     */
    String intro = "[Introduction Message]";
    /**
     * Stores how many lives the player has.
     */
    int lives;
    /**
     * Stores how many lives the player has.
     */
    int quizType;
    /**
     * Stores current roomIndex
     */
    int roomIndex = 0;
    /**
     * Holds 25-30 Room instances with questions in them to be picked either in 
     * sequence or randomly depending on how we choose to implement (maybe 
     * offering player a choice
     */
    LinkedChain<Room> roomPool = new LinkedChain<Room>();
    /**
     * Holds 5-10 fun facts that are chosen from and randomly displayed at times 
     */
    LinkedChain<FunFact> funFactPool = new LinkedChain<FunFact>();
    /**
     * Pointer showing where in the sequence of rooms the player is and what to 
     * display
     */
    Room currentRoom;
    /**
     * Scans user input
     */
    Scanner scanner = new Scanner(System.in);
    
    //~ Constructors ..........................................................
    /**
     * Create a new Game object.
     * @param rooms
     * @param funFacts
     * @param intro
     * @param quizType
     * @param lives
     */
    public Game(LinkedChain<Room> rooms, LinkedChain<FunFact> funFacts, String intro, int quizType, int lives) {
        roomPool = rooms;
        funFactPool = funFacts;
        this.intro = intro;
        this.quizType = quizType;
        this.lives = lives; 
        if (roomPool.size() > 0) {
            currentRoom = roomPool.get(0);
        }
    }
    
    //~Public  Methods ........................................................
    public String toString() {
        return currentRoom.toString();
    }
    
    // ----------------------------------------------------------
    /**
     * Returns the intro of the game
     * @return String
     */
    public String getIntro() {
        return intro;
    }
    // ----------------------------------------------------------
    /**
     * Returns number of hearts
     * @return int
     */
    public int getLives() {
        return lives;
    }
    // ----------------------------------------------------------
    /**
     * Checks whether every room has been cleared.
     * @return true if the room index has reached the end of the chain
     */
    public boolean checkWin() {
        return roomIndex >= roomPool.size();
    }
    // ----------------------------------------------------------
    /**
     * Checks whether the player has run out of lives.
     * @return true if no lives remain
     */
    public boolean checkLoss() {
        return lives <= 0;
    }
    // ----------------------------------------------------------
    /**
     * Increments number of hearts down by one
     */
    public void loseLife() {
        lives -= 1;
    }
    // ----------------------------------------------------------
    /**
     * Returns roomIndex
     * @return int
     */
    public int getRoomIndex() {
        return roomIndex;
    }
    // ----------------------------------------------------------
    /**
     * Returns roomPool
     * @return LinkedChain<Room>
     */
    public LinkedChain<Room> getRoomPool() {
        return roomPool;
    }
    // ----------------------------------------------------------
    /**
     * Returns currentRoom
     * @return Room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    // ----------------------------------------------------------
    /**
     * iterates the room index by one
     */
    public void plusRoomIndex() {
        roomIndex += 1;
    }
    // ----------------------------------------------------------
    /**
     * Moves one room along the linked chain
     */
    public void nextRoom() {
        if (roomIndex < roomPool.size()) {
            currentRoom = roomPool.get(roomIndex);
        }
        else {
            currentRoom = null;
        }
    }
    // ----------------------------------------------------------
    /**
     * Prints a randomFunFact from the list and removes it
     * @return FunFact
     */
    public FunFact getRandomFunFact() {
        if (funFactPool.isEmpty()) {
            return null;
        }
        int randInt = (int) (Math.random()*funFactPool.size());
        FunFact fact = funFactPool.get(randInt);
        funFactPool.remove(funFactPool.get(randInt));
        return fact;
    }
}
