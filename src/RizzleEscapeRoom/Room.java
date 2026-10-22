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
    public static final int RIDDLE = 0;
    public static final int TRIVIA = 1;
    public static final int MATH = 2;
    public static final int TROLL = 3;
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
     * Stores how difficult the question is, from 1 (easy) to 5 (hard).
     */
    int difficulty;
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
    public Room(String question, String[] answers, int questionType) {
        this(question, makeAnswerChain(answers), questionType, 1);
    }

    /**
     * Create a new Room object.
     * @param question
     * @param answers
     * @param questionType
     */
    public Room(String question, LinkedChain<String> answers, int questionType) {
        this(question, answers, questionType, 1);
    }

    /**
     * Create a new Room object with a difficulty rating.
     * @param question
     * @param answers
     * @param questionType
     * @param difficulty
     */
    public Room(String question, LinkedChain<String> answers,
        int questionType, int difficulty) {
        this.question = question;
        this.answers = answers;
        this.questionType = questionType;
        this.difficulty = Math.max(1, Math.min(5, difficulty));
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
     * @return the accepted answer chain
     */
    public LinkedChain<String> getAnswers() {
        return answers;
    }

    /**
     * Returns the type of question in this room.
     * @return questionType
     */
    public int getQuestionType() {
        return questionType;
    }

    /**
     * Returns the difficulty rating for this room.
     * @return difficulty
     */
    public int getDifficulty() {
        return difficulty;
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
        return getTypeName() + question;
    }

    /**
     * Returns the display name for this room's question type.
     * @return type name
     */
    private String getTypeName() {
        if (questionType == TRIVIA) {
            return "[Trivia] ";
        }
        if (questionType == MATH) {
            return "[Math] ";
        }
        if (questionType == TROLL) {
            return "[Troll] ";
        }
        return "[Riddle] ";
    }

    /**
     * Builds an answer chain from an array.
     * @param answerArray the answers to add
     * @return a chain containing the answers
     */
    private static LinkedChain<String> makeAnswerChain(String[] answerArray) {
        LinkedChain<String> answerChain = new LinkedChain<String>();
        for (int i = 0; i < answerArray.length; i += 1) {
            answerChain.addToEnd(answerArray[i]);
        }
        return answerChain;
    }
}
