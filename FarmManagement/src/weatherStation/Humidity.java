package weatherStation;

import java.util.Random;


public class Humidity {
    
    private double Humidity;
    
    public Humidity(){
        Humidity = 50;
    }
    
    
    public double getHumid(){
        Random random = new Random();
        int randomInt = random.nextInt(10);
        double randomDouble = random.nextDouble();
        
        if (randomInt >= 5){
            if(Humidity + randomDouble > 100){
                Humidity = 100;
            }
            else{
                Humidity = Humidity + randomDouble;
            }
        }
        else{
            if(Humidity - randomDouble < 0){
                Humidity = 0;
            }
            else{
                Humidity = Humidity - randomDouble;
            }
        }
        
        
        return Humidity;
    }
    
}
