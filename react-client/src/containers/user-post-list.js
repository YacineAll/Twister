import React, { Component } from "react";
import { connect } from "react-redux";
import { v4 } from "node-uuid";
import UserAddPost from "./user-add-post";
import Post from "./post";

import "../style/user-list.css";

function mapStateToProps(state) {
  return {
    userdata: state.userdata,
  };
}

class UserPostList extends Component {
  render() {
    const allUserComments = [
      ...this.props.userdata.userCommentsList,
      ...this.props.userdata.userfriendsCommentsList,
    ].sort((e1, e2) => new Date(e2.date) - new Date(e1.date));
    console.log(allUserComments[0])
    return (
      <div className="UserPostList card">
        <UserAddPost />
        <div className="UserPostList card-body">
          <h4 className="UserPostList card-title">Recent Comments</h4>
          <h6 className="UserPostList card-subtitle">
            Latest Comments section by users
          </h6>
        </div>
        <div className="list-group comment-widgets m-b-20">
          {allUserComments.map((e) => {
            return (
              <Post
                author={e.nom + " " + e.prenom}
                date={e.date}
                text={e.comment}
                key={v4()}
                replies={e.replies}
                likes={e.likes}
              />
            );
          })}
        </div>
      </div>
    );
  }
}

export default connect(mapStateToProps)(UserPostList);
