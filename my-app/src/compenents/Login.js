import React, { Component } from "react"
import {
    Form, Icon, Input, Button, Checkbox,
} from 'antd';





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
            }
        })
    }

    onChange(e){
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    onChangeDate(date,dateString){
        console.log(date,dateString)
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                <h1>Sign In</h1>
                <Form onSubmit = {this.handleSubmit}>
                    <Form.Item>
                        {
                            getFieldDecorator('userName',{
                                rules: [{ required: true, message: 'Please input your username!' }],
                            })(<Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />)
                        }
                    </Form.Item>
                    <Form.Item>
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
                        <a className="login-form-goog" href="google.com">Forgot password</a>
                        <Button type="primary" htmlType="submit" className="login-form-button">
                            Log in
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        );
    }

}

const Login = Form.create({ name: 'normal_login' })(Log_in);

export default Login