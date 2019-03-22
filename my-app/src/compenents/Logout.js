import React, { Component } from "react"

class Logout  extends Component {
    
    render() {
        return (
            
            <div className="logout">
                <button type="button" onClick={(event) => this.props.setLogout()} >logout</button>
            </div>
        );
    }
}

export default Logout