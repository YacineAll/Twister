import React, { Component } from "react"
import Login from './Login'



import MyLogo from './Logo'
import SignUp from './SignUp';
import Mur from "./Mur"
import NavBar from './NavBar'


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


    routes(){

    }
    
    renderRedirect = () => {
            return (
            <div className="mainPage">
                <div className="container col-lg-4 ml-auto mr-auto align-bottom">
                    <MyLogo ></MyLogo >
                        <SignUp getConnected={this.props.getConnected} setRedirectToFalse={this.setRedirectToFalse} />)
                </div>
            </div>
        )
    }

    

    render() {

    
        if( (this.state.redirect) && (!this.props.isConnected)){
            return this.renderRedirect()
        }
        
        return (
            <div className= "mainPage" >   

                {this.props.isConnected === false ?            
                    <div className="container-fluid">
                        <div className="container-fluid">
                            <NavBar
                                getConnected={this.props.getConnected}
                                setRedirect={this.setRedirect}
                            >

                            </NavBar>
                        </div>
                        <Login getConnected={this.props.getConnected} setRedirect={this.setRedirect}></Login>
                    </div>
                    :
                    <Mur setLogout={this.props.setLogout} ></Mur>
                }       
                </div>
        );
    }
}


export default NavigationPannel
