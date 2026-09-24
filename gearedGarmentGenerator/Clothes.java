package gearedGarmentGenerator;
// -------------------------------------------------------------------------
/**
 *  Represents all clothing items.
 *  Creates the getter and setter for warmth, the getter and setter for 
 *  formality, the getter and setter for description, and the 
 *  meetsCriteria method.
 * 
 *  @author Cassidy McCahill
 *  @version Sep 21, 2026
 */
public class Clothes {
    //~ Fields ................................................................
    private boolean warmth;
    private String formality;
    private String description;
    // ----------------------------------------------------------
    /**
     * Create a new Clothes object.
     * @param warmth if suitable for warmth
     * @param formality the type of formality for specific occasion 
     * @param description the item description
     */
    //~ Constructors ..........................................................
    public Clothes(boolean warmth, String formality, String description) {
        this.warmth = warmth;
        this.formality = formality;
        this.description = description;
    }
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Gets the warmth.
     * 
     * @return the warmth
     */
    public boolean getWarmth() {
        return warmth;
    }
    // ----------------------------------------------------------
    /**
     * Sets the warmth.
     * 
     * @param warmth true if item is warm
     */
    public void setWarmth(boolean warmth) {
        this.warmth = warmth;
    }
    // ----------------------------------------------------------
    /**
     * Gets the formality.
     * 
     * @return the formality
     */
    public String getFormality() {
        return formality;
    }
    // ----------------------------------------------------------
    /**
     * Sets the formality.
     * 
     * @param formality the formality level
     */
    public void setFormality(String formality) {
        this.formality = formality;
    }
    // ----------------------------------------------------------
    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    // ----------------------------------------------------------
    /**
     * Sets the description.
     * 
     * @param description the items description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    // ----------------------------------------------------------
    /**
     * Determines if a clothing item meets the criteria or not.
     * @param weather the weather (temperature and precipitation)
     * @param neededFormality the needed type of formality for specific occasion 
     * @return true if meets criteria
     */
    public boolean meetsCriteria(Weather weather, String neededFormality) {
        
        if (!formality.equals(neededFormality)) {
            return false;
        }
        boolean isCold = weather.getTemp() < 32;
        if (warmth != isCold) {
            return false;
        }
        return true;
    }
}
