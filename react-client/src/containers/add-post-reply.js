import React, { Component } from "react";

class AddPostReply extends Component {
  onClickPostComment(event) {
    console.log("yacine");
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <div className="usepost like p-2 cursor">
          <i className="usepost fa fa-reply"></i>
          <span
            className="usepost ml-1"
            data-toggle="modal"
            data-target="#exampleModalLong"
          >
            Reply
          </span>
        </div>
        <div
          className="modal fade"
          id="exampleModalLong"
          tabIndex="-1"
          role="dialog"
          aria-labelledby="exampleModalLongTitle"
          aria-hidden="true"
        >
          <div className="modal-dialog" role="document">
            <div className="modal-content">
              <div className="modal-body bg-light p-2">
                <div className="d-flex flex-row align-items-start">
                  <img
                    className="rounded-circle"
                    src="https://i.imgur.com/RpzrMR2.jpg"
                    width="40"
                    alt=""
                  />
                  <textarea className="form-control ml-1 shadow-none textarea"></textarea>
                </div>
                <div className="mt-2 text-right">
                  <button
                    className="btn btn-primary btn-sm shadow-none"
                    type="button"
                    data-dismiss="modal"
                    onClick={this.onClickPostComment.bind(this)}
                  >
                    Post comment
                  </button>
                  <button
                    className="btn btn-outline-primary btn-sm ml-1 shadow-none"
                    type="button"
                    data-dismiss="modal"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default AddPostReply;
