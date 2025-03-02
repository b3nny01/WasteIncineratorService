import React from 'react';
import './HomeCellDiv.css'
import homeImg from '../assets/img/home.png'

function HomeCellDiv() {

    return (
        <div className="home-cell-div">
            <img src={homeImg}/>
        </div>
    )    
}

export default HomeCellDiv;