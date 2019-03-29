import React, { Component } from "react"
import Login from './Login'
import Logout from './Logout'

//import NavBar from './NavBar'

import SignUp from './SignUp';

class NavigationPannel extends Component {
    constructor(props) {
        super(props)
        this.state = {
            redirect: false
        }

        this.setRedirect = this.setRedirect.bind(this)
        this.setRedirectToFalse = this.setRedirectToFalse.bind(this)
    }
    
    setRedirect(){
        this.setState({
            redirect: true
        })
    }


    
    setRedirectToFalse() {
        this.setState({
            redirect: false
        })
    }
    
    renderRedirect = () => {
        if (this.state.redirect) {
            return (
            
            <SignUp getConnected={this.props.getConnected} setRedirectToFalse={this.setRedirectToFalse} />)
        }
    }
    

    render() {

    
        if( (this.state.redirect) && (!this.props.isConnected)){
            return this.renderRedirect()
        }
        
        return (
            <div className= "NavigationPannel" >    
                {this.props.isConnected === false ? 
                    
                    <Login getConnected={this.props.getConnected} 
                           setRedirect={this.setRedirect}></Login>
                    :
                    <Logout setLogout={this.props.setLogout} ></Logout>
                }       
                </div>
        );
    }
}


export default NavigationPannel
