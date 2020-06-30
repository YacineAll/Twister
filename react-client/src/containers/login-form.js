import React, { Component } from "react";
import "../style/login-form.css";
import { connect } from "react-redux";
import { login } from "../actions/index";

const mapStateToProps = (store) => {
  return {
    user: store.userReducer.user,
  };
};

const mapDispatchToProps = {
  login,
};

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "", rememberme: false };
  }

  handleSubmit(event) {
    this.props.login(this.state.username, this.state.password);
    event.preventDefault();
  }

  handleChangeUsername(e) {
    this.setState({ username: e.target.value });
  }

  handleChangePassword(e) {
    this.setState({ password: e.target.value });
  }

  handleChangeRememberMe(e) {
    this.setState({ rememberme: !this.state.rememberme });
    console.log(this.state.rememberme);
  }

  render() {
    return (
      <div className="loginform container-fluid">
        <div>
          <div className="loginform container-fluid">
            <div className="loginform row main-content bg-success text-center">
              <div className="loginform col-md-4 text-center company__info">
                <div className="loginform company__logo">
                  <img
                    className="loginform rounded mx-auto d-block logo"
                    src="https://logos.mybrandnewlogo.com/logos/Az2kxWD0XwI4W8pnBDvk/images/logo-Pwkxq5mKp1izl52XkYL9SD2p2x40.png"
                    alt="logo"
                    width="300"
                    height="400"
                  />
                </div>
              </div>
              <div className="loginform col-md-8 col-xs-12 col-sm-12 login_form ">
                <div className="loginform container-fluid">
                  <div className="loginform row">
                    <h2>Log In</h2>
                  </div>
                  <div className="loginform row">
                    <form onSubmit={this.handleSubmit.bind(this)} method="POST" className="form-group">
                      <div className="loginform row">
                        <input
                          required
                          type="email"
                          name="username"
                          id="username"
                          className="loginform form__input"
                          placeholder="Username"
                          onChange={(e) => this.handleChangeUsername(e)}
                        />
                      </div>
                      <div className="loginform row">
                        <input
                          required
                          onChange={(e) => this.handleChangePassword(e)}
                          type="password"
                          name="password"
                          id="password"
                          className="loginform form__input"
                          placeholder="Password"
                        />
                      </div>
                      <div className="loginform row">
                        <input
                          onChange={(e) => this.handleChangeRememberMe(e)}
                          type="checkbox"
                          name="remember_me"
                          id="remember_me"
                          className="loginform "
                        />
                        <label className="loginform remember_me" htmlFor="remember_me">
                          Remember Me!
                        </label>
                      </div>
                      <div className="loginform row">
                        <input type="submit" value="Submit" className="loginform btn" />
                      </div>
                    </form>
                  </div>
                  <div className="loginform row have_account">
                    <p>
                      Don't have an account? <a href="/signup">Register Here</a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);
