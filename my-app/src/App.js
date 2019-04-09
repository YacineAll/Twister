
import React from "react"



import MainPage from './MainPage';

import { Route, Switch } from "react-router-dom"
import NotFound from './compenents/NotFound'
import Mur from './compenents/Mur'
import SignUp from './compenents/SignUp';

// import Login from './Login'

const App = () =>(
    <div>
        <Switch>
            <Route exact path='/' component={MainPage}/>
            <Route exact path='/signup' component={SignUp}/>
            <Route exact path='/signin' component={MainPage}/> 
            <Route exact path='/Mur' component={Mur}/> 
            <Route path="*" component={NotFound}/>
        </Switch>
    </div>
    
)

export default App 