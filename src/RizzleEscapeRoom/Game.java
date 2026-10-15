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
    String winScreen = "[Win Message]";
    String loseScreen = "[Lose Message]";
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
     * @param winScreen 
     * @param loseScreen 
     * @param quizType
     * @param lives
     */
    public Game(LinkedChain<Room> rooms, LinkedChain<FunFact> funFacts, String intro, String winScreen, String loseScreen, int quizType, int lives) {
        roomPool = rooms;
        funFactPool = funFacts;
        this.intro = intro;
        this.winScreen = winScreen;
        this.loseScreen = loseScreen;
        this.quizType = quizType;
        this.lives = lives; 
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
     * Increments number of hearts down by one
     */
    public void loseLife() {
        lives -= 1;
    }
    // ----------------------------------------------------------
    /**
     * Moves one room along the linked chain, if final room, win!
     */
    public void nextRoom() {
        roomIndex += 1;
        currentRoom = roomPool.get(roomIndex);
    }
    // ----------------------------------------------------------
    /**
     * Returns winScreen
     * @return String
     */
    public String getWinScreen() {
        return winScreen;
    }
 // ----------------------------------------------------------
    /**
     * Returns loseScreen
     * @return String
     */
    public String getLoseScreen() {
        return loseScreen;
    }
    // ----------------------------------------------------------
    /**
     * Prints a randomFunFact from the list and removes it
     * @return FunFact
     */
    public FunFact returnRandomFunFact() {
        int randInt = (int) (Math.random()*funFactPool.size());
        FunFact fact = funFactPool.get(randInt);
        funFactPool.remove(funFactPool.get(randInt));
        return fact;
    }
}
