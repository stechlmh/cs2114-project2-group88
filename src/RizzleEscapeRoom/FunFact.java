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
            throw new IllegalArgumentException("Fact Cannot be null");
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
        return fact;
    }
}
