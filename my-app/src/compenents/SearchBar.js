import React, { Component } from "react"


import { Layout, Input} from 'antd'
import Logout from './Logout';

import MyLogo from './Logo'

const Search = Input.Search;

const { Header } = Layout;


export default class SearchBar extends Component{



render(){
return (
    <div className="container-fluid" style={{"marginTop":"1%"}}>
        <Layout className="layout" >
            <Header style={{ "background": "rgb(0, 21, 41)" }}>
                <div className="row align-middle">    
                    <div className="logo-search-bar">
                        <MyLogo>

                        </MyLogo>
                    </div>
                    <Search
                        className="col-lg-4 ml-auto mr-auto align-bottom "
                        placeholder="search"
                        onSearch={value => console.log(value)}
                        style={{ height: 25 , "marginTop": "1%"}}
                    />
                    <div>
                        <Logout setLogout={this.props.setLogout} ></Logout>
                    </div>
                </div>
            </Header>
        </Layout>
    </div>
);
}
} 