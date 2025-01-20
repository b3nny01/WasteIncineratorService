import React from "react";
import "./ControlDiv.css";
import OpRobotStateDiv from "./OpRobotStateDiv";
import LedStateDiv from "./LedStateDiv";

export default function ControlDiv() {
  return (
    <div className="control-div">
      <input type="button" className="add-rp-btn" value="Add rp"/>
      <input type="button" className="empty-ashes-btn" value="Empty ashes"/>
    </div>
  );
}
