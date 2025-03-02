import React from "react";
import "./WasteStorageCellDiv.css";
import wasteStorageImg from "../assets/img/waste_storage.png";

function WasteStorageCellDiv(props: { rps: number }) {
    return (
        <div className="waste-storage-cell-div">
            <img src={wasteStorageImg} />
            <div className="rps-div">
                <span className="label">RPs:</span>
                <span className="value">{String(props.rps)}</span>
            </div>

        </div>
    );
}

export default WasteStorageCellDiv;
