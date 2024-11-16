import React from "react";
import "./OpRobotStateDiv.css";

const stateImageMapping = {
  offline: require("../assets/img/offline.png"),
  moving_ws: require("../assets/img/moving_ws.gif"),
  moved_ws: require("../assets/img/moved_ws.gif"),
  rp_loading: require("../assets/img/rp_loading.gif"),
  rp_loaded: require("../assets/img/rp_loaded.gif"),
  moving_bi: require("../assets/img/moving_incinerator.gif"),
  moved_bi: require("../assets/img/moved_incinerator.gif"),
  rp_unloading: require("../assets/img/rp_unloading.gif"),
  rp_unloaded: require("../assets/img/rp_unloaded.gif"),
  moving_h: require("../assets/img/moving_h.gif"),
  moved_h: require("../assets/img/moved_h.gif"),
  waiting: require("../assets/img/moved_h.gif"),
  moving_bo: require("../assets/img/moving_incinerator.gif"),
  moved_bo: require("../assets/img/moved_incinerator.gif"),
  ash_loading: require("../assets/img/ash_loading.gif"),
  ash_loaded: require("../assets/img/ash_loaded.gif"),
  moving_as: require("../assets/img/moving_as.gif"),
  moved_as: require("../assets/img/moved_as.gif"),
  ash_unloading: require("../assets/img/ash_unloading.gif"),
  ash_unloaded: require("../assets/img/ash_unloaded.gif"),
};

export default function OpRobotStateDiv(props: { opRobotState: String }) {
  return (
    <div className="op-robot-state-div">
      <div className="image-div">
        <img
          src={
            stateImageMapping[
              props.opRobotState as keyof typeof stateImageMapping
            ]
          }
        />
      </div>
      <div>
        <span className="label">OpRobot State:</span>
        <span className="value"> {props.opRobotState}</span>
      </div>
    </div>
  );
}
