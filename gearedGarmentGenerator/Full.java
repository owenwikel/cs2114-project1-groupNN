package gearedGarmentGenerator;
/**
 * // -------------------------------------------------------------------------
/**
 * Represents full-body clothing item (e.g., suit, dress)
 * 
 *  @author sydneyyang
 *  @version Sep 22, 2026
 */
public class Full extends Clothes {
    
    //~ Constructors ..........................................................
    /**
     * Creates a new Full clothing item 
     * 
     * @param warmth true if suitable for weather
     * @param formality type of formality for the occasion
     * @param description item description
     */
    public Full(boolean warmth, String formality, String description) {
        super(warmth, formality, description);
    }
    
}
