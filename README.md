# Farm Management Application
 A farm management application with concurrent client-server model using TCP/IP socket programming. (2021)

---

## Description

#### Technologies used

- Java
- TCP/IP socket programming
- Concurrent programming
- Client-server model

<br/>

This project aims to make use of TCP/IP based network infrastructure to frame and implement a system that has a central server, and that server allows multiple weather stations and farmer stations to connect to it simultaneously in real time, making use of concurrent client-server model. For weather stations, after proper setup and necessary permissions from the server admin, it will be able to connect automatically to the server to send data continuously to the server. For farmer stations, after successful login it will connect to the server and a stream of synchronised data from the server will be passed to the farmer client.

A graphical user interface has been designed for both Server Client and Farmer Client. Server Client GUI is designed to allow server admins to allow or deny permissions to weather and farmer stations to connect to the server and server clients allow the admins to delete registered weather and farmer clients. Farmer stations are designed to display graphical graphs for the continuously updating weather information; it also displays the GPS Positioning, hectares, and the field type information. Farmer stations are equipped with the functionality to select their desired weather stations from a list of connected weather stations and visualise the number of weather stations currently connected to the server.

<br/>

## Features:
### The following features using the respective technologies have been implemented in the software:
#### Server Distribution:
The system has a central server that is administered by the admin accounts. The admins overlook all the connections coming through to the server and have the authority to accept or deny requests of connection to the server. The server is protected by a login layer, the admins will have to login with their respective credentials to access the space. The server can be connected to multiple clients simultaneously at the same time, making use of multi-threading and handshake procedure. The server client allows admins to register and authorises them to delete registered clients, view weather station logs and the admins will also be able to visualise the number of clients online.

<br/>

#### Weather Station Distribution:
The system contains several weather stations that are responsible for sending data continuously to the server. For weather stations to send data to the server, weather stations need to be given permissions by server admins to connect to the server and send data. Once the permissions are attained, weather stations can automatically connect to the server.

<br/>

#### Farmer Station Distribution:
The system has multiple farmer stations that act as the data receiving and viewing point of the data passed by the server. To access farmer clients, user would have to login/register with the relevant credentials. If a user is registering, then the server admin would need to accept it as a valid user to give it access to connect to the server. After successful login, the client gets connected to the server and the user can view the synchronised data being received from the server of the desired connected weather station.

<br/>

#### Weather Station’s Role:
Weather stations, with necessary permissions from the server admin, can automatically connect to the server. For new weather stations, it will have to register its ID with the server and then wait for the server admin to accept their request to connect to the server and become a registered station for future automatic connections to the server. New weather stations being setup will need to specify their GPS Position and the information about their field i.e., hectares, and the field type. The weather stations are responsible for sending data continuously to the server; it can send five kinds of data to the server which include temperature, precipitation, humidity, barometric pressure, and wind force. The server will then receive all the new continuous data and keep saving them to a .txt log file named by the weather stations ID.

<br/>

#### Farmer Station’s Role:
Farmer Stations will have to login successfully to access the workstation window and connect to the server. For new farmer clients, it will have to register its ID to the server and wait for server admin to accept their request to connect to the server and become a registered client to allow for future connections to the server. Farmer station clients are designed to display the synchronised data received from the server in graphical charts, all graphs are updating with an interval of 5 seconds each. Farmers can view the weather information in real time including the up-to-date description about the respective field. Farmers can select the desired weather station from a drop-down menu list with all connected weather stations and view its data. Farmer client stations also provide the feature where the client can see the number of weather stations currently connected to the server.

<br/>

[Back To The Top](#farm-management-application)

---
