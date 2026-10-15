package RizzleEscapeRoom;

// -------------------------------------------------------------------------
/**
 *  Tests LinkedList class
 * 
 *  @author maple
 *  @version Oct 8, 2026
 */
public class LinkedListTest extends student.TestCase
{
    private LinkedChain<String> chain;

    /**
     * Sets up an empty chain.
     */
    public void setUp()
    {
        chain = new LinkedChain<String>();
    }

    /**
     * Normal case: addToEnd preserves the order of entries.
     */
    public void testAddToEndAndGet()
    {
        chain.addToEnd("first");
        chain.addToEnd("second");
        assertEquals(2, chain.size());
        assertEquals("first", chain.get(0));
        assertEquals("second", chain.get(1));
    }

    /**
     * Normal case: add places each new entry at the front.
     */
    public void testAddToFront()
    {
        chain.add("first");
        chain.add("second");
        assertEquals("second", chain.get(0));
    }

    /**
     * Normal case: remove deletes the first matching entry.
     */
    public void testRemoveEntry()
    {
        chain.addToEnd("first");
        chain.addToEnd("second");
        assertTrue(chain.remove("second"));
        assertFalse(chain.contains("second"));
    }

    /**
     * Bad-input case: adding a null entry is rejected.
     */
    public void testAddNull()
    {
        Exception thrown = null;
        try
        {
            chain.add(null);
        }
        catch (IllegalArgumentException e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
    }
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

}
