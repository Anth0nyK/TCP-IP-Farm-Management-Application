package weatherStation;


import java.util.Random;


public class Temperature {
    
    private double Temperature;
    
    public Temperature(){
        Temperature = 25.5;
    }
    
    
    
    public double getTemp(){
        Random random = new Random();
        int randomInt = random.nextInt(10);
        double randomDouble = random.nextDouble();
        
        if(randomInt >= 5){
            if(Temperature + randomDouble > 35){
                Temperature = 35;
            }
            else{
                Temperature = Temperature + randomDouble;
            }
        }
        else
        {
            if(Temperature - randomDouble < 7){
                Temperature = 7;
            }
            else{
                Temperature = Temperature - randomDouble;
            }
        }
        
        return Temperature;
    }
    
    
}
