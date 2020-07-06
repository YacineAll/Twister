import React, { Component } from 'react';

class CommentsButton extends Component {
    render() {
        return (
            <div className="usepost like p-2 cursor" onClick={this.props.onClickComment}>
                    <i className="usepost fa fa-commenting-o"></i>
                    <span className="usepost ml-1">Comment</span>
            </div>
        );
    }
}

export default CommentsButton;