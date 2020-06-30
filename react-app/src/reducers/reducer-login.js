import { LOGIN } from "../actions/index";

const initialState = { user: {} };

export default function (state = initialState, action) {
  switch (action.type) {
    case LOGIN:
      console.log("je suis la")
      return {
        ...state,
        user: action.payload,
      };
    default:
      return state;
  }
}
