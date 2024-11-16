import React from 'react';
import './AshStorageCellDiv.css'
import ashStorageImg from '../assets/img/ash_storage.png'

function AshStorageCellDiv(props: { ashLevel: number }) {

    return (
        <div className="ash-storage-cell-div">
            <div className="ash-level-div">
                <span className="label">Ash Level:</span>
                <span className="value">{String(100 * props.ashLevel) + "%"}</span>
            </div>
            <img src={ashStorageImg} />
        </div>
    )
}

export default AshStorageCellDiv;