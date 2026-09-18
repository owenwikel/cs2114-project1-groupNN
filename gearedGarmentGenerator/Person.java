package gearedGarmentGenerator;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Represents the user. Holds the user's name, their wardrobe grouped by type,
 * the weather and formality they have provided, and the CSV file their
 * wardrobe was read from.
 */
public class Person {
    //~ Fields ................................................................
   private String name;
   private ArrayList<Top> topsList;
   private ArrayList<Bottom> bottomsList;
   private ArrayList <Shoes> shoesList;
   private ArrayList<Full> fullList;
   private ArrayList<Jacket> jacketsList;
   private Weather weather;
   private String desiredFormality;
   private String file;
   private ArrayList<String> failedList;
 

    //~ Constructors ..........................................................
   /**
    * Constructor for person.
    */
    public Person() {
        this.name = "";
        topsList = new ArrayList<Top>();
        bottomsList = new ArrayList<Bottom>();
        shoesList = new ArrayList<Shoes>();
        fullList = new ArrayList<Full>();
        jacketsList = new ArrayList<Jacket>();
        failedList = new ArrayList<String>();
    }
    //~Public  Methods ........................................................
   /**
    * Returns this persons name.
    * 
    * @return the users name.
    */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets this person's name.
     * 
     * @param name the new name to assign.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Creates the user object and allows user to input their name.
     * 
     * @return the user object.
     */
    public static Person meetUser() {
        Person user = new Person();
        System.out.print("Hello what's your name?");
        Scanner scanner = new Scanner(System.in);
        user.setName(scanner.nextLine());
        return user;
    }
    
    /**
     * Greets the user and collects today's weather and desired formality.
     * Re-prompts on invalid input until given a valid temperature, 
     * precipitation, and formality are given.
     * Stores the results in the weather and desiredFormality fields.
     */
    public void welcomeUser() {
        int temp = 200;
        String isTherePrecip = "";
        String precipType = "";
        String formality = "";
        boolean isPrecip = false;
        boolean validPrecip = false;
        boolean validFormality = false;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello " + this.getName() + " welcome to the Geared"
            + " Garment Generator!"
            + " I will suggest an outfit based on todays weather, "
            + " and what you have planned. ");
        while (-70 > temp || temp > 120) {
            System.out.print("What temperature is it in your location? ");
            try {
                temp = scanner.nextInt();
                scanner.nextLine(); //Throws away leftover new line
                if (-70 > temp || temp > 120) {
                    System.out.println("Please enter a valid temperature. ");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid temperature. ");
                scanner.nextLine(); //Throws away bad input
            }
        }
        while (!validPrecip) {
            System.out.print("Is there precipitation? ");
            isTherePrecip = scanner.nextLine();
            if (isTherePrecip.toUpperCase().equals("YES")) {
                validPrecip = true;
                isPrecip = true;
                if (temp <= 32) {
                    precipType = "snow";
                }
                else {
                    precipType = "rain";
                }
            }
            
            else if(isTherePrecip.toUpperCase().equals("NO")) {
                validPrecip = true;
                isPrecip = false;
            }
            else {
                System.out.println("Please enter yes or no. ");
            }
        }
        while (!validFormality) {
            System.out.print("Do you have any formal events today? ");
            formality = scanner.nextLine();
            if (formality.toUpperCase().equals("CASUAL") ||
                formality.toUpperCase().equals("FORMAL")) {
                validFormality = true;   
            }
            else {
                System.out.println("Please enter a valid formality type. ");
            }
        }
        weather = new Weather(temp, isPrecip, precipType);
        desiredFormality = formality; 
    }
    
    /**
     * Prompts the user for the path to their wardrobe CSV, then verifies
     * that it can be opened, and re-prompts upon failure.
     * Stores the valid file path in file field.
     * 
     * @return the valid CSV file
     */
    public String setCSV(){
        boolean validCSV = false;
        Scanner scanner = new Scanner(System.in);
        while(!validCSV) {
            System.out.print("Paste your CSV link containing your wardrobe. ");
            try {
            file = scanner.nextLine();
            File csvFile = new File(file);
            Scanner fileScanner = new Scanner(csvFile);
            fileScanner.close();
            validCSV = true;
            }
            catch(FileNotFoundException e) {
                System.out.print("Please enter a valid CSV link ");
            }
        }
        return file;
    }
    
    /**
     * Reads the wardrobe CSV file and adds each row's item to the matching 
     * list based on category. If a row has an unrecognized category it is
     * skipped and it's name is collected in a failedList.
     * 
     * @throws FileNotFoundException should not occur if setCSV() was called
     * first.
     */
    public void readCSV() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        scanner.nextLine(); //Skip headers
        while (scanner.hasNextLine()) {
            String entry = scanner.nextLine();
            String[] pieces = entry.split(",");
            if (entry.toUpperCase().startsWith("TOP")) {
                Top top = new Top((Boolean.parseBoolean(pieces[1])), pieces[2]
                    , pieces[3]);
                topsList.add(top);
            }
            else if (entry.toUpperCase().startsWith("BOTTOM")) {
                Bottom bottom = new Bottom((Boolean.parseBoolean(pieces[1])),
                    pieces[2], pieces [3]);
                bottomsList.add(bottom);
            }
            else if (entry.toUpperCase().startsWith("JACKET")) {
                Jacket jacket = new Jacket((Boolean.parseBoolean(pieces[1])),
                    pieces[2], pieces[3], pieces[4]);
                jacketsList.add(jacket);
            }
            else if (entry.toUpperCase().startsWith("FULL")) {
                Full full = new Full((Boolean.parseBoolean(pieces[1])), pieces[2],
                    pieces[3]);
                fullList.add(full);
            }
            else if (entry.toUpperCase().startsWith("SHOES")) {
                Shoes shoes = new Shoes((Boolean.parseBoolean(pieces[1])), pieces[2],
                    pieces[3]);
                shoesList.add(shoes);
            }
            else {
                failedList.add(pieces[0]);
            }
        }
        System.out.println("The following items could not be added: " +
            failedList);
    }

    /**
     * Added to be used in test cases.
     * returns the weather.
     * 
     * @return the weather.
     */
    public Weather getWeather() {
        return this.weather;
    }
    
    /**
     * Added to be used in test cases.
     * returns list of tops.
     * 
     * @return tops list.
     */
    public ArrayList<Top> getTopsList() {
        return this.topsList;
    }
    
    /**
     * Added to be used in test cases.
     * returns the desired formality.
     * 
     * @return the desired formality.
     */
    public String getDesiredFormality() {
        return this.desiredFormality;
    }
}
