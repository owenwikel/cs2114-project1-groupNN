package gearedGarmentGenerator;

public class Outfit
{
    //~ Fields ................................................................
    private Top top
    private Bottom bottom
    private Shoes shoes
    private Jacket jacket
    private Full full

    //~ Constructors ..........................................................
    public Outfit(Top top, Bottom bottom, Shoes shoes, Jacket jacket, Full full) {
        this.top = top;
    }
    //~Public  Methods ........................................................
    public Top getTop() {
        return top;
    }
    public void setTop(Top top) {
        this.top = top;
    }
    public Bottom getBottom() {
        return bottom;
    }
    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }
    public Shoes getShoes() {
        return shoes;
    }
    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }
    public Jacket getJacket() {
        return jacket;
    }
    public void setJacket(Jacket jacket) {
        this.jacket = jacket;
    }
    public Full getFull() {
        return full;
    }
    public void setFull(Full full) {
        this.full = full;
    }
    public displayOutfit() {
        
    }
