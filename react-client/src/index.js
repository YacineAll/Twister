import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import { browserHistory } from "react-router";
import { syncHistoryWithStore } from "react-router-redux";
import { routerMiddleware } from 'react-router-redux'

import {loadState, saveState} from './localStorage'

// Apply the middleware to the store
import Routes from "./routes";
import reducers from "./reducers";


const persistedState = loadState()



const middleware = routerMiddleware(browserHistory)
const createStoreWithMiddleware = applyMiddleware(thunk,middleware)(createStore);

const store = createStoreWithMiddleware(
  reducers,persistedState,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

store.subscribe( () => {
  saveState(store.getState())
})

const history = syncHistoryWithStore(browserHistory, store);

ReactDOM.render(
  <Provider store={store}>
      <Routes history={history} />
  </Provider>,
  document.querySelector(".main")
);
