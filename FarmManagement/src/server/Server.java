package server;

import java.net.*; 
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9090);

        //Create a synchronzied list
        List<WeatherStation> StationList = Collections.synchronizedList(new ArrayList<WeatherStation>());
        List<Integer> ConnectedFarmerClients = Collections.synchronizedList(new ArrayList<Integer>());
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerClientLogin(StationList, ConnectedFarmerClients).setVisible(true);
            }
        });
        while(true) {
            System.out.println("Waiting for connection...");

            //accept connection
            Socket client = server.accept();
            System.out.println("Connected " + client.getInetAddress());

            ServerHandler handler = new ServerHandler(client, StationList, ConnectedFarmerClients);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }   
}
