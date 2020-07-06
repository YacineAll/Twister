import React, { Component } from "react";
import { connect } from "react-redux";

import { logout } from "../actions/index";

const mapStateToProps = (state) => {
  return {
    user: state.routing.locationBeforeTransitions.state.user    
  };
};

const mapDispatchToProps = {
  logout,
};

class SignOutButton extends Component {
  onClickLogout(event) {
    this.props.logout(this.props.user.Key);
    event.preventDefault();
  }

  
  render() {
    return (
      <ul className="navbar-nav ">
        <li className="nav-item">
          <button type="button" className="btn btn-default" aria-label="Left Align" onClick={this.onClickLogout.bind(this)}>
            <span className="fa fa-sign-out" aria-hidden="true" style={{"fontSize": "40px"}}></span>
          </button>
        </li>
      </ul>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(SignOutButton);
