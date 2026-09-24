package gearedGarmentGenerator;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 *  Tests each code in the Clothes class.
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class ClothesTest extends TestCase {
    //~ Fields ................................................................
    private Clothes clothes;
    private Clothes item;
    //~ Constructors ..........................................................
    /**
     * Setup for Clothes class.
     */
    public void setUp() {
        clothes = new Clothes(false, "casual", "tank");
        item = new Clothes(true, "casual", "long sleeve");
    }
    // ----------------------------------------------------------
    /**
     * Tests getWarmth method.
     */
    //~Public  Methods ........................................................
    public void testGetWarmth() {
        assertFalse(clothes.getWarmth());
    }
    // ----------------------------------------------------------
    /**
     * Tests setWarmth method.
     */
    public void testSetWarmth() {
        clothes.setWarmth(true);
        assertTrue(clothes.getWarmth());
    }
    // ----------------------------------------------------------
    /**
     * Tests getFormality method.
     */
    //~Public  Methods ........................................................
    public void testGetFormality() {
        assertEquals("casual", clothes.getFormality());
    }
    // ----------------------------------------------------------
    /**
     * Tests setFormality method.
     */
    public void testSetFormality() {
        clothes.setFormality("professional");
        assertEquals("professional", clothes.getFormality());
    }
    // ----------------------------------------------------------
    /**
     * Tests getDescription method.
     */
    //~Public  Methods ........................................................
    public void testGetDescription() {
        assertEquals("tank", clothes.getDescription());
    }
    // ----------------------------------------------------------
    /**
     * Tests setDescription method.
     */
    public void testSetDescription() {
        clothes.setDescription("T-shirt");
        assertEquals("T-shirt", clothes.getDescription());
    }
    // ----------------------------------------------------------
    /**
     * Tests meetsCriteria method (formality doesn't match).
     */
    public void testMeetsCriteriaFT() {
        Weather cold = new Weather(31, false, "snow");
        assertFalse(item.meetsCriteria(cold, "formal"));
    }
    // ----------------------------------------------------------
    /**
     * Tests meetsCriteria method (weather doesn't match).
     */
    public void testMeetsCriteriaTF() {
        Weather warm = new Weather(70, false, "none");
        assertFalse(item.meetsCriteria(warm, "casual"));
    }
    
    // ----------------------------------------------------------
    /**
     * Tests meetsCriteria method (both are true).
     */
    public void testMeetsCriteriaTT() {
        Weather cold = new Weather(31, false, "snow");
        assertTrue(item.meetsCriteria(cold, "casual"));
    }
    // ----------------------------------------------------------
    /**
     * Tests meetsCriteria method (both are false).
     */
    public void testMeetsCriteriaFF() {
        Weather cold = new Weather(31, false, "snow");
        assertFalse(clothes.meetsCriteria(cold, "formal"));
    }
}