package weatherStation;

import java.util.Random;


public class Percipitation {
 
    private double Percipitation;
    
    public Percipitation(){
        Percipitation = 0;
    }
    
    
    public double getPerci(){
        Random random = new Random();
        int randomInt = random.nextInt(10);
        double randomDouble = random.nextDouble();
        
        if(randomInt >= 5){
            Percipitation = Percipitation + randomDouble;
        }
        else
        {
            if(Percipitation - randomDouble > 0){
                Percipitation = Percipitation - randomDouble;
            }
            else{
                Percipitation = 0;
            }
        }
        
        return Percipitation;
    }
    
}
