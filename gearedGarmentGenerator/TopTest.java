package gearedGarmentGenerator;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 *  Tests each code in the Top class.
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class TopTest extends TestCase {
    //~ Fields ................................................................
    private Top top;
    //~ Constructors ..........................................................
    /**
     * Setup for Top class.
     */
    public void setUp() {
        top = new Top(false, "casual", "tank");
    }
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Tests the Top class.
     */
    public void testTopClass() {
        assertFalse(top.getWarmth());
        assertEquals("casual", top.getFormality());
        assertEquals("tank", top.getDescription());
    }
}
