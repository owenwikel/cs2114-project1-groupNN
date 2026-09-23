package gearedGarmentGenerator;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Represents the user. Holds the user's name, their wardrobe grouped by type,
 * the weather and formality they have provided, and the CSV file their
 * wardrobe was read from.
 * 
 *  @author Owen Wikel
 *  @version Sep 21, 2026
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
   private Scanner scanner;
 

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
     * Runs the full program. collects weather/formality, reads wardrobe csv
     * , builds outfits, and displays the results.
     */
    public void runProgram() {
        this.welcomeUser();
        this.setCSV();
        try {
            this.readCSV();
        } catch (FileNotFoundException e) {
            //Not directly testable. setCSV() already
            // confirms the file exists before readCSV() is called, so this
            // branch would only go if the file were deleted between
            // those two calls.
            System.out.println("File could not be read,"
                + " Please check and try again.");
            return;
        }
        ArrayList<Outfit> outfits = this.createOutfits(this.weather,
            this.desiredFormality);

        if (outfits.isEmpty()) {
            System.out.println("Add more items. Not enough"
                + " clothes for conditions.");
        } else {
            for (int i = 0; i < outfits.size(); i++) {
                System.out.println("Outfit " + (i + 1) + ":");
                System.out.println(outfits.get(i).displayOutfit());
            }
        }
   }
    
    
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
        user.setName(user.getScanner().nextLine());
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
        
        System.out.println("Hello " + this.getName() + " welcome to the Geared"
            + " Garment Generator!"
            + " I will suggest an outfit based on todays weather, "
            + " and what you have planned. ");
        while (-70 > temp || temp > 120) {
            System.out.print("What temperature is it in your location? ");
            try {
                temp = getScanner().nextInt();
                getScanner().nextLine(); //Throws away leftover new line
                if (-70 > temp || temp > 120) {
                    System.out.println("Please enter a valid temperature. ");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid temperature. ");
                getScanner().nextLine(); //Throws away bad input
            }
        }
        while (!validPrecip) {
            System.out.print("Is there precipitation? ");
            isTherePrecip = getScanner().nextLine();
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
            formality = getScanner().nextLine();
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
        while(!validCSV) {
            System.out.print("Paste your CSV link containing your wardrobe. ");
            try {
            file = getScanner().nextLine();
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
    
    /**
     * Creates the users outfits to choose from
     * based on daily criteria.
     * 
     * @param weather the weather
     * @param desiredFormality the formality of the day.
     * @return outfits.
     */
    public ArrayList<Outfit> createOutfits(Weather weather, String desiredFormality) {
        ArrayList<Outfit> outfits = new ArrayList<Outfit>();
        //LLM recommended using this object type for implementation
        Random random = new Random();
        ArrayList<Top> matchingTops = new ArrayList<Top>();
        for (Top t : topsList) {
            if (t.meetsCriteria(weather, desiredFormality)) matchingTops.add(t);
        }
        ArrayList<Bottom> matchingBottoms = new ArrayList<Bottom>();
        for (Bottom b : bottomsList) {
            if (b.meetsCriteria(weather, desiredFormality)) matchingBottoms.add(b);
        }
        ArrayList<Shoes> matchingShoes = new ArrayList<Shoes>();
        for (Shoes s : shoesList) {
            if (s.meetsCriteria(weather, desiredFormality)) matchingShoes.add(s);
        }
        ArrayList<Jacket> matchingJackets = new ArrayList<Jacket>();
        for (Jacket j : jacketsList) {
            if (j.meetsCriteria(weather, desiredFormality)) matchingJackets.add(j);
        }
        ArrayList<Full> matchingFulls = new ArrayList<Full>();
        for (Full f : fullList) {
            if (f.meetsCriteria(weather, desiredFormality)) matchingFulls.add(f);
        }
        boolean hasTopBottom = !matchingTops.isEmpty() && !matchingBottoms.isEmpty();
        boolean hasFull = !matchingFulls.isEmpty();
        if (matchingShoes.isEmpty() || (!hasTopBottom && !hasFull)) {
            //checking if possible or not to make outfit
            return outfits;
        }
        int maxAttempts = 50;
        int attempts = 0;
        while (outfits.size() < 3 && attempts < maxAttempts) {
            attempts++;
            Outfit outfit = new Outfit();
            // From LLM (next boolean returns either true or false 50/50)
            boolean useFull = hasFull && (!hasTopBottom || random.nextBoolean());
            Top chosenTop = null;
            Bottom chosenBottom = null;
            Full chosenFull = null;
            Jacket chosenJacket = null;
            if (useFull) {
                //random.netInt will return random int from 0 to the bound
                chosenFull = matchingFulls.get(random.nextInt(matchingFulls.size()));
                outfit.setFull(chosenFull);
            } else {
                chosenTop = matchingTops.get(random.nextInt(matchingTops.size()));
                chosenBottom = matchingBottoms.get(random.nextInt(matchingBottoms.size()));
                outfit.setTop(chosenTop);
                outfit.setBottom(chosenBottom);
            }
            //Shoes defined here because they are never optional
            // you could have an outfit with no Top, Bottom, full, or jacket
            // but you must have shoes.
            Shoes chosenShoes = matchingShoes.get(random.nextInt(matchingShoes.size()));
            outfit.setShoes(chosenShoes);
            if (!matchingJackets.isEmpty()) {
                chosenJacket = matchingJackets.get(random.nextInt(matchingJackets.size()));
                outfit.setJacket(chosenJacket);
            }
            boolean isDuplicate = false;
            // making sure no repeat outfits.
            for (Outfit existing : outfits) {
                if (existing.getFull() == chosenFull
                    && existing.getTop() == chosenTop
                    && existing.getBottom() == chosenBottom
                    && existing.getShoes() == chosenShoes
                    && existing.getJacket() == chosenJacket) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                outfits.add(outfit);
            }
        }
        return outfits;
    }
    
    /**
     * Added for testing
     */
    public ArrayList<Bottom> getBottomsList() {
        return this.bottomsList;
    }

    /**
     * Added for testing.
     */
    public ArrayList<Shoes> getShoesList() {
        return this.shoesList;
    }
    
    /**
     * Added for testing.
     */
    public ArrayList<Jacket> getJacketsList() {
        return this.jacketsList;
    }

    /**
     * Added for testing.
     */
    public ArrayList<Full> getFullList() {
        return this.fullList;
    }
    /**
     * Returns the shared scanner.
     * 
     * @return shared scanner.
     */
    private Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
    

}
