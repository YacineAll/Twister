import React, { Component } from "react"
import NavigationPannel from './compenents/NavigationPannel';
import SignUp from './compenents/SignUp';
import NotFound from './compenents/NotFound'
import history from './history'
import { BrowserRouter, Switch, Route } from 'react-router-dom'



const routes=[
    {
        path: "/",
        component: MainPage, 
    },
    {
        path: "/SignUp",
        component: SignUp,

    }
]


// const MyRoute = () => (
//     <BrowserRouter history={history}>
//         <Switch>
//             <Route exact path="/" component={MainPage}></Route>
//             <Route exact path="/SignUp" component={SignUp} ></Route>
//             <Route exact path="/SignIn" component={MainPage}></Route>
//             <Route component={NotFound}></Route>
//         </Switch>
//     </BrowserRouter>
// )



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
        this.setState({current_page : "profile", isConnected : true })
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
            </div>
        ) 
    }

}


