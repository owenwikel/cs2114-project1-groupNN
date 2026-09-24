package gearedGarmentGenerator;
import junit.framework.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 * Test the Shoes class
 * 
 *  @author sydneyyang
 *  @version Sep 23, 2026
 */
public class ShoesTest extends TestCase
{
    private Shoes shoes;
    /**
     * setting up initial condition
     */
    public void setUp() {
        shoes = new Shoes(false, "casual", "white sneakers");
    }
    /**
     * tests setters and getters from shoes class
     */ 
    public void testShoesClass() {
        assertFalse(shoes.getWarmth());
        assertEquals("casual", shoes.getFormality());
        assertEquals("white sneakers", shoes.getDescription());
    }

}
