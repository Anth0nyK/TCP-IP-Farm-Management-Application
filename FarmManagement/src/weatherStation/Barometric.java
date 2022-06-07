package weatherStation;

import java.util.Random;



public class Barometric {
    
    private double Barometric;
    
    public Barometric(){
        Barometric = 1000;
    }
    
    public double getBaro(){
        Random random = new Random();
        int randomInt = random.nextInt(10);
        double randomDouble = random.nextDouble();
        
        if (randomInt >= 5){
            if(Barometric + randomDouble > 1050){
                Barometric = 1050;
            }
            else{
                Barometric = Barometric + randomDouble;
            }
        }
        else{
            if(Barometric - randomDouble < 990){
                Barometric = 990;
            }
            else{
                Barometric = Barometric - randomDouble;
            }
        }
        
        
        return Barometric;
    }
    
}
