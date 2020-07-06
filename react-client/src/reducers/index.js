import { combineReducers } from "redux";
import { routerReducer } from "react-router-redux";
import loginReducer from "./login-reducer";

const rootReducer = combineReducers({
  routing: routerReducer,
  userdata: loginReducer
});

export default rootReducer;
