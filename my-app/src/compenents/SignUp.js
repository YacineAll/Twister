import React, { Component } from "react"
import { Button,Input, Checkbox } from 'antd';

class SignUp extends Component{
    constructor(props) {
        super(props);
        this.state = {
            username:'',
            email:'',
            password:'',
            isAdmin:false
        } 
    }

    onSubmit(){
        console.log(this.state)
    }
        
    onChange(e){
        
        if(e.target.name === 'isAdmin' ){
            this.setState({
                [e.target.name]: e.target.checked,
            })
        }else{
            this.setState({
                [e.target.name] : e.target.value,       
            })
        }
    }
    render() {
        return (
            <div className="signUp">
                <h1>Sign Up</h1>
                <Input 
                 name = "username" 
                 placeholder="Username"
                 onChange={(e) => this.onChange(e)} 
                 value={this.state.username}
                 />
                <Input
                    name="email"
                    type="email"
                    placeholder="email"
                    onChange={(e) => this.onChange(e)}
                    value={this.state.email}
                />
                <Input
                    name="password"
                    type="password"
                    placeholder="password"
                    onChange={(e) => this.onChange(e)}
                    value={this.state.password}
                />
                <Checkbox
                    name="isAdmin"
                    checked={this.state.isAdmin} 
                    onChange={(e) => this.onChange(e)}>
                    isAdmin?
                </Checkbox>
                <br/>
                <Button 
                    onClick={()=> this.onSubmit()} 
                    type="primary">
                    Primary
                </Button>
            </div>
        );
    }


}


export default SignUp 