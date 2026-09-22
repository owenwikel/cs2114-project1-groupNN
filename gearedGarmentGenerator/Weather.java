package gearedGarmentGenerator;
public class Weather
{
    //~ Fields ................................................................
private int temp;
private boolean isPrecip;
private String precipType;
    //~ Constructors ..........................................................
public Weather(int temp, boolean isPrecip, String precipType) {
    this.temp = temp;
    this.isPrecip = isPrecip;
    this.precipType = precipType;
}
    //~Public  Methods ........................................................
public int getTemp() {
    return this.temp;
}

public boolean isPrecip() {
    return this.isPrecip;
}

public String getPrecipType() {
    return this.precipType;
}
}
