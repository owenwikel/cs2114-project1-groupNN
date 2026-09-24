package gearedGarmentGenerator;
/**
 * // -------------------------------------------------------------------------
/**
 *  Complete outfit consisting of the users individual clothing pieces
 * 
 *  @author sydneyyang
 *  @version Sep 22, 2026
 */
public class Outfit
{
    //~ Fields ................................................................
    private Top top;
    private Bottom bottom;
    private Shoes shoes;
    private Jacket jacket;
    private Full full;

    //~ Constructors ..........................................................
    /*
     * // ----------------------------------------------------------
    /**
     * Create a new Outfit object.
     * 
     * @param top
     * @param bottom
     * @param shoes
     * @param jacket
     * @param full
     */
    public Outfit(Top top, Bottom bottom, Shoes shoes, Jacket jacket, Full full) {
        this.shoes = shoes;
        this.jacket = jacket;
        if (full != null) {
            this.full = full;
            this.top = null;
            this.bottom = null;
        }
        else {
            this.top = top;
            this.bottom = bottom;
            this.full = null;
        }
        
    }
    //~Public  Methods ........................................................
    /**
     * Gets the top item
     * 
     * @return top
     */
    public Top getTop() {
        return top;
    }
    /**
     * Sets the top to the outfit, if full is set then null
     * 
     * @param top top item to set
     */
    public void setTop(Top top) {
        this.top = top;
    }
    /**
     * Gets the bottom item
     * 
     * @return bottom
     */
    public Bottom getBottom() {
        return bottom;
    }
    /**
     * Sets the bottom item, if full is set then null
     * 
     * @param bottom bottom item to set
     */
    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }
    /**
     * Gets the shoes
     * 
     * @return shoes
     */
    public Shoes getShoes() {
        return shoes;
    }
    /**
     * Sets the shoes 
     * 
     * @param shoes shoes item to set 
     */
    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }
    /**
     * Gets the jacket item
     * 
     * @return jacket
     */
    public Jacket getJacket() {
        return jacket;
    }
    /**
     * Sets the jacket 
     * 
     * @param jacket jacket item to set 
     */
    public void setJacket(Jacket jacket) {
        this.jacket = jacket;
    }
    /**
     * Gets the full body item 
     * 
     * @return top
     */
    public Full getFull() {
        return full;
    }
    /**
     * Sets the full to the outfit, if top and bottom are set then null
     * 
     * @param full full body item to set 
     */
    public void setFull(Full full) {
        this.full = full;
        if (full != null) {
            this.top = null;
            this.bottom = null;
        }
    }
    /**
     * Builds string summary of the outfit 
     * 
     * @return outfit summary string 
     */
    public String displayOutfit() {
        StringBuilder builder = new StringBuilder();
        if (full != null) {
            builder.append("Full: ").append(full.getDescription().append("\n"));
        }
        else {
            if(top != null) {
                builder.append("Top: ").append(top.getDescription().append("\n"));
            }
            if(bottom != null) {
                builder.append("Bottom: ").append(bottom.getDescription().append("\n"));
            }
        }
        if (jacket != null) {
            builder.append("Jacket: ").append(jacket.getDescription().append("\n"));
        }
        if (shoes != null) {
            builder.append("Shoes: ").append(shoes.getDescription().append("\n"));
        }
        return builder.toString();
    }
}
