package gearedGarmentGenerator;
/**
 * // -------------------------------------------------------------------------
/**
 *  Represents the weather conditions by the user
 * 
 *  @author sydneyyang
 *  @version Sep 22, 2026
 */
public class Weather
{
    //~ Fields ................................................................
private int temp;
private boolean isPrecip;
private String precipType;
    //~ Constructors ..........................................................
/**
 * Creates a new Weather object with specified conditions.
 * 
 * @param temp temperature in degrees
 * @param isPrecip true if precipitation is occurring
 * @param precipType type of precipitation (e.g., rain, snow)
 */
public Weather(int temp, boolean isPrecip, String precipType) {
    this.temp = temp;
    this.isPrecip = isPrecip;
    this.precipType = precipType;
}
    //~Public  Methods ........................................................
/**
 * Gets the temperature.
 * 
 * @return the temperature
 */
public int getTemp() {
    return this.temp;
}
/**
 * Sets the temperature
 * @param temp
 */
public void setTemp(int temp) {
    this.temp = temp;
}
/**
 * Checks if precipitation is true
 * 
 * @return true if precipitation is true 
 */
public boolean isPrecip() {
    return this.isPrecip;
}
/**
 * Sets whether it is precipitating
 * 
 * @param value true if precipitating 
 */
public void setIsPrecip(boolean value) {
    this.isPrecip = value;
}
/**
 * Gets the type of precipitation 
 * 
 * @return precipitation type 
 */
public String getPrecipType() {
    return this.precipType;
}
/**
 * Sets the precipitation type
 * 
 * @param type the precipitation to set
 */
public void setPrecipType(String type) {
    this.precipType = type;
}
}
