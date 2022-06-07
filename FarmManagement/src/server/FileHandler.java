package server;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.input.ReversedLinesFileReader;


public class FileHandler {
    private static final FileHandler inst = new FileHandler();
    private static String serverDataPath = "src/server/ServerDataFiles";
    private FileHandler() {
        super();
    }

    /**
    * Logs the current stats of a certain weather station
    *
    * @param  _theStationID  the id of the weather station
    * @param  name log stats line of a weather station
    */
    public synchronized void saveWeatherStationFile(String _theStationID, String _theLine) {
        //Create a folder to store txt files
        File newFolder = new File(serverDataPath + "/weatherStationsLogs");
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }

        // Create a txt file to store data
        // File theTextFile = new File(String.valueOf("src/WeatherStationData/"+_theStationID)+".txt");
        try {
            // Write the data in a newline on the txt file
            FileOutputStream ops = new FileOutputStream(serverDataPath + "/weatherStationsLogs/" + _theStationID + ".txt", true);
            OutputStreamWriter opswriter = new OutputStreamWriter(ops);
            BufferedWriter bw = new BufferedWriter(opswriter);
            bw.write(_theLine);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    // Get the same instance of the filewriter in order to maintin thread safe
    public static FileHandler getInstance() {
        return inst;
    }
    
    
    /**
    * Verifies if a certain weather station already did its setUp
    *
    * @param  weatherStationId  the id of the weather station
    * @return   returns a code in reference to the existence/activation of the weather station
    */
    public synchronized String verifyWeatherStationSetUp(String weatherStationId){
        try {
            // Create an empty file incase the file does not exist
            File theFile = new File(serverDataPath + "/setUps.txt");
            theFile.createNewFile();
            
            FileReader fin = new FileReader(serverDataPath + "/setUps.txt");
            BufferedReader din = new BufferedReader(fin);

            String line = null;
            while((line = din.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line,":");
                
                String StationID = st.nextToken();
                String Status = st.nextToken();
                String GPS = st.nextToken();
                
                if(StationID.equals(weatherStationId)){
                    if(Status.equals("1")) {
                        return "1";
                    } else {
                        return "-1";
                    }
                }
            }
            return "-2";
        }
        catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return "";
    }   
    
    
    /**
    * Saves the setup of a weather station
    *
    * @param  weatherStationId  the id of the weather station
    * @param  weatherStationData    setup data of the weather station
    */
    public synchronized void saveWeatherStationSetUp(String weatherStationId, String weatherStationData){
        String _theLine = weatherStationId + ":0:" + weatherStationData;
        
        //Create a folder to store txt files
        File newFolder = new File(serverDataPath);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
        
        //Create a txt file to store data
        //File theTextFile = new File(String.valueOf("src/WeatherStationData/"+_theStationID)+".txt");
        try {
            //Write the data in a newline on the txt file
            FileOutputStream ops = new FileOutputStream(serverDataPath + "/setUps.txt", true);
            OutputStreamWriter opswriter = new OutputStreamWriter(ops);
            BufferedWriter bw = new BufferedWriter(opswriter);
            bw.write(_theLine);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    /**
    * Gets the information of a certain weather station.
    *
    * @param  weatherStationId  the id of the weather station
    * @return   List<String> with the set-up details of the weather station
    */
    public synchronized List<String> getWeatherStationInfo(int weatherStationId) {
        List<String> weatherStationInfo = new ArrayList<String>();
        try {
            FileReader fin = new FileReader(serverDataPath + "/setUps.txt");
            BufferedReader din = new BufferedReader(fin);

            String fileLine = null;
            while((fileLine = din.readLine()) != null) {
                StringTokenizer weatherStation = new StringTokenizer(fileLine, ":");
                
                int sWeatherStationId = Integer.parseInt(weatherStation.nextToken());
                if (weatherStationId == sWeatherStationId) {
                    weatherStation.nextToken();
                    String GPSLocation = weatherStation.nextToken();
                    String hectares = weatherStation.nextToken();
                    String usedFor = weatherStation.nextToken();
                    weatherStationInfo = Arrays.asList(GPSLocation, hectares, usedFor);
                    break;
                }
            }
        }
        catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return weatherStationInfo;
    }
    
    
    /**
    * Verifies the login credentials of a certain user
    *
    * @param  username  users username
    * @param  password  users password
    * @param  isServerAccount  boolean to verify if its a server account or a farmers client one.
    * @return   returns the userId of the user or a value indicated that the credentials are incorrect/the user is inactive
    */
    public synchronized int verifyLoginCredentials(String username, String password, boolean isServerAccount){
        try {
            //Create an empty file incase the file does not exist
            String typeOfAccount = (isServerAccount) ? "adminUsers" : "users";
            File theFile = new File(serverDataPath + "/" + typeOfAccount + ".txt");
            theFile.createNewFile();
            
            FileReader fin = new FileReader(serverDataPath + "/" + typeOfAccount + ".txt");
            BufferedReader din = new BufferedReader(fin);

            String fileLine = null;
            while((fileLine = din.readLine()) != null) {
                StringTokenizer user = new StringTokenizer(fileLine, ":");
                
                int savedUserId = Integer.parseInt(user.nextToken());
                String activeUser = user.nextToken();
                String savedUsername = user.nextToken();
                String savedPassword = user.nextToken();
                if (savedUsername.equals(username) && savedPassword.equals(password)) {
                    if (activeUser.equals("0")) {
                        return -2;
                    }
                    return savedUserId;
                }
            }
        }
        catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return -1;
    }
    
    
    /**
    * Registers an account with the credentials of a certain user
    *
    * @param  username  users username
    * @param  password  users password
    * @param  isServerAccount  boolean to verify if its a server account or a farmers client one.
    * @return   String with a code in reference to if the username already existed and to if the account was created.
    */
    public synchronized String registerAccount(int UserId, String username, String password, boolean isServerAccount) {
        try {
            String newUserData = UserId + ":0:" + username + ":" + password;
            String typeOfAccount = (isServerAccount) ? "adminUsers" : "users";

            FileOutputStream ops = new FileOutputStream(serverDataPath + "/" + typeOfAccount + ".txt", true);
            OutputStreamWriter opswriter = new OutputStreamWriter(ops);
            BufferedWriter bw = new BufferedWriter(opswriter);
            bw.write(newUserData);
            bw.newLine();
            bw.close();
            return "1";
        }
        catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return "-2";
    }
    
    
    /**
    * Gets all user accounts
    *
    * @param  isServerAccount  boolean to verify if its a server account or a farmers client one.
    * @return   TreeMap containing a list with all the users, with the key being their userId.
    */
    public java.util.TreeMap<Integer, List<String>> getAllUsers(boolean isServerAccount) {
        java.util.TreeMap<Integer, List<String>> usersLst = new java.util.TreeMap<Integer, List<String>>();
        try {
            String typeOfAccount = (isServerAccount) ? "adminUsers" : "users";
            File theFile = new File(serverDataPath + "/" + typeOfAccount + ".txt");
            theFile.createNewFile();

            FileReader fin = new FileReader(serverDataPath + "/" + typeOfAccount + ".txt");
            BufferedReader din = new BufferedReader(fin);

            String fileLine = null;
            while((fileLine = din.readLine()) != null) {
                StringTokenizer user = new StringTokenizer(fileLine, ":");
                String savedUserId = user.nextToken();
                String activeUser = user.nextToken();
                String savedUsername = user.nextToken();
                usersLst.put(Integer.parseInt(savedUserId), (Arrays.asList(savedUserId, activeUser, savedUsername)));
            }
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return usersLst;
    }
    
    
    /**
    * Re-writes a certain file with a certain data
    *
    * @param  file  file name/path to re-write
    * @param  data  data to re-write the file with.
    */
    private void updateFileData(String file, String data) {
        try {
            PrintWriter writer = new PrintWriter(serverDataPath + "/" + file + ".txt");
            writer.print(data);
            writer.close();   
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
    
    /**
    * Changes the account status (active/inactive) of a certain user
    *
    * @param  isServerAccount  boolean to verify if its a server account or a farmers client one.
    * @param  userId  user userId
    * @param  status  status to update with
    */
    public void changeUserActiveStatus(boolean isServerAccount, int userId, boolean status) {
        String fileType = (isServerAccount) ? "adminUsers" : "users";
        try {
            FileReader fin = new FileReader(serverDataPath + "/" + fileType + ".txt");
            BufferedReader din = new BufferedReader(fin);
            
            String newUserData = "";
            String fileLine = "";
            while((fileLine = din.readLine()) != null) {
                StringTokenizer user = new StringTokenizer(fileLine, ":");
                String savedUserId = user.nextToken();
                if (Integer.parseInt(savedUserId) == userId) {
                    String activeUser = user.nextToken();
                    String savedUsername = user.nextToken();
                    String userPassword = user.nextToken();
                    String newStatus = (status) ? "1" : "0";
                    newUserData += savedUserId + ":" + newStatus + ":" + savedUsername + ":" + userPassword + "\n";
                    continue;
                }
                newUserData += fileLine += "\n";
            }
            
            updateFileData(fileType, newUserData);
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
    
    /**
    * Deletes a user account
    *
    * @param  isServerAccount  boolean to verify if its a server account or a farmers client one.
    * @param  userId  user userId
    */
    public void deleteUser(boolean isServerAccount, int userId) {
        String fileType = (isServerAccount) ? "adminUsers" : "users";
        try {
            FileReader fin = new FileReader(serverDataPath + "/" + fileType + ".txt");
            BufferedReader din = new BufferedReader(fin);
            
            String newUserData = "";
            String fileLine = "";
            while((fileLine = din.readLine()) != null) {
                StringTokenizer user = new StringTokenizer(fileLine, ":");
                String savedUserId = user.nextToken();
                if (Integer.parseInt(savedUserId) == userId) {
                    continue;
                }
                newUserData += fileLine += "\n";
            }
            updateFileData(fileType, newUserData);
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
    
    /**
    * Updates a user details
    *
    * @param  isServerAccount  boolean to verify if its a server account or a farmers client one.
    * @param  userId  user userId
    * @param  username  users username
    * @param  password  users password
    */
    public void updateUserData(boolean isServerAccount, int userId, String username, String password) {
        String fileType = (isServerAccount) ? "adminUsers" : "users";
        try {
            FileReader fin = new FileReader(serverDataPath + "/" + fileType + ".txt");
            BufferedReader din = new BufferedReader(fin);
            
            String newUserData = "";
            String fileLine = "";
            while((fileLine = din.readLine()) != null) {
                StringTokenizer user = new StringTokenizer(fileLine, ":");
                String savedUserId = user.nextToken();
                if (Integer.parseInt(savedUserId) == userId) {
                    String activeUser = user.nextToken();
                    String savedUsername = user.nextToken();
                    String userPassword = user.nextToken();
                    password = (!password.isEmpty()) ? password : userPassword;
                    newUserData += savedUserId + ":" + activeUser + ":" + username + ":" + password + "\n";
                    continue;
                }
                newUserData += fileLine += "\n";
            }
            updateFileData(fileType, newUserData);
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
    
    /**
    * Gets the set-up data of all the weather stations
    *
    * @return  TreeMap  contains a map with all the weatherstations details stored in a List<String> (key = weatherStationId)
    */
    public java.util.TreeMap<Integer, List<String>> getAllWeatherStations() {
        java.util.TreeMap<Integer, List<String>> weatherStations = new java.util.TreeMap<Integer, List<String>>();
        try {
            File theFile = new File(serverDataPath + "/setUps.txt");
            theFile.createNewFile();

            FileReader fin = new FileReader(serverDataPath + "/setUps.txt");
            BufferedReader din = new BufferedReader(fin);

            String fileLine = null;
            while((fileLine = din.readLine()) != null) {
                StringTokenizer weatherStation = new StringTokenizer(fileLine, ":");
                String weatherStationId = weatherStation.nextToken();
                String activeStatus = weatherStation.nextToken();
                String GPSPostion = weatherStation.nextToken();
                String hectares = weatherStation.nextToken();
                String UsedFor = weatherStation.nextToken();
                
                weatherStations.put(Integer.parseInt(weatherStationId), (Arrays.asList(weatherStationId, activeStatus, GPSPostion, hectares, UsedFor)));
            }
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return weatherStations;
    }

    
    /**
    * Changes the status of a weather station
    *
    * @param  weatherStationId  the id of the weather station
    * @param  status  status to update the weather station with
    */
    public void changeWeatherStationActiveStatus(int weatherStationId, boolean status) {
        try {
            FileReader fin = new FileReader(serverDataPath + "/setUps.txt");
            BufferedReader din = new BufferedReader(fin);
            
            String newWeatherStationData = "";
            String fileLine = "";
            while((fileLine = din.readLine()) != null) {
                StringTokenizer weatherStation = new StringTokenizer(fileLine, ":");
                String sWeatherStationId = weatherStation.nextToken();
                if (Integer.parseInt(sWeatherStationId) == weatherStationId) {
                    String sWeatherStationStatus = weatherStation.nextToken();
                    String sGPSPosition = weatherStation.nextToken();
                    String sHectares = weatherStation.nextToken();
                    String sUsedFor = weatherStation.nextToken();
                    String newStatus = (status) ? "1" : "0";
                    newWeatherStationData += sWeatherStationId + ":" + newStatus + ":" + sGPSPosition + ":" + sHectares + ":" + sUsedFor + "\n";
                    continue;
                }
                newWeatherStationData += fileLine += "\n";
            }
            updateFileData("setUps", newWeatherStationData);
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
    
    /**
    * Updates the set-up data of a certain weather station
    *
    * @param  weatherStationId  the id of the weather station
    * @param  GPSLocation  GPS location of the weather station
    * @param  hectares  number of hectares that the weather station covers
    * @param  userFor  what the weather station is used for.
    */
    public void updateWeatherStationDate(int weatherStationId, String GPSLocation, String hectares, String userFor) {
        try {
            String fileType = "setUps";
            FileReader fin = new FileReader(serverDataPath + "/" + fileType + ".txt");
            BufferedReader din = new BufferedReader(fin);
            
            String newWeatherStationData = "";
            String fileLine = "";
            while((fileLine = din.readLine()) != null) {
                StringTokenizer weatherStation = new StringTokenizer(fileLine, ":");
                String sWeatherStationId = weatherStation.nextToken();
                if (Integer.parseInt(sWeatherStationId) == weatherStationId) {
                    String sWeatherStationStatus = weatherStation.nextToken();
                    newWeatherStationData += sWeatherStationId + ":" + sWeatherStationStatus + ":" + GPSLocation + ":" + hectares + ":" + userFor + "\n";
                    continue;
                }
                newWeatherStationData += fileLine += "\n";
            }
            updateFileData(fileType, newWeatherStationData);
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
    }
    
    
    
    /**
    * Returns the stats list of a certain weather station.
    *
    * @param  weatherStationId  the id of the weather station
    * @return the data of a weather station
    */
    public String getWeatherStationData(int weatherStationId) {
        String weatherStationData = "";
        try {
            File file = new File(serverDataPath + "/weatherStationsLogs/" + weatherStationId + ".txt");
            weatherStationData =  FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch(IOException e) {
            System.err.println("Error:" + e.getMessage());
        }
        return weatherStationData;
    }
    
    
    public int downloadFile(File fileToSave, int weatherStationId) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(serverDataPath + "/weatherStationsLogs/" + weatherStationId + ".txt");
            os = new FileOutputStream(fileToSave.getAbsolutePath());
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}