package weatherStation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


public class Sensor {
    private Temperature Temp;
    private Percipitation Perci;
    private Humidity Humid;
    private Barometric Baro;
    private WindForce Wind;
    
    public Sensor(){
        this.Temp = new Temperature();
        this.Perci = new Percipitation();
        this.Humid = new Humidity();
        this.Baro = new Barometric();
        this.Wind =  new WindForce();
    }
    
    public double getTemp(){
        return BigDecimal.valueOf(this.Temp.getTemp()).setScale(1,RoundingMode.HALF_UP).doubleValue();
    }
    
    public double getPerci(){
        return BigDecimal.valueOf(this.Perci.getPerci()).setScale(3,RoundingMode.HALF_UP).doubleValue();
    }
    
    public double getHumid(){
        return BigDecimal.valueOf(this.Humid.getHumid()).setScale(3,RoundingMode.HALF_UP).doubleValue();
    }
    
    public double getBaro(){
        return BigDecimal.valueOf(this.Baro.getBaro()).setScale(3,RoundingMode.HALF_UP).doubleValue();
    }
    
    public double getWind(){
        return BigDecimal.valueOf(this.Wind.getWind()).setScale(3,RoundingMode.HALF_UP).doubleValue();
    }
    
    public int getTimeStamp(){
        Date now = new Date();
        Long longTime = now.getTime()/1000;
        int timeStamp = longTime.intValue();
        return timeStamp;
    }
}