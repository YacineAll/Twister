import React, { Component } from "react";
import { connect } from "react-redux";
import { addPost } from "../actions/index";

const mapDispatchToProps = {
  addPost,
};
function mapStateToProps(state) {
  return {
    user: state.routing.locationBeforeTransitions.state.user,
    userCommentsList: state.userdata.userfriendsCommentsList,
  };
}

class UserAddPost extends Component {
  constructor(props) {
    super(props);
    this.state = { text: "" };
  }

  onChangeText(event) {
    this.setState({ text: event.target.value });
    event.preventDefault();
  }

  onClickAddPost(event) {
    if (this.state.text.length > 0) {
      const comment = {
        nom: this.props.user.nom,
        prenom: this.props.user.prenom,
        date: new Date(),
        comment: this.state.text,
        id: this.props.userCommentsList.length + 1,
        replies: [],
      };
      this.props.addPost(this.props.user.Key, comment);
    }
    event.preventDefault();
  }

  render() {
    return (
      <div className="bg-light p-2">
        <div className="d-flex flex-row align-items-start">
          <img
            className="rounded-circle"
            src="https://i.imgur.com/RpzrMR2.jpg"
            width="40"
            alt=""
          />
          <textarea
            className="form-control ml-1 shadow-none textarea"
            onChange={this.onChangeText.bind(this)}
          ></textarea>
        </div>
        <div className="mt-2 text-right">
          <button
            className="btn btn-primary btn-sm shadow-none"
            type="button"
            onClick={this.onClickAddPost.bind(this)}
          >
            Post comment
          </button>
        </div>
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(UserAddPost);
