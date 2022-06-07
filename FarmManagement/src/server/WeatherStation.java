package server;

public class WeatherStation  {
    private int StationID;
    private String GPS;
    private double Temperature;
    private double Percipitation;
    private double Humidity;
    private double Barometric;
    private double WindForce;
    private int timeStamp;
        
    public WeatherStation(int _StationID, double _Temp, double _Percip, double _Humid, double _Baro, double _Wind, int _timeStamp){
        StationID = _StationID;
        Temperature = _Temp;
        Percipitation = _Percip;
        Humidity = _Humid;
        Barometric = _Baro;
        WindForce = _Wind;
        timeStamp = _timeStamp;
    }
    
    public int getID(){
        return StationID;
    }

    public double getTemp(){
        return Temperature;
    }
    
    
    public double getPerci(){
        return Percipitation;
    }
    
    public double getHumid(){
        return Humidity;
    }

    public double getBaro(){
        return Barometric;
    }
    
    public double getWind(){
        return WindForce;
    }
    
    public int getTimeStamp(){
        return timeStamp;
    }
}