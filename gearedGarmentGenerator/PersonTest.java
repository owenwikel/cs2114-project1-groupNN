package gearedGarmentGenerator;
import junit.framework.TestCase;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
//I was prompted by a LLM to look into ByteArrayInputStream for
//simulating user input, since testing these methods without it
//would have made the test cases unreasonably long.
/**
 * Tests for person.
 */
public class PersonTest extends TestCase {
    //~ Fields ................................................................
    private Person person;
    //~ Constructors ..........................................................
    /**
     * Setup for PersonTest class.
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
}
