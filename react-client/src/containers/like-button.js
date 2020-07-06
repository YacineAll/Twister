import React, { Component } from "react";

class LikeButton extends Component {
  render() {
    return (
      <div className="usepost like p-2 cursor">
        <i className="usepost fa fa-thumbs-o-up"></i>
        <span className="usepost ml-1">Like</span>
      </div>
    );
  }
}

export default LikeButton;
