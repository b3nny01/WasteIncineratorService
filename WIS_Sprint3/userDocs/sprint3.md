<link rel="stylesheet" type="text/css" href="resources/css/custom.css" />

# Waste Incinerator Service

## Sprint info

<table>
<tr><th>Sprint name</th><td>Sprint 3</td></tr>
<tr><th>Previous sprint</th><td><a href="/WIS_Sprint2">Sprint 2</a></td></tr>
<tr><th>QAK model</th><td><a href="../src/sprint3.qak">sprint3.qak</a></td></tr>
<tr><th>Developed by</th><td>Alessio Benenati<br/>Giulia Fattori</td></tr>
<tr><th>Repo Site</th><td><a href="https://github.com/b3nny01/WasteIncineratorService">WasteIncineratorService</td><tr>
</table>

## Sprint Starting Condition and Goals

In the previous sprint, we focused on the MonitoringDevice and how to connect its physical components to our system. In this sprint, we aim to connect our fully working system to a web interface to show users all the steps taken by OpRobot and let them interact directly with it; on top of that, we will dockerize all of our sprints in different containers for easier activation.

## Requirements

In the previous sprint review, to test the virtual environment application more easily, the client asked to add two buttons to the user interface, one to increase the number of RP in WasteStorage, the other to free AshStorage.

## Problem Analysis

### MQTT

All the previous sprints used the qak function _updateResource_ to emit updates that are then read by the observers; this method introduces quite a strong coupling between the different components of the system.</br>
Another possibility is using a publish/subscribe protocol, like **MQTT**, that introduces an intermediary between the communicating qak actors and grants a **higher degree of separation** throughout the entire system, as well as more robust communication. 

### Interface

Following the system requirements, a **ServiceStatusGui** is needed to display and update the status of two storages, the incinerator, and the OpRobot; on top of that, it must give the user the possibility to increase the number of RP in WasteStorage and empty completely the AshStorage.</br>
Consequently, the Gui needs **both to receive and send information** from and to the qak system, which means they require a common communication protocol to exchange this data in real-time. The Gui also has to display all the required information on a **graphic interface**, using different intuitive icons.

## Project

### Project Architecture

Based on the Problem Analysis carried out previously, we implemented an executable version of the system covering the discussed features; we attach here a visual representation of the system architecture:

<img src="resources/imgs/wis_systemarch.png" width="1100px"/>

## Implementation

### Docker
In order to simplify the application deployment, we decided to **dockerize all the components of the system** but the MonitoringDevice, since it needs to run on an embedded device.

### MQTT Broker

A **MQTT Broker** is needed to implement the MQTT protocol. We decided to use a Mosquitto local broker, as it is easily integrated alongside our qak architecture; this broker is also used to implement communication between the ServiceStatusGui and the qak system, more details will be given in the next section.</br>
The actors that need to send or receive messages connect to the broker and then either **subscribe to or publish updates on specific topics**:</br>
-**actor_state**: topic used by the actors to publish updates about their own status.</br>
-**system_state**: topic used by WIS to expose the real-time state of the whole system.</br>
-**mock_cmd**: topic used by Backend to send the command (increase RP or empty AshStorage) when a button is pressed.

### ServiceStatusGui

We decided to split our Gui into two projects:</br>
-**Backend**: it manages all communication between the Gui and the WIS System, using the MQTT protocol.</br>
-**Frontend**: React Application showing the console. It communicates with Backend via WebSocket.


### Usage

#### Monitoring Device
Firstly, you will need:
* a raspberry (we used a raspberry PI 3+)
* a led
* a sonar (HCSR04)
* a 220ohm resistor
* a breadboard

You will have to assemble those elements following this wiring scheme:

<img src="resources/imgs/rasp_scheme.jpeg">

Then you will have to deploy the Monitoring Device control software, to do so, open a terminal inside the `MD_Sprint3` folder run:

```
gradlew build
```

After that, copy the `MD_Sprint3/build/distributions/monitoring_device-1.0.zip` folder inside the raspberry (for instance using `scp`) and unzip it

#### System Activation
Then, you have to activate the monitoring device, to do so connect to your raspberry via `ssh`, then move inside the `monitoring_device-1.0/bin` folder and run
```
./monitoring_device
```

Lastly, you have to activate the WIS system by opening a third terminal inside the `WIS_Sprint3` folder and running

```
docker compose up
```
**N.B.** Type `docker compose -f docker-compose-mock.yaml up` if you want to launch the mock version of the application.
