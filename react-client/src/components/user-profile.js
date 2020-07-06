import React, { Component } from "react";
import "../style/user-profile.css";
class UserProfile extends Component {
  render() {
    return (
      <div className="container-fluid">
        <div className="UserProfile card">
          <div className="card-header">
            <a href="#kk">
              <h6 style={{ "fontSize": "1.2vw" }}>{this.props.nom+" "+this.props.prenom}</h6>
            </a>{" "}
          </div>
          <img
            className="rounded-circle mx-auto card-img-top"
            src="https://www.w3schools.com/w3images/team2.jpg"
            alt="John"
            style={{ width: "100%" }}
          />

          <p className="title" style={{ "fontSize": "1vw" }}>
            CEO & Founder, Example
          </p>
          <p style={{ "fontSize": "1vw" }}>Harvard University</p>
          <span>
            <p style={{ "fontSize": "1vw" }}>
              Nombre de Tweets: <a href="/">{this.props.numberOfcomments}</a>
            </p>
          </span>
          <span>
            <p style={{ "fontSize": "1vw" }}>
              Nombre d'amis': <a href="/">{this.props.numberOfFriends}</a>
            </p>
          </span>
        </div>
      </div>
    );
  }
}

export default UserProfile;
