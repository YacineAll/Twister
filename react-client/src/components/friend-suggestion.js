import React, { Component } from 'react';

class FriendSuggestion extends Component {
    render() {
        return (
            <div class="nearby-user">
            <div className="row list-group-item">
              <div class="row">
                <div class="col-md-2 col-sm-2">
                  <img
                    src="https://bootdey.com/img/Content/avatar/avatar7.png"
                    alt="user"
                    class="profile-photo-lg"
                  />
                </div>
                <div class="col-md-7 col-sm-7">
                  <h5>
                    <a href="#" class="profile-link">
                      Sophia Page
                    </a>
                  </h5>
                  <p>Software Engineer</p>
                  <p class="text-muted">500m away</p>
                </div>
                <div class="col-md-3 col-sm-3">
                  <button class="btn btn-primary pull-right">Add</button>
                </div>
              </div>
            </div>
          </div>
        );
    }
}

export default FriendSuggestion;