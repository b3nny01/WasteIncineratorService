import React from "react";
import "./ServiceAreaDiv.css";
import CellDiv from "./CellDiv";

import HomeCellDiv from "./HomeCellDiv";
import IncineratorCellDiv from "./IncineratorCellDiv";
import WasteStorageCellDiv from "./WasteStorageCellDiv";
import AshStorageCellDiv from "./AshStorageCellDiv";
import WallCellDiv from "./WallCellDiv";
import ObstacleCellDiv from "./ObstacleCellDiv";
import OpRobotCellDiv from "./OpRobotCellDiv";

function buildGridTemplateAreas(robotPosX: number, robotPosY: number) {
  let serviceArea = [
    [".", ".", ".", ".", ".", ".", "."],
    [".", ".", ".", ".", "i", "i", "."],
    [".", ".", ".", ".", "i", "i", "."],
    [".", "o", ".", ".", ".", ".", "."],
    [".", ".", ".", ".", ".", ".", "."],
  ];

  serviceArea[robotPosY][robotPosX] = "r";

  let result = "'w0 h w1 w1 w1 w1 w1 w1 w2'\n";
  for (let i = 0; i < serviceArea.length; i++) {
    result += "'w0 ";
    result += serviceArea[i].join(" ");
    result += " w2'";
  }
  result += "'w0 ws ws w3 w3 w3 as as w2'";

  return result;
}

function ServiceArea(props: {
  rps: number;
  active: boolean;
  burning: boolean;
  ashLevel: number;
  opRobotState: string;
  opRobotPosition: { x: number; y: number };
}) {
  const cellDivs: React.JSX.Element[] = [];

  for (let i = 0; i < 4; i++) {
    cellDivs.push(<WallCellDiv id={"w" + String(i)} />);
  }
  for (let i = 0; i < 29; i++) {
    cellDivs.push(<CellDiv />);
  }
  cellDivs.push(<HomeCellDiv />);
  cellDivs.push(<WasteStorageCellDiv rps={props.rps} />);
  cellDivs.push(
    <IncineratorCellDiv active={props.active} burning={props.burning} />
  );
  cellDivs.push(<AshStorageCellDiv ashLevel={props.ashLevel} />);
  cellDivs.push(<ObstacleCellDiv />);
  cellDivs.push(<OpRobotCellDiv />);

  return (
    <div
      className="service-area-div"
      style={
        {
          gridTemplateAreas: buildGridTemplateAreas(
            props.opRobotPosition.x,
            props.opRobotPosition.y
          ),
        } as React.CSSProperties
      }
    >
      {cellDivs}
    </div>
  );
}

export default ServiceArea;
