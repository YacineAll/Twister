import React, { Component } from "react";
import { connect } from "react-redux";
import "../style/suggestions-friend-list.css";
import FriendSuggestion from  '../components/friend-suggestion'
function mapStateToProps(state) {
  return {};
}

class FriendsSuggestionsList extends Component {
  render() {
    return (
      <div class="container-fluid list-group">
        <div class="people-nearby">
          <FriendSuggestion/>
          <FriendSuggestion/>
          <FriendSuggestion/>
        </div>
      </div>
    );
  }
}

export default connect(mapStateToProps)(FriendsSuggestionsList);
