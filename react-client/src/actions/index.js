import axios from "axios";
const URL = "http://localhost:8080/twister-server";

export const LOGIN = "LOGIN";
export const SIGNUP = "SIGNUP";

export function login(username, password) {
  return function (dispatch) {
    var params = new URLSearchParams();
    params.append("login", username);
    params.append("password", password);
    var request = {
      params: params,
    };
    axios
      .get(`${URL}/login`, request)
      .then((response) => {
        dispatch({ type: LOGIN, payload: response.data });
      })
      .catch((error) => {
        alert("error");
      });
  };
}

export function signup(name, lastname, email, password, sex, birthdate) {
  return function (dispatch) {
    var params = new URLSearchParams();
    params.append("nom", name);
    params.append("prenom", lastname);
    params.append("login", email);
    params.append("password", password);
    params.append("sex", sex);
    params.append("birth_date", birthdate);
    var request = {
      params: params,
    };
    axios
      .get(`${URL}/createUser`, request)
      .then((response) => {
        dispatch({ type: SIGNUP, payload: response.data });
      })
      .catch((error) => {
        alert("error");
      });
  };
}
