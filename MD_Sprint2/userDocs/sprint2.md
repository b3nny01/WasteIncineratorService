<link rel="stylesheet" type="text/css" href="resources/css/custom.css" />

# Waste Incinerator Service

## Sprint info 

<table>
<tr><th>Sprint name</th><td>Sprint 2</td></tr>
<tr><th>Previous sprint</th><td><a href="/WIS_Sprint1">Sprint 1</a></td></tr>
<tr><th>Next sprint</th><td></td></tr>
<tr><th>QAK model</th><td><a href="../src/sprint2.qak">sprint2.qak</a></td></tr>
<tr><th>Developed by</th><td>Alessio Benenati<br/>Giulia Fattori</td></tr>
<tr><th>Repo Site</th><td><a href="https://github.com/b3nny01/WasteIncineratorService">WasteIncineratorService</td><tr>
</table>

## Sprint Starting Condition and Goals

In the previous sprint, we focused on studying the requirements related to the application logic of OpRobot and WIS.**In this sprint, the focus is on the MonitoringDevice**, specifically aiming to **connect the virtual system** produced in sprint 1 **to a real MonitoringDevice** deployed on a physical Raspberry Pi.

## Problem Analysis

### MonitoringDevice subcomponents

In the previous sprints, we hid the complexity of the monitoring device in a single mock actor without worrying about its subcomponents (LED and Sonar).  
A more in-depth study of the component's application logic reveals two possible approaches:  
- Developing a single MonitoringDevice actor responsible for both managing the LED and emitting data from the Sonar.  
- Breaking down the MonitoringDevice into two distinct actors, the LED and the Sonar.  

The second solution allows for greater decoupling between the two components, especially considering their different nature (the Sonar is a "producer" of information while the LED acts as a "consumer").  
For this reason, it is recommended to **decompose the MonitoringDevice into its two subcomponents (LED and Sonar) and implement them as two independent actors in the same context**.

### Analysis Architecture
Below, we present a comparison between the system architecture derived from the problem analysis in sprint 1 and the one resulting from sprint 2.

**Sprint 1 Architecture**
<img src="./resources/imgs/wis_system_1.png" width="550px"/>

**Sprint 2 Architecture**
<img src="./resources/imgs/wis_system_2.png" width="550px"/>
<img src="./resources/imgs/monitoring_device_2.png" width="550px"/>

## Project

### System Architecture

Based on the Problem Analysis carried out previously, we implemented an executable version of the system covering the discussed features; we attach here a visual representation of the system architecture:

<img src="resources/imgs/wis_system.png" width="1100px"/>

## Implementation

### Sonar Pipeline
During the implementation, we encountered the high sensitivity of the Sonar, which often produces "noisy" data. For this reason, it became necessary to introduce a "Filtering Pipeline" to eliminate spurious data.  

Specifically, this pipeline is composed of three actors:
- **SonarDevice**, which handles the actual reading of all data from the physical sonar.
- **DataCleaner**, which monitors the SonarDevice and filters the relevant results for the problem, aiming to minimize the effect of measurement errors.
- **Sonar**, which serves as the "interface" towards the WIS.

## Test Plan

**Test Class**: [WISTest](../src/main/java/test/WISTest.java)

<table>
<tr>
  <th><b>Test Name</b></th>
  <th><b>Initial Condition</b></th>
  <th><b>Expected Behavior</b></th>
</tr>
<tr>
  <td><b>testIncinineratorActivation</b></td>
  <td>WasteStorage contains 4 RP, AshStorge is empty, nobody empties AshStorage, Incinerator is inactive</td>
  <td>Once the system is initialized, Incinerator is active</td>
</tr>
<tr> 
  <td><b>testOk4Rp</b></td>
  <td>WasteStorage contains 4 RP, AshStorge is empty and can contain the ashes of 3 RPs, nobody empties AshStorage</td>
  <td>After some time WasteStorage contains 1 RP and AshStorage is full</td>
</tr>
</table>

### Usage
To test the system you will have to activate the Virtual Environment first.
To do so, open a terminal in the `unibo.basicrobot24` folder and type
```
docker compose -f virtualRobot23.yaml up
```
**N.B.** If you have an older version of docker, you may have to type `docker-compose` instead of `docker compose`

After that, you will have to activate the BasicRobot, which will act as a mediator between the VirtualRobot and the WasteIncineratorService application.
To do so open another terminal inside the `unibo.basicrobot24` folder and type 

```
gradlew run
```

Lastly, you have to activate the WIS system by opening a third terminal inside the `WIS_Sprint1` folder and running

```
gradlew run
```
**N.B.** Type `gradlew test` if you want to launch JUnit tests instead of activating the system demo.

## Future Sprints
In the next sprint, we will focus on the MonitoringDevice's behavior.<br/>
Our goal is to connect the actual prototype of the system to a real monitoring device deployed on a real raspberry. 
