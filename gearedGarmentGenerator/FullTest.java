package gearedGarmentGenerator;
import junit.framework.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests for Full class
 * 
 *  @author sydneyyang
 *  @version Sep 22, 2026
 */
public class FullTest extends TestCase
{
    private Full full;
    /**
     * Sets up test condition
     */
    public void setUp() {
        full = new Full(true, "formal", "black suit");
    }
    /**
     * tests getters and setters
     */
    public void testFullClass() {
        assertTrue(full.getWarmth());
        assertEquals("formal", full.getFormality());
        assertEquals("black suit", full.getDescription());
    }
    

}
