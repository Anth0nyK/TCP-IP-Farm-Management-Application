package weatherStation;

import java.util.Random;



public class WindForce {
    
    private double WindForce;
    
    public WindForce(){
        WindForce = 15;
    }
    
    
    public double getWind(){
        Random random = new Random();
        int randomInt = random.nextInt(10);
        double randomDouble = random.nextDouble();
        
        if (randomInt >= 5){
            WindForce = WindForce + randomDouble;

        }
        else{
            if(WindForce - randomDouble < 0){
                WindForce = 0;
            }
            else{
                WindForce = WindForce - randomDouble;
            }
        }
        
        return WindForce;
    }
    
}
