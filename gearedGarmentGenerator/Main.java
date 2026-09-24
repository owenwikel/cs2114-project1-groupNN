package gearedGarmentGenerator;
/**
 * Starts the Geared Garment Generator.
 * 
 * @author Owen Wikel (wikelog)
 * @version 2026.09.24
 */
public class Main {
    //~ Fields ................................................................

    //~ Constructors ..........................................................
        /**
         * Creates a user and runs the oufit generator.
         * 
         * @param args unused
         */
    public static void main(String[] args) {
        Person user = Person.meetUser();
        user.runProgram();
    }
    //~Public  Methods ........................................................

}
