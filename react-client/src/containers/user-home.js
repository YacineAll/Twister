import React, { Component } from "react";
import { connect } from "react-redux";

import NavBar from "./nav-bar";
import UserProfile from "../components/user-profile";
import UserPostList from "./user-post-list";
import FriendsSuggestionsList from "./friends-suggestions-list";

function mapStateToProps(state) {
  return {
    user: state.routing.locationBeforeTransitions.state.user,
    userdata : state.userdata, 
  };
}



class UserHome extends Component {
  render() {
    const { nom, prenom, Sex, DateNaiss } = this.props.user;
    const { userCommentsList, userfriendlist} = this.props.userdata;
    return (
      <div className="container-fluid">
        <NavBar />
        <div className="row">
          <div className="col-md">
            <UserProfile
              nom={nom}
              prenom={prenom}
              Sex={Sex}
              DateNaiss={DateNaiss}
              numberOfcomments = {userCommentsList.length}
              numberOfFriends = {userfriendlist.length}
            />
          </div>
          <div className="col-md">
            <UserPostList/>
          </div>
          <div className="col-md">
            <FriendsSuggestionsList/>
          </div>
        </div>
      </div>
    );
  }
}

export default connect(mapStateToProps)(UserHome);
