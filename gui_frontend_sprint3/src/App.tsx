import React, { useEffect, useRef, useState } from "react";
import logo from "./logo.svg";
import "./App.css";
import ServiceAreaDiv from "./components/ServiceAreaDiv";
import InfoDiv from "./components/InfoDiv";
import useWebSocket, { ReadyState } from "react-use-websocket";
import ControlDiv from "./components/ControlDiv";

const webSocketUrl = "ws://localhost:8085/accessgui";

function App() {
  const [systemState, setSystemState] = useState({
    rps: -1,
    active: false,
    burning: false,
    ashLevel: -1,
    opRobotState: "offline",
    opRobotPosition:{x:0,y:0},
    ledState: "offline",
  });

  const { sendMessage, lastMessage, readyState } = useWebSocket(webSocketUrl, {
    onOpen: () => {
      console.log("WebSocket connection established.");
    },
  });

  useEffect(() => {
    if (lastMessage !== null) setSystemState(JSON.parse(lastMessage.data));
  }, [lastMessage]);

  return (
    <div className="App">
      <div className="left-div">
        <ServiceAreaDiv
          rps={systemState.rps}
          active={systemState.active}
          burning={systemState.burning}
          ashLevel={systemState.ashLevel}
          opRobotState={systemState.opRobotState}
          opRobotPosition={systemState.opRobotPosition}
        />
      </div>
      <div className="right-div">
        <InfoDiv
          opRobotState={systemState.opRobotState}
          ledState={systemState.ledState}
        />
        <ControlDiv/>
      </div>
    </div>
  );
}

export default App;
