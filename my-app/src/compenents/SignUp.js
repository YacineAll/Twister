import React, { Component } from "react"
import {
    Form, Input, Checkbox, DatePicker, Button} from 'antd';


import Style from 'style-it'
import { Radio } from 'antd';

const RadioGroup = Radio.Group;

class Sign_Up extends Component {
    constructor(props) {
        super(props);
        this.state = {
            confirmDirty: false,
            autoCompleteResult: [],
        };
    }


    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                this.props.getConnected()
                this.props.setRedirectToFalse()
            }
        });
    }

    compareToFirstPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && value !== form.getFieldValue('password')) {
            callback('Two passwords that you enter is inconsistent!');
        } else {
            callback();
        }
    }



    validateToNextPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], { force: true });
        }
        callback();
    }


    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    }


    handleSelectChange = (value) => {
        console.log(value);
        this.props.form.setFieldsValue({
            note: `Hi, ${value === 'male' ? 'man' : 'lady'}!`,
        });
    }


    render() {
        const { getFieldDecorator } = this.props.form;
        
        const formItemLayout = {
            labelCol: {
                xs: { span: 24 },
                sm: { span: 8 },
            },
            wrapperCol: {
                xs: { span: 24 },
                sm: { span: 16 },
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 16,
                    offset: 8,
                },
            },
        };
        const config = {
            rules: [{ type: 'object', required: true, message: 'Please select time!' }],
        };
        return (
            <div className="container">
            <Style>
                {
                    `   
                        #title {
                            color : red;
                            text-align: center;
                        }
                        .ant-form-item-label ant-col-xs-24 ant-col-sm-8{
                            width : 0.2 %;
                        }

                    `
                }
            </Style>
                <h1 className="container" id="title"> Sign Up </h1>
                <div className="container">
                    <Form {...formItemLayout} onSubmit={this.handleSubmit}>
                        <Form.Item label="Name">
                            {
                                getFieldDecorator('Name', {
                                    rules: [{ required: true, message: 'Please input your Name!' }
                                    ],
                                })(
                                    <Input />)
                            }
                        </Form.Item>
                        <Form.Item label="LastName">
                            {
                                getFieldDecorator('LastName', {
                                    rules: [{ required: true, message: 'Please input your LastName!' }
                                    ],
                                })(
                                    <Input />)
                            }
                        </Form.Item>
                        <Form.Item label="birth date">

                                {getFieldDecorator('date-picker', config)(
                                
                                 <DatePicker />
                                )}

                        </Form.Item>
                        <Form.Item label="Gender">
                                
                                {getFieldDecorator('gender', {
                                    rules: [{ required: true, message: 'Please select your gender!' }],
                                })(
                                    <RadioGroup onChange={this.onChange} value={this.state.value}>
                                        <Radio value={1}>Female</Radio>
                                        <Radio value={2}>Male</Radio>
                                    </RadioGroup>
                                )}
                        </Form.Item>
                        <Form.Item label = "E-mail">
                            {
                                getFieldDecorator('email',{
                                    rules: [{ type: 'email', message:'The input is not valid E-mail!'},
                                            { required: true, message: 'Please input your E-mail!'}
                                           ],})(
                                    <Input/>)
                            }
                        </Form.Item>
                        <Form.Item label="Password">
                             {
                                 getFieldDecorator('password',{
                                     rules: [{required : true , message : "please input your password"},
                                         { validator: this.validateToNextPassword },],
                                 })(<Input.Password type ="password"/>)
                             }
                        </Form.Item>
                                <Form.Item label="confirm">
                                {
                                    getFieldDecorator('confirm', {
                                        rules: [{ required: true, message: "please confirm your password" },
                                        { validator: this.compareToFirstPassword },],
                                    })(<Input.Password type="password" />)
                                }
                                </Form.Item>
                            <Form.Item {...tailFormItemLayout}>
                                {getFieldDecorator('agreement', {
                                valuePropName: 'checked',
                                })(
                                <Checkbox>I have read the <a href="google.com">agreement</a></Checkbox>
                                )}
                            </Form.Item>
                            <Form.Item {...tailFormItemLayout}>
                                <Button type="primary" htmlType="submit">Register</Button>
                                <Button type="danger"onClick={(event) => this.props.setRedirectToFalse()} >Cancel</Button>
                            </Form.Item>
                        </Form>
                </div>
            </div>
            );
    }

}

const SignUp = Form.create({ name: 'register' })(Sign_Up);
export default SignUp