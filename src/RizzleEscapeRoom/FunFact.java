package RizzleEscapeRoom;

// -------------------------------------------------------------------------
/**
 *  Class storing a fun fact
 * 
 *  @author Katie Smith
 *  @version Oct 8, 2026
 */
public class FunFact
{
    public static final int FACT = 0;
    public static final int HINT = 1;
    public static final int TROLL = 2;
    private String fact;
    private int questionType;
    
    
    /**
     * creates a new FunFact object
     * 
     * @param fact
     * @param questionType
     */
    public FunFact(String fact, int questionType)
    {
        if(fact == null)
        {
            throw new IllegalArgumentException("Fact cannot be null");
        }
        
        this.fact = fact;
        this.questionType = questionType;
    }
    
    
    /**
     * returns fun fact
     * 
     * @return fact the fact that is being returned
     */
    public String getFunFact()
    {
        return fact;
    }

    /**
     * returns the format type of this fact
     * @return questionType
     */
    public int getQuestionType()
    {
        return questionType;
    }
    
    
    /**
     * sets fun fact to a new string
     * 
     * @param fact new fun fact
     */
    public void setFunFact(String fact)
    {
        this.fact = fact;
    }
    
    
    /**
     * returns fact as a string based on question type (to be implemented)
     */
    public String toString()
    {
        if(questionType == HINT)
        {
            return "Hint: " + fact;
        }
        if(questionType == TROLL)
        {
            return "Troll fact: " + fact;
        }
        return fact;
    }
}
