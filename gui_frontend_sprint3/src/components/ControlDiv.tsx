import React, { useState, useEffect } from "react";
import "./ControlDiv.css";
import OpRobotStateDiv from "./OpRobotStateDiv";
import LedStateDiv from "./LedStateDiv";

interface ControlDivProps {
  sendMessage: (message: string) => void;
}

export default function ControlDiv({sendMessage}: ControlDivProps) {

  const handleAddRP = () => {
    sendMessage(JSON.stringify("waste"))
    console.log("Message sent: add RP")
  };

  const handleEmptyAshes = () => {
    sendMessage(JSON.stringify("ash"))
    console.log("Message sent: empty ashes")
  };
  
  return (
    <div className="control-div">
      <input type="button" className="add-rp-btn" value="Add RP" onClick={handleAddRP}/>
      <input type="button" className="empty-ashes-btn" value="Empty ashes" onClick={handleEmptyAshes}/>
    </div>
  );
}
