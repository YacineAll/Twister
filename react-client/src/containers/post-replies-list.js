import React, { Component } from "react";
import Post from "./post";

class PostRepliesList extends Component {
  render() {
    return (
      <div className="bg-light p-2">
        <div className="list-group">
          <div className="list-group-item">
          {this.props.replies.map((e) => {
            return (
              <Post
                author={e.nom + " " + e.prenom}
                date={e.date}
                text={e.comment}
                key={e.id}
                replies={e.replies}
              />
            );
          })}
          </div>
        </div>
      </div>
    );
  }
}

export default PostRepliesList;
