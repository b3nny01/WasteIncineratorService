import React from "react";
import "./LedStateDiv.css";

const stateImageMapping = {
  OFFLINE: require("../assets/img/offline.png"),
  ON: require("../assets/img/led_on.png"),
  OFF: require("../assets/img/led_off.png"),
  BLINKING: require("../assets/img/led_blinking.gif"),
};

export default function LedStateDiv(props: { ledState: String }) {
  return (
    <div className="led-state-div">
      <div>
        <span className="label">Led State:</span>
        <span className="value"> {props.ledState}</span>
      </div>
      <div className="image-div">
        <img
          src={
            stateImageMapping[
              props.ledState as keyof typeof stateImageMapping
            ]
          }
        />
      </div>
    </div>
  );
}
