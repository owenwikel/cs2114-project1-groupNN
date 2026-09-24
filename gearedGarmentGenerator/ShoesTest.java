package gearedGarmentGenerator;
import junit.framework.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests for Outfit class
 * 
 *  @author sydneyyang
 *  @version Sep 22, 2026
 */
public class ShoesTest extends TestCase
{
    private Shoes shoes;
    /**
     * Sets up initial test conditions
     */
    public void setUp() {
        shoes = new Shoes(false, "causal", "white sneakers");
    }
    
    /**
     * Tests constructor setters and getters
     */
    public void testShoeClass() {
        assertFalse(shoes.getWarmth());
        assertEquals("casual", shoes.getFormality());
        assertEquals("white sneakers", shoes.getDescription());
    }


}
