
import React, { Component } from "react"

import SearchBar from './SearchBar';

export default class Profile extends Component {
    
    render() {
    return (
        <SearchBar setLogout={this.props.setLogout}>
        
        </SearchBar>
    )
}

}