package weatherStation;

import java.io.*;
import java.net.*;
import java.util.Date;


public class WeatherStationClient {
    public static String Client_Type = "station";
    public static String Station_ID = "0";
    
    public static void main(String[] args)  throws IOException {
        //Start the sensor
        Sensor theSensor = new Sensor();
        
        Socket server = new Socket("localhost", 9090);
        System.out.println("Connected to " + server.getInetAddress());
            
        DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
        DataInputStream inFromClient = new DataInputStream(server.getInputStream());


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Please insert the weather station code: ");
            Station_ID = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        outToServer.writeUTF(Client_Type + "\nsendWeatherStationStats\n" + Station_ID);
        
        String message = inFromClient.readUTF();
        // System.out.println("Message: " + message);
        
        switch (message) {
            case "RequestGPS":
                System.out.print("Please enter the GPS position:");
                String GPSPos = reader.readLine();
                
                System.out.print("Please enter the number of hectares of this land:");
                String HectaresNum = reader.readLine();

                System.out.print("Please enter the information about what this land is being used to:");
                String typePlant = reader.readLine();
                
                outToServer.writeUTF(GPSPos + ":" + HectaresNum + ":" + typePlant); // No break on purpose.
            case "NotActivated":
                System.out.print("This weatherstation has not been activated yet.\nPlease contact a server administrator.");
                return;
        }

        //Keep sending the server current weather data with an interval
        while(true) {
            //Get vales from sensor
            String theTemp = Double.toString(theSensor.getTemp());
            String thePerci = Double.toString(theSensor.getPerci());
            String theHumid = Double.toString(theSensor.getHumid());
            String theBaro = Double.toString(theSensor.getBaro());
            String theWind = Double.toString(theSensor.getWind());
            String timeStamp = Integer.toString(theSensor.getTimeStamp());
            
            String weatherStats = theTemp + "\n" + thePerci + "\n" + theHumid + "\n" + theBaro + "\n" + theWind + "\n" + timeStamp;
            outToServer.writeUTF(weatherStats);
            System.out.println(weatherStats.replace("\n", " - "));
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                System.out.println("got interrupted.");
                break;
            }
        }
        System.out.println("Connection to server has just closed");
    }
}
