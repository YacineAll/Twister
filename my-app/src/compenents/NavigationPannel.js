import React, { Component } from "react"
import Login from './Login'
import Logout from './Logout'

class NavigationPannel extends Component {

    render() {
        return (
                <div className= "NavigationPannel" >    
                {this.props.isConnected === false ? 
                    
                    <Login getConnected = { this.props.getConnected }></Login>
                    :
                    <Logout setLogout   = { this.props.setLogout    } ></Logout>
                    
                }       
                </div>
        );
        
    }
    
}
export default NavigationPannel