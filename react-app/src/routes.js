import React, { Component } from "react";
import { Router, Route, Link, browserHistory, IndexRoute } from "react-router";

import LoginForm from "./containers/login-form";
import SignUpForm from "./containers/sign-up-form";
import Home from "./components/home";

class Routes extends Component {
  render() {
    return (
      <div>
        <Router history={browserHistory}>
          <Route path="/" component={Home}></Route>
          <Route path="/login" component={LoginForm}></Route>
          <Route path="/signup" component={SignUpForm}></Route>
      
        </Router>
      </div>
    );
  }
}

export default Routes;
