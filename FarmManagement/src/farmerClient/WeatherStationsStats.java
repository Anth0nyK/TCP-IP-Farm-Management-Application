/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerClient;

/**
 *
 * @author nunos
 */
public class WeatherStationsStats {
    private String GPSLocation;
    private double temperature;
    private int humidity;
    private double barometricPressure;
    private double windForce;
    private int precipitation;
    private int timeStamp;
    private String hectares;
    private String usedFor;
            
    public WeatherStationsStats(String GPSLocation, double temperature, int humidity, double barometricPressure, double windForce, int precipitation, int timeStamp, String hectares, String usedFor) {
        this.GPSLocation = GPSLocation;
        this.temperature = temperature;
        this.humidity = humidity;
        this.barometricPressure = barometricPressure;
        this.windForce = windForce;
        this.precipitation = precipitation;
        this.timeStamp = timeStamp;
        this.hectares = hectares;
        this.usedFor = usedFor;
    }
    
    public String getGPSLocation() { return this.GPSLocation; }
    
    public double getTemperature() { return this.temperature; }
    
    public int getHumidity() { return this.humidity; }
    
    public double getBarometricPressure() { return this.barometricPressure; }
    
    public double getWindForce() { return this.windForce; }
    
    public int getPrecipitation() { return this.precipitation; }
    
    public int getTimeStamp() { return this.timeStamp; }
    
    public String getHectares() { return this.hectares; }
    
    public String getUsedFor() { return this.usedFor; }
}
