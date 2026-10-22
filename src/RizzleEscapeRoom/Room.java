package RizzleEscapeRoom;

import java.util.Scanner;
// -------------------------------------------------------------------------
/**
 *  Class that stores question and display information
 * 
 *  @author maple
 *  @version Oct 8, 2026
 */
public class Room
{
    //~ Fields ................................................................
    /**
     * Stores the question the room will display
     */
    String question;
    /**
     * Stores the answers to the question
     */
    LinkedChain<String> answers;
    /**
     * Stores data for the case where a room needs to be displayed in a 
     * different format for toString()
     */
    int questionType;
    /**
     * Scans user input
     */
    Scanner scanner = new Scanner(System.in);
    
    //~ Constructors ..........................................................
    /**
     * Create a new Room object.
     * @param question
     * @param answers
     * @param questionType
     */
    public Room(String question, LinkedChain<String> answers, int questionType) {
        this.question = question;
        this.answers = answers;
        this.questionType = questionType;
    }
    
    //~Public  Methods ........................................................
    /**
     * Returns the question text
     * @return String
     */
    public String getQuestion() {
        return question;
    }
    
    /**
     * Returns the acceptable answers
     * @return LinkedChain<String>
     */
    public LinkedChain<String> getAnswers() {
        return answers;
    }
    
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param answer 
     * @return boolean
     */
    public boolean checkAnswer(String answer) {
        boolean contains = false;
        for(int i = 0; i < answers.size(); i += 1) {
            if(answer != null && answers.get(i).equalsIgnoreCase(answer.trim())) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * Returns the question text for display.
     * @return question
     */
    public String toString() {
        return question;
    }
}
