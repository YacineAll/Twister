import React, { Component } from "react"
import {
    Form, Icon, Input, Button, Checkbox,
} from 'antd';

import Style from 'style-it'




class Log_in extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username : '',
            email :'',
            password:''
        }
    }
    

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err,values) => {
            if(!err){
                console.log('Received values of form: ', values)
                this.props.getConnected()
                this.setRedirect()
            }
        })
    }

    handleSignUpOnclick(){
        console.log("yacine")
    }

    render() {
        const { getFieldDecorator } = this.props.form;

        return (
            <div className="container">
                <Style>
                    {
                        `
                        @import url('https://fonts.googleapis.com/css?family=Open+Sans:400,700');
                        @import url('https://fonts.googleapis.com/css?family=Merriweather:400,700');
                        
                        @import 'antd/dist/antd.css';
                        
                        Form {
                            border: 3px solid #f1f1f1;
                            margin : 50px;

                        }

                        Input[type=text], Input[type=password] {
                            width: 100%;
                            padding: 12px 20px;
                            margin: 8px 0;
                            display: inline-block;
                            border: 1px solid #ccc;
                            box-sizing: border-box;
                        }
                        
                        Button {
                            background-color: #2c8def;
                            color: white;
                            margin: 8px 0;
                            padding: 14px 20px;
                            cursor: pointer;
                            border: none;
                            width: 100%;
                        }

                        Button:hover {
                            opacity: 0.8;
                        }

                        .title {
                            text-align: center;
                            margin: 24px 0 12px 0;
                            font-family: "Merriweather", serif;
                            font-size: 32px;
                        }
                        
                        .container {
                            padding: 16px;
                        }
                        
                        span.psw {
                            float: left;
                            padding-top: 16px;
                        }
                        @media screen and (max-width: 300px) {
                            span.psw {
                                display: block;
                                float: none;
                        }


                        `
                    }
                </Style>
                <div className="title">
                    <h1 >Sign In</h1>
                </div>
                <Form onSubmit={this.handleSubmit} className="container" >
                    <Form.Item >
                        {
                            getFieldDecorator('userName',{
                                rules: [{ required: true, message: 'Please input your username!' }],
                            })(<Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />)
                        }
                    </Form.Item >
                    <Form.Item >
                        {
                            getFieldDecorator('password',{
                                rules: [{ required: true, message: 'Please input your Password!' }],
                            })(<Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />)
                        }
                    </Form.Item>
                    <Form.Item>
                        {
                            getFieldDecorator('remember',{
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>Remember me</Checkbox>
                            )
                        }
                        <Button type="primary" htmlType="submit">
                            Sign In
                        </Button>
                        <span className="psw">Forgot <a href="google.com">password?</a></span>
                        <br/>
                        <Button type="second" onClick={(event) => this.props.setRedirect()} >
                                Sign Up
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        );
    }

}

const Login = Form.create({ name: 'normal_login' })(Log_in);

export default Login