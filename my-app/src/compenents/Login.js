import React, { Component } from "react"
import { Button,Input, Checkbox } from 'antd';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username : '',
            email :'',
            password:''
        }
    }
    
    onSubmit(){
        console.log(this.state)
        this.props.getConnected()
    }

    onChange(e){
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    render() {
        return (
            <div className="login">
                <h1>Sign In</h1>
                <hr />
                <label htmlFor="email"><b>Email</b></label>
                <Input 
                    placeholder="Enter Email" 
                    type="email" 
                    name="email" 
                    required
                    onChange={(e)=> this.onChange(e)}
                    value = {this.state.email}
                />
                <label htmlFor="psw"><b>Password</b></label>
                <Input
                    placeholder="Enter Password"
                    type="password"
                    name="password"
                    required
                    onChange={(e) => this.onChange(e)}
                    value={this.state.password}
                />
                <Button 
                        type="button" 
                        onClick={(event) => this.onSubmit()} 
                    >
                    login
                </Button>
                <Checkbox
                
                />

                
            </div>
        );
    }

}

export default Login
