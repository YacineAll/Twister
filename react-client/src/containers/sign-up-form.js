import React, { Component } from "react";
import { connect } from "react-redux";
import "../style/signup-form.css";
import { signup } from "../actions/index";

const mapStateToProps = (store) => {
  return {
    user: store.userSignup.user,
  };
};

const mapDispatchToProps = {
  signup,
};

class SignUpForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      lastname: "",
      email: "",
      password: "",
      cpassword: "",
      birthdate: "",
      sex: "",
    };
  }
  handleSubmit(event) {
    if (this.state.cpassword === this.state.password) {
      this.props.signup(
        this.state.name,
        this.state.lastname,
        this.state.email,
        this.state.password,
        this.state.sex,
        this.state.birthdate
      );
    } else {
      alert("password not the same");
    }
    event.preventDefault();
  }

  handleChangeName(e) {
    this.setState({ name: e.target.value });
    e.preventDefault();
  }

  handleChangeLName(e) {
    this.setState({ lastname: e.target.value });
    e.preventDefault();
  }

  handleChangeEmail(e) {
    this.setState({ email: e.target.value });
    e.preventDefault();
  }

  handleChangePassword(e) {
    this.setState({ password: e.target.value });
    e.preventDefault();
  }

  handleChangeCPassword(e) {
    this.setState({ cpassword: e.target.value });
    e.preventDefault();
  }

  handleChangeBDate(e) {
    var date = new Date(e.target.value);
    var d = date.getDate();
    var m = date.getMonth() + 1;
    var y = date.getFullYear();
    var dateString =
      (d <= 9 ? "0" + d : d) + "/" + (m <= 9 ? "0" + m : m) + "/" + y;
    this.setState({ birthdate: dateString });
    e.preventDefault();
  }

  handleChangeMale(e) {
    this.setState({ sex: "M" });
  }

  handleChangeFemale(e) {
    this.setState({ sex: "F" });
  }

  render() {
    return (
      <div>
        <div className="signupform container-fluid">
          <div className="signupform container-fluid">
            <div className="signupform row main-content bg-success text-center">
              <div className="signupform col-md-4 text-center company__info">
                <div className="signupform company__logo">
                  <img
                    className="signupform rounded mx-auto d-block logo"
                    src="https://logos.mybrandnewlogo.com/logos/Az2kxWD0XwI4W8pnBDvk/images/logo-Pwkxq5mKp1izl52XkYL9SD2p2x40.png"
                    alt="logo"
                    width="300"
                    height="400"
                  />
                </div>
              </div>
              <div className="signupform col-md-8 col-xs-12 col-sm-12 login_form ">
                <div className="signupform container-fluid">
                  <div className="signupform row">
                    <h2>SignUP</h2>
                  </div>
                  <div className="signupform row">
                    <form
                      onSubmit={this.handleSubmit.bind(this)}
                      method="POST"
                      className="signupform form-group"
                    >
                      <div className="signupform row">
                        <input
                          required
                          type="text"
                          name="first_name"
                          id="first_name"
                          className="signupform form__input"
                          placeholder="First Name"
                          onChange={(e) => this.handleChangeName(e)}
                        />
                      </div>
                      <div className="signupform row">
                        <input
                          required
                          type="text"
                          name="last_name"
                          id="last_name"
                          className="signupform form__input"
                          placeholder="Last Name"
                          onChange={(e) => this.handleChangeLName(e)}
                        />
                      </div>
                      <div className="signupform row birthdate">
                        <label htmlFor="start">Birth Date:</label>
                        <input
                          type="date"
                          id="start"
                          name="trip-start"
                          min="1900-01-01"
                          max="2020-12-31"
                          onChange={(e) => this.handleChangeBDate(e)}
                        />
                      </div>
                      <div className="signupform row">
                        <input
                          type="radio"
                          id="male"
                          name="gender"
                          value="male"
                          onChange={(e) => this.handleChangeMale(e)}
                        />{" "}
                        <label className="signupform sex">Male</label>
                        <input
                          type="radio"
                          id="female"
                          name="gender"
                          value="female"
                          onChange={(e) => this.handleChangeFemale(e)}
                        />{" "}
                        <label className="signupform sex">Female</label>
                      </div>
                      <div className="signupform row">
                        <input
                          required
                          type="email"
                          name="email"
                          id="email"
                          className="signupform form__input"
                          placeholder="email"
                          onChange={(e) => this.handleChangeEmail(e)}
                        />
                      </div>
                      <div className="signupform row">
                        <input
                          onChange={(e) => this.handleChangePassword(e)}
                          required
                          type="password"
                          name="password"
                          id="password"
                          className="signupform form__input"
                          placeholder="Password"
                        />
                      </div>
                      <div className="signupform row">
                        <input
                          onChange={(e) => this.handleChangeCPassword(e)}
                          required
                          type="password"
                          name="ConfirmPassword"
                          id="ConfirmPassword"
                          className="signupform form__input"
                          placeholder="Confirm Password"
                        />
                      </div>
                      <div className="signupform row">
                        <input
                          type="submit"
                          value="Submit"
                          className="signupform btn"
                        />
                      </div>
                    </form>
                  </div>
                  <div className="signupform row have_account">
                    <p>
                      You have an account? <a href="/login">Login Here</a>
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

export default connect(mapStateToProps, mapDispatchToProps)(SignUpForm);
