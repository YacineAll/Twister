import { LOGIN } from "../actions/index";
import { ADD_POST } from "../actions/index";

const initialState = {};

export default function (state = initialState, action) {
  switch (action.type) {
    case LOGIN:
      return {
        ...action.payload,
      };
    case ADD_POST:
      return {
        userfriendlist: state.userfriendlist,
        userfriendsCommentsList: state.userfriendsCommentsList,
        userCommentsList: [...state.userCommentsList, action.payload],
      };
    default:
      return state;
  }
}
