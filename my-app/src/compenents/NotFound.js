import React, { Component } from "react"
import PageNotFound from '../assets/images/notFoundPage1.jpg';
import { Link } from 'react-router-dom';

export default class NotFound extends Component {
    
    render(){
        return(
            <div>
                <img src={PageNotFound} style={{ width: 400, height: 400, display: 'block', margin: 'auto', position: 'relative' }} alt='Not Found'/>
                <center><Link to="/">Return to Home Page</Link></center>
            </div>
        );
    }
    
}