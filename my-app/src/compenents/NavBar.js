import React, { Component } from "react";
import {Link} from "react-router-dom"
import { Layout} from 'antd'
import {
    Form, Icon, Input, Button,
} from 'antd';

export default class NavBar extends Component{

render(){
    return (
        <Layout>
            <Layout className="layout" style={{ float: 'right' }}>
                <Form layout="inline" >
                    <Form.Item>
                        <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />
                    </Form.Item>
                </Form>            
            </Layout>
        </Layout>

    );
}
}

