package gearedGarmentGenerator;
// -------------------------------------------------------------------------
/**
 *  Represents top clothing items (T-shirt, Tank, etc.),
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class Top extends Clothes {
    //~ Fields ................................................................

    //~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Creates a new Top object.
     * 
     * @param warmth if suitable for warmth
     * @param formality the type of formality for specific occasion 
     * @param description the item description
     */
    public Top(boolean warmth, String formality, String description) {
        super(warmth, formality, description);
    }
    //~Public  Methods ........................................................

}
