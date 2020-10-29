import axios from "axios";
import { push } from "react-router-redux";

const URL = "http://localhost:8080/twister-server";

export const LOGIN   ="LOGIN";
export const LOGOUT  ="LOGOUT";
export const SIGNUP  ="SIGNUP";
export const ADD_POST="ADD_POST";

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
      .then(async (response) => {
        if (response.data.code === -1) {
          const userfriendlist = await getFriendList(response.data.Key);
          const userfriendsCommentsList = await getFriendsComments(
            response.data.Key
          );
          const userCommentsList = await getUserComments(response.data.ID);
          dispatch({
            type: LOGIN,
            payload: {
              userfriendlist: userfriendlist,
              userfriendsCommentsList: userfriendsCommentsList,
              userCommentsList: userCommentsList,
            },
          });

          dispatch(
            push({
              pathname: "/account",
              state: {
                user: response.data,
              },
            })
          );
        }
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
        if (response.data.code === -1) {
          dispatch(push("/login"));
        }
      })
      .catch((error) => {
        alert("error");
      });
  };
}

export function logout(key) {
  return function (dispatch) {
    var params = new URLSearchParams();
    params.append("key", key);
    var request = {
      params: params,
    };
    axios
      .get(`${URL}/logout`, request)
      .then((response) => {
        dispatch({ type: LOGOUT, payload: response.data });
        if (response.data.code === -1) {
          dispatch(push({ pathname: "/login", state: {} }));
        }
      })
      .catch((error) => {
        alert("error");
      });
  };
}

async function getFriendList(key) {
  var params = new URLSearchParams();
  params.append("key", key);
  var request = {
    params: params,
  };
  let res = await axios.get(`${URL}/listerFriends`, request);
  if (res.data.code === -1) {
    return res.data;
  }
  return [];
}

async function getFriendsComments(key) {
  var params = new URLSearchParams();
  params.append("key", key);
  var request = {
    params: params,
  };
  let res = await axios.get(`${URL}/friendsComments`, request);
  if (res.data.code === -1) {
    return res.data;
  }
  return [];
}

async function getUserComments(id) {
  var params = new URLSearchParams();
  params.append("idAuthor", id);
  var request = {
    params: params,
  };
  let res = await axios.get(`${URL}/commentsAuthor`, request);
  if (res.data.code === -1) {
    return res.data.comments;
  }
  return [];
}

export function addPost(key, newComment) {
  return function (dispatch) {
    var params = new URLSearchParams();
    params.append("key", key);
    params.append("text", newComment.comment);

    var request = {
      params: params,
    };
    axios
      .get(`${URL}/addComment`, request)
      .then((response) => {
        if (response.data.code === -1) {
          newComment.id = response.data.idComment;
          dispatch({ type: ADD_POST, payload: newComment });
        }
      })
      .catch((error) => {
        alert("error");
      });
  };
}
