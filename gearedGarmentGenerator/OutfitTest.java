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
public class OutfitTest extends TestCase
{
    private Outfit outfit;
    private Top top;
    private Bottom bottom;
    private Shoes shoes;
    private Jacket jacket;
    private Full full;

    public void setUp() {
        top = new Top(false, "casual", "gray cotton t-shirt");
        bottom = new Bottom(false, "casual", "blue denim jeans");
        shoes = new Shoes(false, "casual", "white sneakers");
        jacket = new Jacket(true, "casual", "navy winter jacket", "winter");
        full = new Full(true, "formal", "black suit");

        outfit = new Outfit(top, bottom, shoes, jacket, null);
    }

    /**
     * Tests all getters and initial constructor logic.
     */
    public void testGettersAndConstructor() {
        assertEquals(top, outfit.getTop());
        assertEquals(bottom, outfit.getBottom());
        assertEquals(shoes, outfit.getShoes());
        assertEquals(jacket, outfit.getJacket());
        assertNull(outfit.getFull());

        // Test constructor behavior when full is provided
        Outfit fullOutfit = new Outfit(top, bottom, shoes, jacket, full);
        assertNull(fullOutfit.getTop());
        assertNull(fullOutfit.getBottom());
        assertEquals(full, fullOutfit.getFull());
    }

    /**
     * Tests setters for top, bottom, shoes, and jacket.
     */
    public void testSetters() {
        Top newTop = new Top(false, "formal", "white shirt");
        Bottom newBottom = new Bottom(false, "formal", "black slacks");
        Shoes newShoes = new Shoes(true, "formal", "dress shoes");
        Jacket newJacket = new Jacket(false, "casual", "rain jacket", "rain");

        outfit.setTop(newTop);
        outfit.setBottom(newBottom);
        outfit.setShoes(newShoes);
        outfit.setJacket(newJacket);

        assertEquals(newTop, outfit.getTop());
        assertEquals(newBottom, outfit.getBottom());
        assertEquals(newShoes, outfit.getShoes());
        assertEquals(newJacket, outfit.getJacket());
    }

    /**
     * Tests setting full clears top and bottom.
     */
    public void testSetFullClearsTopAndBottom() {
        outfit.setFull(full);

        assertEquals(full, outfit.getFull());
        assertNull(outfit.getTop());
        assertNull(outfit.getBottom());
    }

    /**
     * Tests displayOutfit formatting for both standard and full outfits.
     */
    public void testDisplayOutfit() {
        String expectedStandard = "Top: gray cotton t-shirt\n"
                + "Bottom: blue denim jeans\n"
                + "Jacket: navy winter jacket\n"
                + "Shoes: white sneakers\n";
        assertEquals(expectedStandard, outfit.displayOutfit());

        Outfit fullOutfit = new Outfit(null, null, shoes, null, full);
        String expectedFull = "Full: black suit\n"
                + "Shoes: white sneakers\n";
        assertEquals(expectedFull, fullOutfit.displayOutfit());
    }

}
