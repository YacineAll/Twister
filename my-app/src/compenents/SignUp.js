import React, { Component } from "react"
import {
    Form, Input, Checkbox, DatePicker, Button, Select} from 'antd';


const { Option } = Select;

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
            <div className="SignUp">
            <h1> Sign Up </h1>
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
                            <Select
                                placeholder="Select a option and change input text above"
                            >
                                <Option value="male">male</Option>
                                <Option value="female">female</Option>
                            </Select>
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
                                     {validator : this.validateToNextPassword },] , })(<Input type ="password"/>)
                     }
                </Form.Item>
                <Form.Item label="confirm">
                    {
                        getFieldDecorator('confirm', {
                            rules: [{ required: true, message: "please confirm your password" },
                            { validator: this.compareToFirstPassword },],
                        })(<Input type="password" />)
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
                </Form.Item>
            </Form>
            </div>
            );
    }

}

const SignUp = Form.create({ name: 'register' })(Sign_Up);
export default SignUp