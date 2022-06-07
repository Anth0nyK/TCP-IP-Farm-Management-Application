package server;

import java.net.*; 
import java.io.*;
import java.util.*;


public class ServerHandler implements Runnable {
    Socket client;
    DataOutputStream outToClient;
    DataInputStream inFromClient;
    
    private final List<WeatherStation> theList;
    private final List<Integer> ConnectedFarmerClients;
    private List<Integer> connectedClients = new ArrayList<Integer>();
    
    //Constructor
    public ServerHandler(Socket theClient, List<WeatherStation> theList, List<Integer> ConnectedFarmerClients) throws IOException {
        client = theClient;
        inFromClient = new DataInputStream(client.getInputStream());
        outToClient = new DataOutputStream(client.getOutputStream());
        this.ConnectedFarmerClients = ConnectedFarmerClients;
        this.theList = theList;
        
    }

    @Override
    public void run() {
        try{
            //get message from client
            String type = inFromClient.readUTF();
            String[] clientParameters = type.split("\n");
            if (clientParameters.length >= 2) { // has at least client type and method to be called
                switch (clientParameters[0]) {
                    case "user":
                        userService(clientParameters);
                        break;
                    case "station":
                        stationService(clientParameters);
                        break;
                    default:
                        System.out.println("A not know device has just disconnected");
                        return;
                }
                System.out.println("A " + clientParameters[0] + " has just disconnected");  
                return;
            }
            // System.out.println("A not know device has just disconnected");    
        }
        catch(IOException e) {}
    }
    
    public void userService(String[] clientParameters) {
        System.out.println("A user has just connected");
        
        String method = clientParameters[1];
        
        switch (method) {
            case "login":
                login(clientParameters);
                break;
            case "registerUser":
                registerUser(clientParameters);
                break;
            case "getWeatherStationsStats":
                // String weatherStationId = "1";
                getWeatherStationsStats(clientParameters);
                break;
            /* case "getNumConnectedClients":
                getNumConnectedClients();
                break; */
            case "getWeatherStationData":
                getWeatherStationData(clientParameters);
                break;
        }
    }
    
    public void login(String[] clientParameters) {
        try {
            String username = clientParameters[2];
            String password = clientParameters[3];
            int userId = FileHandler.getInstance().verifyLoginCredentials(username, password, false);
            outToClient.writeUTF(String.valueOf(userId)); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void registerUser(String[] clientParameters) {
        try {
            String username = clientParameters[2];
            String password = clientParameters[3];
            
            TreeMap<Integer, List<String>> usersLst = FileHandler.getInstance().getAllUsers(false);
            for (Map.Entry<Integer, List<String>> user : usersLst.entrySet()) {
                if ((user.getValue().get(2)).equals(username)) { // If username already exists
                    outToClient.writeUTF("-1");
                    return;
                }
            }
            
            int userId = 0;
            if (usersLst.size() > 0) {
                userId = Integer.parseInt(usersLst.lastEntry().getValue().get(0)) + 1;
            }
            String code = FileHandler.getInstance().registerAccount(userId, username, password, false);
            outToClient.writeUTF(code); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
        
        
    public void getWeatherStationsStats(String[] clientParameters) {
        int clientId = Integer.parseInt(clientParameters[2]);

        synchronized(ConnectedFarmerClients) {
            if (!ConnectedFarmerClients.contains(clientId)) {
                ConnectedFarmerClients.add(clientId);
            }   
        }
        
        while (true) {
            try {
                String update = inFromClient.readUTF();
                sendWeatherStationsData(theList, clientId);
            } catch (Exception e) {
                if (ConnectedFarmerClients.contains(clientId)) {
                    ConnectedFarmerClients.remove(Integer.valueOf(clientId));
                }   
            }
        }
    }

    public void sendWeatherStationsData(List<WeatherStation> weatherStationsToPrint, int clientId) {
        try {
            String weatherStationsToClient = "";
            synchronized (weatherStationsToPrint){
                for (WeatherStation station : weatherStationsToPrint) {
                    int weatherStationId = station.getID();
                    double temperature = station.getTemp();
                    double thePerci = station.getPerci();
                    int humidity = (int)station.getHumid();
                    int barometric = (int)station.getBaro();
                    double windForce = station.getWind();
                    // String GPSLocation = FileHandler.getInstance().IDisInTheFile(String.valueOf(weatherStationId));
                    List<String> weatherStationInfo = FileHandler.getInstance().getWeatherStationInfo(weatherStationId);
                    Date now = new Date();      
                    Long longTime = now.getTime()/1000;
                    int timeStamp = longTime.intValue();
                    // outToClient.writeUTF("TheStationToMonitor("+theStationIDtoMonitor+"): " + "Temp: "+theTemp + "|Perci: " + thePerci + "|Humid: " + theHumid + "|Baro: " + theBaro + "|Wind: " + theWind);  
                    weatherStationsToClient += "WEATHERSTATION\n" + weatherStationId + "\n" + temperature + "\n" + (int)thePerci + "\n" + humidity + "\n" + barometric + "\n"
                     + windForce + "\n" + timeStamp + "\n" + weatherStationInfo.get(0) + "\n" + weatherStationInfo.get(1) + "\n" + weatherStationInfo.get(2);
                }
            };
            outToClient.writeUTF(weatherStationsToClient);  

        } catch(IOException e) {
            e.printStackTrace();
            synchronized(ConnectedFarmerClients) {
                if (ConnectedFarmerClients.contains(clientId)) {
                    ConnectedFarmerClients.remove(Integer.valueOf(clientId));
                }   
            }
        }
    }
    
    /*
    public void getNumConnectedClients() {
        try {
            outToClient.writeUTF(String.valueOf(ConnectedFarmerClients.size()));
            String weatherStationsToClient = "";
            synchronized (theList){
                for (WeatherStation station : theList) {
                    int weatherStationId = station.getID();
                    double temperature = station.getTemp();
                    double thePerci = station.getPerci();
                    int humidity = (int)station.getHumid();
                    int barometric = (int)station.getBaro();
                    double windForce = station.getWind();
                    // String GPSLocation = FileHandler.getInstance().IDisInTheFile(String.valueOf(weatherStationId));
                    String GPSLocation = "55";
                    Date now = new Date();      
                    Long longTime = new Long(now.getTime()/1000);
                    int timeStamp = longTime.intValue();;
                    // outToClient.writeUTF("TheStationToMonitor("+theStationIDtoMonitor+"): " + "Temp: "+theTemp + "|Perci: " + thePerci + "|Humid: " + theHumid + "|Baro: " + theBaro + "|Wind: " + theWind);  
                    weatherStationsToClient += "WEATHERSTATION\n" + weatherStationId + "\n" + temperature + "\n" + (int)thePerci + "\n" + humidity + "\n" + barometric + "\n"
                     + windForce + "\n" + timeStamp + "\n" + GPSLocation;
                    //weatherStationsToClient += "WEATHERSTATION\n" + weatherStationId + "\n" + temperature + "\n" + humidity + "\n" + barometric + "\n" + windForce + "\n"+ (int)thePerci + "\n" + timeStamp + "\n" + GPSLocation;
                }
            };
            System.out.println("oitat");
            outToClient.writeUTF(weatherStationsToClient);  
            System.out.println(weatherStationsToClient);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public void getWeatherStationData(String[] clientParameters) {
        int weatherStationId = Integer.parseInt(clientParameters[3]);
        boolean weatherStationFound = false;
        for (WeatherStation cw : theList) {
            if (cw.getID() == weatherStationId) {
                weatherStationFound = true;
                List<WeatherStation> weatherStationLst = new ArrayList<WeatherStation>();
                weatherStationLst.add(cw);
                sendWeatherStationsData(weatherStationLst, weatherStationId);
                break;
            }
        }
        if (!weatherStationFound) {
            try {
                outToClient.writeUTF("error");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    

    public void stationService(String[] clientParameters) {
        System.out.println("A weather station has just connected");
        
        String method = clientParameters[1];
        switch (method) {
            case "sendWeatherStationStats":
                // String weatherStationId = "1";
                sendWeatherStationStats(clientParameters);
                break;
        }
    }
    
    public void sendWeatherStationStats(String [] clientParameters) {
        int weatherStationId = Integer.parseInt(clientParameters[2]);
        int thisStationIndex = -1;

        boolean keppSationAlive = true;

        String serverSetUpStatus = FileHandler.getInstance().verifyWeatherStationSetUp(clientParameters[2]);
        //If theGPS is null, ask the client for the GPS
        try {
            switch(serverSetUpStatus) {
                case "1":
                    outToClient.writeUTF("OK");
                    break;
                case "-1": // not activated
                    outToClient.writeUTF("NotActivated");
                    break;
                case "-2": // doesnt exist
                    outToClient.writeUTF("RequestGPS");  

                    //Get the GPS from client
                    String weatherStationData = inFromClient.readUTF();
                    //Write it back to the file
                    FileHandler.getInstance().saveWeatherStationSetUp(String.valueOf(weatherStationId), weatherStationData);
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        
        while (true & keppSationAlive) {
            try {
                String weatherStationStats = inFromClient.readUTF();
                String[] statsParameters = weatherStationStats.split("\n");
                // outToClient.writeUTF("Hello weather station");  
                boolean stationIsInList = false;
                String theLine = "";

                //Get the stationID from the station
                // weatherStationId = Integer.parseInt(statsParameters[0]);

                boolean stationFound = false;
                synchronized (theList){
                    for (WeatherStation cw : theList) {
                         if (cw.getID() == weatherStationId) {
                             stationFound = true;
                             thisStationIndex = theList.indexOf(cw);
                             break;
                         }
                    }

                    WeatherStation currentWeatherStation;
                    double temperature = Double.parseDouble(statsParameters[0]);
                    double percipitation = Double.parseDouble(statsParameters[1]);
                    double humidity = Double.parseDouble(statsParameters[2]);
                    double barometricPressure = Double.parseDouble(statsParameters[3]);
                    double wind = Double.parseDouble(statsParameters[4]);
                    int timeStamp = Integer.parseInt(statsParameters[5]);

                    if (stationFound) {
                        currentWeatherStation = new WeatherStation(weatherStationId, temperature, percipitation, humidity, barometricPressure, wind, timeStamp);
                        theList.set(thisStationIndex, currentWeatherStation);
                    } else {
                        currentWeatherStation = new WeatherStation(weatherStationId, temperature, percipitation, humidity, barometricPressure, wind, timeStamp);
                        theList.add(currentWeatherStation);
                    }
            
                    String weatherStationStatsStr = currentWeatherStation.getTemp() + "," + currentWeatherStation.getPerci() + "," + currentWeatherStation.getHumid()
                            + "," + currentWeatherStation.getBaro() + "," + currentWeatherStation.getWind() + "," + currentWeatherStation.getTimeStamp();

                    FileHandler.getInstance().saveWeatherStationFile(String.valueOf(weatherStationId), weatherStationStatsStr);
                };
            } catch(IOException e) { 
                System.out.println("A Weather Station Disconnected! " + e);

                //The weather station has just disconnected, pop and update the weather station list
                if(thisStationIndex != -1){
                    synchronized (theList) {
                        theList.remove(thisStationIndex);
                        keppSationAlive = false;
                    };
                }
            }
        }
    }
}