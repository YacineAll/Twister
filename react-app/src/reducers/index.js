import { combineReducers } from "redux";

import ReducerLogin from "./reducer-login";
import ReducerSignup from "./reducer-signup";

const rootReducer = combineReducers({
    userReducer: ReducerLogin,
    userSignup: ReducerSignup,
})

export default rootReducer;

