import React from 'react';
import './WallCellDiv.css'
import wallImg from '../assets/img/obstacle.png'


function WallCellDiv(props: { id: String }) {

    return (
        <div className={"wall-cell-div " + props.id}>
        </div>
    )
}

export default WallCellDiv;