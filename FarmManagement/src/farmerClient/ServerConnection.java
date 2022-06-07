/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author nunos
 */
public class ServerConnection {
    private String clientType = "user";
    private Socket serverConn;
    private boolean connected = false;

    ServerConnection() {
    }
    
    public void connect() {
        try {
            this.serverConn = new Socket("localhost", 9090);
            System.out.println("Connected to " + this.serverConn.getInetAddress());
            this.connected = true;
        } catch (Exception e) {
            e.printStackTrace();
            this.connected = false;
        }
    }
    
    public boolean sendNoReplyRequest(String message, boolean includeClientType) {
        try {
            if (includeClientType) {
                message = clientType + "\n" + message;
            }
            
            DataOutputStream outToServer = new DataOutputStream(this.serverConn.getOutputStream());
            outToServer.writeUTF(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public String sendRequest(String message, boolean includeClientType) {
        String messageFromServer = "";
        try {
            if (includeClientType) {
                message = clientType + "\n" + message;
            }
            
            DataOutputStream outToServer = new DataOutputStream(this.serverConn.getOutputStream());
            outToServer.writeUTF(message);
            
            DataInputStream inFromServer = new DataInputStream(this.serverConn.getInputStream());
            messageFromServer = inFromServer.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return messageFromServer;
    }
    
    public void disconnect() {
        try {
            this.serverConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean isConnected() {
        return this.connected;
    }
/*
    public String sendSoloServerRequest() {
        String weatherStationsServerStats = "";
        try {
            //Create io streams
            DataInputStream inFromServer = new DataInputStream(this.serverConn.getInputStream());
            DataOutputStream outToServer = new DataOutputStream(this.serverConn.getOutputStream());

            //send to server
            outToServer.writeUTF(clientType + "\n" + requestParameters);

            //read from server
            weatherStationsServerStats = inFromServer.readUTF();
            // System.out.println("Server said: " + data);
            server.close();
        } catch (Exception e) {    
            System.out.println(e);
            System.out.println("error");
        }

        System.out.println("Connection to server has just closed\n");
        return weatherStationsServerStats;
    }*/
}
