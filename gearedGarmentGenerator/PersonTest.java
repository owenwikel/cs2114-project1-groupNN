package gearedGarmentGenerator;
import junit.framework.TestCase;
import java.io.ByteArrayInputStream;
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
     */
    public void testReadCSV() {
        System.setIn(new ByteArrayInputStream("sample_closet.csv\n".getBytes()));
        person.setCSV();
        person.readCSV();
        assertEquals(3, person.getTopsList().size());
    }
}
