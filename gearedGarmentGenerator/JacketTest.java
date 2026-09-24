package gearedGarmentGenerator;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 *  Tests each code in the Jacket class.
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class JacketTest extends TestCase {
    //~ Fields ................................................................
    private Jacket jacket;
    //~ Constructors ..........................................................
    /**
     * Setup for Jacket class.
     */
    public void setUp() {
        jacket = new Jacket(true, "casual", "coat", "winter jacket");
    }
    // ----------------------------------------------------------
    /**
     * Tests getJacketType method.
     */
    //~Public  Methods ........................................................
    public void testGetJacketType() {
        assertEquals("winter jacket", jacket.getJacketType());
    }
    // ----------------------------------------------------------
    /**
     * Tests setJackectType method.
     */
    public void testSetJacketType() {
        jacket.setJacketType("rain jacket");
        assertEquals("rain jacket", jacket.getJacketType());
    }
}
