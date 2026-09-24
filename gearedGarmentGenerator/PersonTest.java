package gearedGarmentGenerator;
import junit.framework.TestCase;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//I was prompted by a LLM to look into ByteArrayInputStream for
//simulating user input, since testing these methods without it
//would have made the test cases unreasonably long.
/**
 * Tests for person.
 * 
 * @author Owen Wikel
 * @version Sep 21, 2026
 */
public class PersonTest extends TestCase {
    //~ Fields ................................................................
    private Person person;
    //~ Constructors ..........................................................
    /**
     * Setup for PersonTest class.
     * 
     * @author Owen Wikel
     * @version Sep 21, 2026
     */
    public void setUp() {
        person = new Person();
    }
    //~Public  Methods .......................................................
    /**
     * Tests that get name returns the correct name.
     */
    public void testGetName() {
        person.setName("Owen");
        assertEquals("Owen", person.getName());
    }
    /**
     * Tests that set name sets the persons name properly.
     */
    public void testSetName() {
        person.setName("Owen");
        assertEquals("Owen", person.getName());
    }
    /**
     * Tests that welcomeUser stores the correct temperature.
     */
    public void testWelcomeUser() {
        System.setIn(new ByteArrayInputStream("20\nno\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals(20, person.getWeather().getTemp());
    }
    
    /**
     * Tests setCSV stores a valid file.
     */
    public void testSetValid() {
        System.setIn(new ByteArrayInputStream(
            "sample_closet.csv\n".getBytes()));
        String result = person.setCSV();
        assertEquals("sample_closet.csv", result);
    }
    /**
     * Tests setCSV re-prompts after a bad filename.
     */
    public void testSetBadFile() {
        System.setIn(new ByteArrayInputStream(
            "nonexistent.csv\nsample_closet.csv\n".getBytes()));
        String result = person.setCSV();
        assertEquals("sample_closet.csv", result);
    }
    
    /**
     *  Tests readCSV correctly fills tops list.
     *  
     *  @throws FileNotFoundException should not occur with valid CSV file.
     */
    public void testReadCSV() throws FileNotFoundException {
        System.setIn(new ByteArrayInputStream(
                "sample_closet.csv\n".getBytes()));
        person.setCSV();
        person.readCSV();
        assertEquals(3, person.getTopsList().size());
    }
    
    /**
     * Tests meetUser creates a Person with the entered name.
     */
    public void testMeetUser() {
        System.setIn(new ByteArrayInputStream("Owen\n".getBytes()));
        Person newPerson = Person.meetUser();
        assertEquals("Owen", newPerson.getName());
    }
    
    /**
     * Tests welcomeUser re-prompts on a non-num temp.
     */
    public void testWelcomeUserNoNumTemp() {
        System.setIn(new ByteArrayInputStream(
            "abc\n20\nno\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals(20, person.getWeather().getTemp());
    }

    /**
     *  Tests welcomeUser reprompts with temp not in range
     */
    public void testWelcomeUserTempNotInRange() {
        System.setIn(new ByteArrayInputStream(
            "500\n20\nno\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals(20, person.getWeather().getTemp());
    }

    /** 
     * Tests welcomeUser then stores rain when precipitating and warm.
     */
    public void testWelcomeUserRain() {
        System.setIn(new ByteArrayInputStream(
            "50\nyes\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals("rain", person.getWeather().getPrecipType());
    }

    /**
     * Tests welcomeUser stores snow when precipitating and cold.
     */
    public void testWelcomeUserSnow() {
        System.setIn(new ByteArrayInputStream(
            "20\nyes\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals("snow", person.getWeather().getPrecipType());
    }

    /**
     * Tests welcomeUser reprompts on an invalid yes/no answer. 
     */
    public void testWelcomeUserInvalidPrecip() {
        System.setIn(new ByteArrayInputStream(
            "20\nmaybe\nno\ncasual\n".getBytes()));
        person.welcomeUser();
        assertFalse(person.getWeather().isPrecip());
    }
    
    /**
     * Tests welcomeUser reprompts on invalid formality answer.
     */
    public void testWelcomeUserInvalidFormality() {
        System.setIn(new ByteArrayInputStream(
            "20\nno\nbusiness\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals("CASUAL", person.getDesiredFormality().toUpperCase());
    }
    
    /**
     * Tests welcomeUser remprompts on a temperature below valid range
     */
    public void testWelcomeUserTooLowTemp() {
        System.setIn(new ByteArrayInputStream(
            "-100\n20\nno\ncasual\n".getBytes()));
        person.welcomeUser();
        assertEquals(20, person.getWeather().getTemp());
    }
    
    /**
     * Tests welcomeUser accepts formal as a valid formality
     */
    public void testWelcomeUserFormalFormality() {
        System.setIn(new ByteArrayInputStream(
            "20\nno\nformal\n".getBytes()));
        person.welcomeUser();
        assertEquals("FORMAL", person.getDesiredFormality().toUpperCase());
    }
    
    /**
     * Tests runProgram completes when given valid input and
     *  wardrobe with enough matching items.
     */
    public void testRunProgram() {
        System.setIn(new ByteArrayInputStream(
            "20\nno\ncasual\nsample_closet.csv\n".getBytes()));
        person.runProgram();
        assertEquals(20, person.getWeather().getTemp());
    }
    
    /**
     * Tests that createOufits creates outfits properly.
     */
    public void testCreateOutfits() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getTopsList().add(new Top(true, "casual", "Fleece pullover"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getBottomsList().add(new Bottom(true, "casual", "Jeans"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getShoesList().add(new Shoes(true, "casual", "Snow boots"));

        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");

        assertEquals(3, outfits.size());
        for (Outfit o : outfits) {
            assertNotNull(o.getTop());
            assertNotNull(o.getBottom());
            assertNotNull(o.getShoes());
        }
    }
    /**
     * Tests that createOuftits returns empty list when not enough clothes.
     */
    public void testCreateOutfitsNotEnoughClothes() {
        person.getShoesList().add(new Shoes(false, "casual", "Sandals"));
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));

        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");

        assertTrue(outfits.isEmpty());
    }
    
    /**
     * Tests createOufits builds outfits using jacket and full
     * matches when both are available.
     */
    public void testCreateOutfitsJacketAndFull() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getJacketsList().add(new Jacket(true, "casual", "Winter coat",
            "winter jacket"));
        person.getFullList().add(new Full(true, "casual", "Warm dress"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertFalse(outfits.isEmpty());
    }

    /**
     * Tests createOufits builds full only outfits
     * when no matching top bottom combination.
     */
    public void testCreateOutfitsFullOnly() {
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getFullList().add(new Full(true, "casual", "Warm dress"));
        person.getFullList().add(new Full(true, "casual", "Formal suit"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertFalse(outfits.isEmpty());
        for (Outfit o : outfits) {
            assertNotNull(o.getFull());
            assertNull(o.getTop());
            assertNull(o.getBottom());
        }
    }

    /**
     * Tests createOufits builds top+bottom outfits
     * when no full item exists.
     */
    public void testCreateOutfitsTopBottomOnly() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertFalse(outfits.isEmpty());
        for (Outfit o : outfits) {
            assertNull(o.getFull());
            assertNotNull(o.getTop());
            assertNotNull(o.getBottom());
        }
    }

    /**
     * Tests createOufits returns an empty list when only
     * available top fails to meet criteria.
     */
    public void testCreateOutfitsFailMeetsCriteria() {
        person.getTopsList().add(new Top(false, "formal",
            "Light summer shirt"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertTrue(outfits.isEmpty());
    }

    /**
     * Tests createOufits never assigns jacket to an outfit
     * when no jackets available.
     */
    public void testCreateOutfitsNoMatchingJackets() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        for (Outfit o : outfits) {
            assertNull(o.getJacket());
        }
    }
    
    /**
     * Tests createOufits returns empty list when only
     * bottom fails to meet criteria.
     */
    public void testCreateOutfitsBottomFails() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(false, "formal", "Light skirt"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertTrue(outfits.isEmpty());
    }
    
    /**
     * Tests createOufits never assigns jacket that fails
     * to meet criteria.
     */
    public void testCreateOutfitsJacketFails() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getJacketsList().add(new Jacket(false, "formal", "Rain jacket",
            "rain jacket")); 
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        for (Outfit o : outfits) {
            assertNull(o.getJacket()); 
        }
    }
    
    /**
     * Tests createOufits never assigns full item that fails
     * to meet criteria to an outfit.
     */
    public void testCreateOutfitsFullFails() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getFullList().add(new Full(false, "formal", "Summer dress")); 
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        for (Outfit o : outfits) {
            assertNull(o.getFull()); 
        }
    }
    
    /**
     * Tests createOufits still builds outfits using a full item
     * when tops exist but no matching bottoms are available.
     */
    public void testCreateOutfitsTopsNoBottoms() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getFullList().add(new Full(true, "casual", "Warm dress"));
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertFalse(outfits.isEmpty());
    }
    
    /**
     * Tests createOufits doesn't produce duplicate outfit
     * combinations when only one per.
     */
    public void testCreateOutfitsDuplicateDetection() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve")); 
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants")); 
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots")); 
        person.getJacketsList().add(new Jacket(true, "casual", "Winter coat",
            "winter jacket")); 
        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertEquals(1, outfits.size());
    }
    
    /**
     * Tests runProgram prints the not enough clothes message.
     */
    public void testRunProgramNotEnoughClothes() {
        System.setIn(new ByteArrayInputStream(
            "20\nno\ncasual\nempty_closet.csv\n".getBytes()));
        person.runProgram();
        assertTrue(person.getTopsList().isEmpty());
    }
    
    /**
     * Tests createOufits correctly identifies nonduplicates when
     * bottom and jacket are different.
     */
    public void testCreateOutfitsDuplicateCheckPartialMismatch() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getBottomsList().add(new Bottom(true, "casual", "Jeans"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getJacketsList().add(new Jacket(true, "casual", "Winter coat", "winter jacket"));
        person.getJacketsList().add(new Jacket(true, "casual", "Puffer coat", "winter jacket"));

        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");

        assertEquals(3, outfits.size());
    }
    
    /**
     * Tests createOufits correctly identifies nonduplicates when
     * top, bottom, and jacket are different between two outfits.
     */
    public void testCreateOutfitsDuplicateCheckMismatchPart() {
        person.getTopsList().add(new Top(true, "casual", "Warm long-sleeve"));
        person.getTopsList().add(new Top(true, "casual", "Fleece pullover"));
        person.getBottomsList().add(new Bottom(true, "casual", "Sweatpants"));
        person.getBottomsList().add(new Bottom(true, "casual", "Jeans"));
        person.getShoesList().add(new Shoes(true, "casual", "Winter boots"));
        person.getJacketsList().add(new Jacket(true, "casual", "Winter coat", "winter jacket"));
        person.getJacketsList().add(new Jacket(true, "casual", "Puffer coat", "winter jacket"));

        Weather coldWeather = new Weather(25, true, "snow");
        ArrayList<Outfit> outfits = person.createOutfits(coldWeather, "casual");
        assertEquals(3, outfits.size());
    }
}
