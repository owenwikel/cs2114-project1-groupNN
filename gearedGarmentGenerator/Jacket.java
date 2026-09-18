package gearedGarmentGenerator;
public class Jacket extends Clothes {
    //~ Fields ................................................................
    private String jacketType;
    //~ Constructors ..........................................................
    public Jacket(boolean warmth, String formality, String description,
        String jacketType) {
        super(warmth, formality, description);
        this.jacketType = jacketType;
    }
    //~Public  Methods ........................................................

}
