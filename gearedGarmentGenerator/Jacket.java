package gearedGarmentGenerator;
// -------------------------------------------------------------------------
/**
 *  Represents jacket clothing item.
 *  Creates getter and setter for JacketType.
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class Jacket extends Clothes {
    //~ Fields ................................................................
    private String jacketType;
    // ----------------------------------------------------------
    /**
     * Create a new Jacket object.
     * @param warmth if suitable for warmth
     * @param formality the type of formality for specific occasion 
     * @param description the item description
     * @param jacketType the type of jacket depending on weather
     */
    //~ Constructors ..........................................................
    public Jacket(boolean warmth, String formality, String description,
        String jacketType) {
        super(warmth, formality, description);
        this.jacketType = jacketType;
    }
    // ----------------------------------------------------------
    /**
     * Gets the jacket type.
     * 
     * @return the jacketType
     */
    //~Public  Methods ........................................................
    public String getJacketType() {
        return jacketType;
    }
    // ----------------------------------------------------------
    /**
     * Sets the jacket type.
     * 
     * @param jacketType the type of jacket
     */
    public void setJacketType (String jacketType) {
        this.jacketType = jacketType;
    }
}
