<link rel="stylesheet" type="text/css" href="resources/css/custom.css" />  

# Waste Incinerator Service
 
## Sprint info
<table>
<tr><th>Sprint name</th><td>Sprint 1</td></tr>
<tr><th>Previous sprint</th><td><a href="/WIS_Sprint0">Sprint 0</a></td></tr>
<tr><th>Next sprint</th><td></td></tr>
<tr><th>QAK model</th><td><a href="../src/sprint1.qak">sprint1.qak</a></td></tr>
<tr><th>Developed by</th><td>Alessio Benenati<br/>Giulia Fattori</td></tr>
</table>       

## Sprint Starting Condition and Goals
In the previous sprint we focused on requirements analysis and we produced a simple base archictecture of what could be inferred by the assignement text.
in this sprint we will focus on the relationship between WIS and OpRobot, our goals are
* finding the best way to divide the buisness logic between the OpRobot and the WIS actor 
* consequently choosing the right model (**Actor** or **POJO**) for the OpRobot
* producing a simple prototype of the system reproducing the functioning of this two entities


## Problem Analysis

### OpRobot and WIS
When considering how to divide the business logic between components, we identified two main approaches:
* **Centralized Solution**: In this approach, the OpRobot functions as a simple POJO, acting as an executor for WIS. WIS takes responsibility for monitoring the system's state and instructs the OpRobot to move accordingly.
* **Distributed Solution**: Here, the OpRobot is designed as an Actor that interacts with WIS. The OpRobot requests WIS, which observes the system's state, to confirm whether conditions for movement are met. If they are, the OpRobot initiates an 'OpRobot Cycle,' which includes obtaining an RP, incinerating it, and transporting the ashes to ashStorage.


### Pro e Contro
* Centralizzato: 
    * + logica di buinsess concentrata
    * - (poco estendibile)


## Future Sprints
