
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
            <Route exact path='/' component={MainPage} ></Route>
            <Route exact path='/signup' component={SignUp}></Route>
            <Route exact path='/signin' component={MainPage}> </Route>
            <Route exact path='/Mur' component={Mur}
                render={props => <Mur {...props} values={this.props.values}> </Mur>}
            ></Route>
            <Route path="*" component={NotFound}></Route>
        </Switch>
    </div>
    
)

export default App 