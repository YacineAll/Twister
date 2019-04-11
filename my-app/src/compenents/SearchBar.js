import React, { Component } from "react"
import { Layout, Mention, Avatar} from 'antd'
import axios from 'axios'

import Logout from './Logout';
import MyLogo from './Logo'


const { Header } = Layout;


const Nav = Mention.Nav;


var users = []

export default class SearchBar extends Component{
    constructor(props) {
        super(props)
        this.state = {
            suggestions: [],
        }
        this.goTo = this.goTo.bind(this)
    }
    
    componentDidMount(){
        axios.get('http://localhost:8080/Twitter/getuserslist')
            .then(response => {
                if (response.data.code === -1) {
                    users = response.data.Users.map((user) => { return { 
                        nom: user.nom , 
                        prenom: user.prenom,
                        type: user.Sex === 'M' ? 'Man' : 'Woman', 
                        icon: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
                        idUser:user.id,
                        login:user.login,
                        DateNaiss: user.DateNaiss,
                        Sex:user.Sex,
                        Depuis: user.Depuis
                    }})
                } 
            })
            .catch(error => {
                alert('erreur')
            });
    }

    onSearchChange = (value) => {
        const searchValue = value.toLowerCase();
        const filtered = users.filter(item => (item.nom + " " + item.prenom).toLowerCase().indexOf(searchValue) !== -1);
        const suggestions = filtered.map(suggestion => (
            <Nav
                value={suggestion.nom + " " + suggestion.prenom}
                data={suggestion}
                disabled={suggestion.disabled}
            >
                <Avatar
                    src={suggestion.icon}
                    size="small"
                    style={{
                        width: 14, height: 14, marginRight: 8, top: -1, position: 'relative',
                    }}
                />
                {suggestion.nom + " " + suggestion.prenom} - {suggestion.type}
            </Nav>
        ));
        this.setState({ suggestions });
    }

    goTo(name){
        const user = users.find((user) => {
            return (user.nom + " " + user.prenom) === name;
        })
        this.props.goToProfile(user)
    }


render(){
return (
    <div className="container-fluid" style={{"marginTop":"1%"}}>
        <Layout className="layout" >
            <Header style={{ "background": "rgb(0, 21, 41)" }}>
                <div className="row align-middle">    
                    <div className="logo-search-bar">
                        <MyLogo/>
                    </div>
                    <Mention
                        prefix=''
                        className="col-lg-4 ml-auto mr-auto align-bottom "
                        placeholder="search"
                        style={{ height: 25 , "marginTop": "0.6%"}}
                        suggestions={this.state.suggestions}
                        onSearchChange={this.onSearchChange}
                        onSelect={this.goTo}
                    />
                    <div>
                        <Logout getValues={this.props.getValues} setLogout={this.props.setLogout} ></Logout>
                    </div>
                </div>
            </Header>
        </Layout>
    </div>
);
}
} 