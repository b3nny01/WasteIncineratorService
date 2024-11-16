import React from "react";
import "./InfoDiv.css";
import OpRobotStateDiv from "./OpRobotStateDiv";
import LedStateDiv from "./LedStateDiv";

export default function InfoDiv(props: {
  opRobotState: string;
  ledState: string;
}) {
  return (
    <div className="info-div">
      <LedStateDiv ledState={props.ledState} />
      <OpRobotStateDiv opRobotState={props.opRobotState} />
    </div>
  );
}
