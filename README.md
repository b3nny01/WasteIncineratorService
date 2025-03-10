# WasteIncineratorService

## Intro
WasteIncineratorService is the final project assigned by Prof. [anatali](https://github.com/anatali) for the major course of Software Engineering.<br/>
You can find the requirements of the application [here](https://htmlpreview.github.io/?https://github.com/b3nny01/WasteIncineratorService/blob/main/_resources/slides/TemaFinale24.html).


## QAK
The majority of the project has been modeled using QAK (Quasi Actor Kotlin), a meta-model created at UNIBO.
QAK has its own DSL developed using xText that compiles directly into Kotlin code.<br/>
QAK allowed us to design the application with a higher level of abstraction, introducing the following main concepts:
* Actor: active entity modelled as finite state machines capable of sending and receiving messages.
* Context: an environment that contains some actors and abilitates them to communicate with other actors both in the same or in another context
* Interactions: abstractions of the main communications strategies (requests, dispatches, events).

We chose to use QAK because it helps bridge the abstraction gap, allowing us to maintain a higher level of technology independence during the initial phases of development.<br/>
You can find a detailed description of QAK  [here](https://htmlpreview.github.io/?https://github.com/b3nny01/WasteIncineratorService/blob/main/_resources/slides/QakActors24.html).

## Development process
We adopted a Scrub inspired development process, where the main assignement was divided in a series of sub-problems each faced during in a Sprint.<br/>
At the end of each Sprint we produced an executable version of the system covering some of the requirements.

### Sprints

<table>
    <tr>
        <th>
            <b>Sprint Name</b>
        </th>
        <th>
            <b>Description</b>
        </th>
        <th>
            <b>QAK</b>
        </th>
        <th>
            <b>UserDoc</tb>
        </th>
        <th>
        Output
        </th>
    </tr>
    <tr>
        <td>
            <a href="Sprint0/WIS/">WIS_Sprint0</a>
        </td>
        <td width="300px">
            requirements analysis
        </td>
        <td>
            <a href="Sprint0/WIS/src/sprint0.qak">sprint0.qak</a>
        </td>
        <td>
            <img src="_resources/imgs/readmeLogo.svg" height="15px"/> <a href="Sprint0/README.md">sprint0.md</a><br/>
            <img src="_resources/imgs/pdfLogo.png" height="15px"/> <a href="https://nbviewer.org/github/b3nny01/WasteIncineratorService/blob/main/Sprint0/README.pdf" title="sprint0.pdf" download>sprint0.pdf</a><br/>
        </td>
        <td>
            <img src="Sprint0/_resources/imgs/wis_systemarch.png" height="100px">
        </td>
    </tr>
    <tr>
        <td>
            <a href="Sprint1/WIS/">WIS_Sprint1</a>
        </td>
        <td width="300px">
            OpRobot and WIS responsibilities and business logic, first working prototype in virtual environment.
        </td>
        <td>
            <a href="Sprint1/WIS/src/sprint1.qak">sprint1.qak</a>
        </td>
        <td>
            <img src="_resources/imgs/readmeLogo.svg" height="15px"/> <a href="Sprint1/README.md">sprint1.md</a><br/>
            <img src="_resources/imgs/pdfLogo.png" height="15px"/> <a href="https://nbviewer.org/github/b3nny01/WasteIncineratorService/blob/main/Sprint1/README.pdf" title="sprint1.pdf" download>sprint1.pdf</a><br/>
        </td>
        <td>
            <img src="Sprint1/_resources/imgs/sprint1_recording.gif" height="100px">
        </td>
    </tr>
        <tr>
        <td>
            <a href="Sprint2/WIS">WIS_Sprint2</a>
        </td>
        <td width="300px">
            Connection of the virtual environment to a real monitoring device deployed on a phisical Raspberry.
        </td>
        <td>
            <a href="Sprint2/WIS/src/sprint1.qak">sprint2.qak</a>,
            <a href="Sprint2/MD/src/monitoring-device.qak">monitoring-device.qak</a>
        </td>
        <td>
            <img src="_resources/imgs/readmeLogo.svg" height="15px"/> <a href="Sprint2/README.md">sprint2.md</a><br/>
            <img src="_resources/imgs/pdfLogo.png" height="15px"/> <a href="https://nbviewer.org/github/b3nny01/WasteIncineratorService/blob/main/Sprint2/README.pdf" title="sprint1.pdf" download>sprint2.pdf</a><br/>
        </td>
        <td>
            <img src="Sprint2/_resources/imgs/monitoring_device.jpg" height="100px">
        </td>
    </tr>
    <tr>
        <td>
            <a href="Sprint3/WIS">WIS_Sprint3</a>
        </td>
        <td width="300px">
            User interface, MQTT Broker, and dockerization.
        </td>
        <td>
            <a href="Sprint3/WIS/src/sprint3.qak">sprint3.qak</a>
        </td>
        <td>
            <img src="_resources/imgs/readmeLogo.svg" height="15px"/> <a href="Sprint3/README.md">sprint3.md</a><br/>
            <img src="_resources/imgs/pdfLogo.png" height="15px"/> <a href="https://nbviewer.org/github/b3nny01/WasteIncineratorService/blob/main/Sprint3/README.pdf" title="sprint3.pdf" download>sprint3.pdf</a><br/>
        </td>
    </tr>
</table>

## Credits
Developed by
* [b3nny01](https://github.com/b3nny01/)
* [jjulespop](https://github.com/jjulespop/)

QAK infrastructure and BasicRobot project by
* Prof. [anatali](https://github.com/anatali)