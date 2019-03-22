import React, { Component } from "react"
import NavigationPannel from './compenents/NavigationPannel';
import SignUp from './compenents/SignUp';

export default class MainPage extends Component{
    constructor(props){
        super(props)
        this.state = {
            current_page : "connection",
            isConnected  : false
        }

        this.getConnected = this.getConnected.bind(this)
        this.setLogout = this.setLogout.bind(this)

    }

    getConnected(){
        this.setState({current_page : "profile",isConnected : true})
    }
    setLogout() {
        this.setState({ current_page: "connection", isConnected: false })
    }
    render(){
        return(
           <div className="MainPage">
                <div className = "MainNavigatePanel">
                    <NavigationPannel  
                        getConnected= {this.getConnected}
                        setLogout  =  {this.setLogout}
                        isConnected = {this.state.isConnected}
                    ></NavigationPannel> 
                </div>
                <div className="SignUp">
                    {this.state.current_page === "connection" && <SignUp/>}
                </div>   
            </div>
        ) 
    }

}