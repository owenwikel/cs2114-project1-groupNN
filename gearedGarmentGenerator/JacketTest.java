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
    
    /**
     * Tests meetsCriteria method.
     */
    public void testMeetsCriteria() {
        Weather rain = new Weather(85, true, "rain");
        Weather snow = new Weather(20, true, "snow");
        Weather dry = new Weather(20, false, "none");
        Jacket lightRain = new Jacket(false, "casual", "shell", "rain jacket");
        Jacket lightWinter = new Jacket(false, "casual", "coat", "winter jacket");
        Jacket warmRain = new Jacket(true, "casual", "raincoat", "rain jacket");
        Jacket warmWinter = new Jacket(true, "casual", "puffer", "winter jacket");
        assertFalse(warmWinter.meetsCriteria(snow, "formal"));
        assertTrue(lightRain.meetsCriteria(rain, "casual"));
        assertFalse(lightWinter.meetsCriteria(rain, "casual"));
        assertTrue(warmWinter.meetsCriteria(snow, "casual"));
        assertFalse(warmRain.meetsCriteria(snow, "casual"));
        assertTrue(warmWinter.meetsCriteria(dry, "casual"));
        assertFalse(warmRain.meetsCriteria(dry, "casual"));
    }
}
