package gearedGarmentGenerator;
// -------------------------------------------------------------------------
/**
 *  Represents bottom clothing items (Pants, Shorts, etc.).
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class Bottom extends Clothes {
    //~ Fields ................................................................

    //~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Bottom object.
     * 
     * @param warmth if suitable for warmth
     * @param formality the type of formality for specific occasion 
     * @param description the item description
     */
    public Bottom(boolean warmth, String formality, String description) {
        super(warmth, formality, description);
    }
    //~Public  Methods ........................................................

}
