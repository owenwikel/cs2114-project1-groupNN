package gearedGarmentGenerator;
import java.util.ArrayList;
public class Person {
    //~ Fields ................................................................
   private String name;
   private ArrayList<Top> topsList;
   private ArrayList<Bottom> bottomsList = new ArrayList<Bottom>();
   private ArrayList <Shoes> shoesList = new ArrayList<Shoes>();
   private ArrayList<Full> fullList = new ArrayList<Full>();
   // private ArrayList<Jacket> jacketsList = new ArrayList<Jacket>();
   // private String name;

    //~ Constructors ..........................................................
    public Person(String name) {
        name = this.name
        topsList = new ArrayList<Top>();
    }
    //~Public  Methods ........................................................

    public String getName(String name) {
        return this.name;
    }
    
    public void welcomeUser() {
        
    }

}
