import React, { Component } from "react";
import PostRepliesList from "./post-replies-list";
import PostContent from "../components/post-content";
import CommentsButton from "../components/comments-button";
import LikeButton from "./like-button";

import AddPostReply from "./add-post-reply";

import "../style/user-post.css";

class Post extends Component {
  constructor(props) {
    super(props);
    this.state = { collapsereplies: false };
  }

  onClickComment(event) {
    event.preventDefault();
    this.setState({ collapsereplies: !this.state.collapsereplies });
  }

  renderReplies() {
    if(this.props.replies.length === 0){
      return <p >No Replies!!</p>
    }

    if (this.state.collapsereplies) {
      return <PostRepliesList replies={this.props.replies}/>;
    }
  }

  render() {
    return (
      <div className="usepost list-group-item container mt-8">
        <div className="usepost d-flex justify-content-center row">
          <div className="usepost col-md-12">
            <div className="usepost d-flex flex-column comment-section">
              <PostContent
                author={this.props.author}
                date={this.props.date}
                text={this.props.text}
              />
              <div className="usepost bg-white">
                <div className="usepost d-flex flex-row fs-12">
                  <LikeButton />
                  <CommentsButton
                    onClickComment={this.onClickComment.bind(this)}
                  />
                  <AddPostReply />
                </div>
              </div>
              {this.renderReplies()}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Post;
