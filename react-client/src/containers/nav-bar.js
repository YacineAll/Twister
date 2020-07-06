import React, { Component } from "react";
import "../style/nav-bar.css";
import SearchNavBar from "./search-nav-bar";
import SignOutButton from "./signout-button";
class NavBar extends Component {
  render() {
    return (
      <div className="container">
        <nav
          className="navbar navbar-icon-top navbar-expand-lg navbar-light"
          style={{ "backgroundColor": "#283e4a" }}
        >
          <a className="navbar-brand" href="/account">
            <img
              className="rounded mx-auto d-block logo"
              src="https://logos.mybrandnewlogo.com/logos/jjkr4mVLKYt0nZAAkEBo/images/logo-go4l77kkWgIEKx7J8qkLUl8xgzL0.png"
              alt="logo"
              width="50px"
              height="50px"
            />
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <SearchNavBar />
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <a className="nav-link" href="/account">
                  <i className="fa fa-home"></i>
                  Home
                  <span className="sr-only">(current)</span>
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#/">
                  <i className="fa fa-users">
                    {/* <span className="badge badge-danger">11</span> */}
                  </i>
                  Friends
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#/">
                  <i className="fa fa-comment" aria-hidden="true">
                    {/* <span className="badge badge-warning">11</span> */}
                  </i>
                  Posts
                </a>
              </li>
            </ul>
            <SignOutButton />
          </div>
        </nav>
      </div>
    );
  }
}

export default NavBar;
