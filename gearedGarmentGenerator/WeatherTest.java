package gearedGarmentGenerator;
import junit.framework.TestCase; 
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests for Weather class
 * 
 *  @author sydneyyang
 *  @version Sep 22, 2026
 */
public class WeatherTest extends TestCase{
    private Weather weather;
   /**
    * sets up the initial condition 
    */
    public void setUp() {
        weather = new Weather(72, true, "rain");
    }
    /**
     * Tests temperature getter and setter
     */
    public void testGetAndSetTempe() {
        assertEquals(72, weather.getTemp());
        weather.setTemp(30);
        assertEquals(30,weather.getTemp());
    }
    /**
     * Tests precipitation getter and setter
     */
    public void testIsAndSetIsPrecip() {
        assertTrue(weather.isPrecip());
        weather.setIsPrecip(false);
        assertFalse(weather.isPrecip());
    }
    /**
     * Tests precipitation type getter and setter
     */
    public void testGetAndSetPrecipitation() {
        assertEquals("rain", weather.getPrecipType());
        weather.setPrecipType("snow");
        assertEquals("snow", weather.getPrecipType());
    }

}
