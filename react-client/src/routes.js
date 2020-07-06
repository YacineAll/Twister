import React, { Component } from "react";
import { Router, Route } from "react-router";

import LoginForm from "./containers/login-form";
import SignUpForm from "./containers/sign-up-form";
import UserHome from './containers/user-home'


class Routes extends Component {
  render() {
    return (
      <div>
        <Router history={this.props.history}>
          <Route path="/" component={LoginForm}></Route>
          <Route path="/login" component={LoginForm}></Route>
          <Route path="/signup" component={SignUpForm}></Route>
          <Route path="/account" component={UserHome}></Route>
        </Router>
      </div>
    );
  }
}

export default Routes;
