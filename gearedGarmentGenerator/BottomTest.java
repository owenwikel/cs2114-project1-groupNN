package gearedGarmentGenerator;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 *  Tests each code in the Bottom class.
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class BottomTest extends TestCase {
    //~ Fields ................................................................
    private Bottom bottom;
    //~ Constructors ..........................................................
    /**
     * Setup for Bottom class.
     */
    public void setUp() {
        bottom = new Bottom(false, "casual", "shorts");
    }
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Tests the Bottom class.
     */
    public void testBottomClass() {
        assertFalse(bottom.getWarmth());
        assertEquals("casual", bottom.getFormality());
        assertEquals("shorts", bottom.getDescription());
    }
}
