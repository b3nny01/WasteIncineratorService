# WasteIncineratorService

## Intro
WasteIncineratorService is the final project assigned by Prof. [anatali](https://github.com/anatali) for the major course of Software Engineering.<br/>
You can find the requirements of the application [here](https://htmlpreview.github.io/?https://github.com/b3nny01/WasteIncineratorService/blob/main/resources/slides/TemaFinale24.html).


## QAK
The majority of the project has been modeled using QAK (Quasi Actor Kotlin), a meta-model created at UNIBO.
QAK has its own DSL developed using xText that compiles directly into Kotlin code.<br/>
QAK allowed us to design the application with a higher level of abstraction, introducing the following main concepts:
* Actor: active entity modelled as finite state machines capable of sending and receiving messages.
* Context: an environment that contains some actors and abilitates them to communicate with other actors both in the same or in another context
* Interactions: abstractions of the main communications strategies (requests, dispatches, events).

We chose to use QAK because it helps bridge the abstraction gap, allowing us to maintain a higher level of technology independence during the initial phases of development.<br/>
You can find a detailed description of QAK  [here](https://htmlpreview.github.io/?https://github.com/b3nny01/WasteIncineratorService/blob/main/resources/slides/QakActors24.html).

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
    </tr>
    <tr>
        <td>
            <a href="./WIS_Sprint0/">WIS_Sprint0</a>
        </td>
        <td>
            requirements analysis
        </td>
        <td>
            <a href="./WIS_Sprint0/src/sprint0_v1.qak">sprint0.qak</a>
        </td>
        <td>
            <img src="resources/imgs/readmeLogo.svg" height="15px"/> <a href="./WIS_Sprint0/userDocs/sprint0_v1.md">sprint0.md</a><br/>
            <img src="resources/imgs/pdfLogo.png" height="15px"/> <a href="https://htmlpreview.github.io/?https://github.com/b3nny01/WasteIncineratorService/blob/main/WIS_Sprint0/userDocs/sprint0_v1.pdf" title="sprint0.pdf" download>sprint0.pdf</a><br/>
            <img src="resources/imgs/htmlLogo.png" height="15px"/> <a href="https://htmlpreview.github.io/?https://github.com/b3nny01/WasteIncineratorService/blob/main/WIS_Sprint0/userDocs/sprint0_v1.html" title="sprint0.html" download>sprint0.html</a>
        </td>
    </tr>
</table>

## Usage

## Credits
