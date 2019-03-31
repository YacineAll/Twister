import React, { Component } from "react"

import { Button,Icon } from 'antd' 



const IconFont = Icon.createFromIconfontCN({
    scriptUrl: '//at.alicdn.com/t/font_8d5l8fzk5b87iudi.js',
});

class Logout  extends Component {

    
    render() {
        return (
            <div >
                <Button type="primary" onClick={(event) => this.props.setLogout()} >
                    <IconFont type="icon-tuichu" style={{ fontSize: '26px'}} />
                </Button>
            </div>
        );
    }
}

export default Logout