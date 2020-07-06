import React, { Component } from "react";
import Moment from 'react-moment';

class PostContent extends Component {
  render() {
    const stylename = () =>{
      if(true){
        return {"color":"black"}
      }else{
        return {"color":"#007bff"}
      }
    }
    return (
      <div className="usepost bg-white p-2">
        <div className="usepost d-flex flex-row user-info">
          <img
            className="usepost rounded-circle"
            src="https://i.imgur.com/RpzrMR2.jpg"
            width="40"
            alt=""
          />
          <div className="usepost d-flex flex-column justify-content-start ml-2">
            <a href="/">
              <span className="usepost d-block font-weight-bold name" style={stylename()} >
                {this.props.author}
              </span>
            </a>
            <span className="usepost date text-black-50">
              <Moment fromNow>{this.props.date}</Moment>
            </span>
          </div>
        </div>
        <div className="usepost mt-2">
          <p className="usepost comment-text">
            {this.props.text}
          </p>
        </div>
      </div>
    );
  }
}

export default PostContent;
