import React from "react";
import "./IncineratorCellDiv.css";
import incineratorImg from "../assets/img/incinerator.png";
import incineratorBurningImg from "../assets/img/incinerator_burning.gif";

function IncineratorCellDiv(props: { active: boolean; burning: boolean }) {
  return (
    <div
      className={
        "incinerator-cell-div  " +
        (props.active ? "active " : "inactive ") +
        (props.burning ? "burning " : "")
      }
    >
      <img
        src={
          props.active && props.burning ? incineratorBurningImg : incineratorImg
        }
      />
    </div>
  );
}

export default IncineratorCellDiv;
