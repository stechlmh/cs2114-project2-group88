package RizzleEscapeRoom;

import RizzleEscapeRoom.FunFact;

// -------------------------------------------------------------------------
/**
 *  Tests FunFact class
 * 
 *  @author Katie Smith
 *  @version Oct 8, 2026
 */
public class FunFactTest extends student.TestCase
{
   private FunFact fact;
   
   
   
   public void setUp()
   {
       fact = new FunFact("An octopus has 3 hearts", 0);
   }
   
   
   /**
    * tests that exception is thrown when constructor is called with null string
    */
   public void testConstructorNullFact()
   {
       Exception thrown = null;
       try
       {
           new FunFact(null, 0);
       }
       catch (IllegalArgumentException e)
       {
           thrown = e;
       }
       
       assertNotNull(thrown);
       assertEquals("Fact cannot be null", thrown.getMessage());
   }
   
   /**
    * tests getFunFact()
    */
   public void testGetFunFact()
   {
       assertEquals("An octopus has 3 hearts", fact.getFunFact());
   }
   
   
   /**
    * tests setFunFact()
    */
   public void testSetFunFact()
   {
       fact.setFunFact("Fortune cookies were invented in California");
       assertEquals("Fortune cookies were invented in California", fact.getFunFact());
   }
   
   
   /**
    * tests toString()
    */
   public void testToString()
   {
       assertEquals("An octopus has 3 hearts", fact.toString());
   }

   /**
    * Checks hint and troll facts display their formats.
    */
   public void testQuestionTypes()
   {
       FunFact hint = new FunFact("Look closely.", FunFact.HINT);
       FunFact troll = new FunFact("This may be a trick.", FunFact.TROLL);
       assertEquals("Hint: Look closely.", hint.toString());
       assertEquals("Troll fact: This may be a trick.", troll.toString());
       assertEquals(FunFact.HINT, hint.getQuestionType());
   }
   
}
