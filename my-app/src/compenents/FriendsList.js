import React, {Component} from 'react'
import {
    List,  Avatar, } from 'antd';

import axios from 'axios'

const MyFriendsList = ({ friends }) => (
    <List
        dataSource={friends}
        header={`${friends.length} ${friends.length > 1 ? 'Post' : 'Posts'}`}
        itemLayout="horizontal"
        renderItem={item => (
            <List.Item key={item.id}>
                <List.Item.Meta
                    actions={[<a href="#/">View Profile</a>, <a href="#/">Delete</a>]}
                    avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                    title={item.name}
                />
            </List.Item>
        )}
    />
);

export default class FriendsLists extends Component {
    state = {
        friends: [],
    }

    componentDidMount() {
        var params = new URLSearchParams();
        params.append("key", this.props.getValues().Key);
        var request = {
            params: params
        };
        axios.get('http://localhost:8080/Twitter/listerFriends', request)
            .then(response => {
                console.log(response.data)
                if (response.data.code === -1) {
                    const Myfriends = response.data.amis
                    var cms = Myfriends.map((friend) => {
                         return { 
                             name: friend.nom + " " + friend.prenom, 
                             id: friend.id,  
                            } 
                        })
                    this.setState({ friends: cms })
                }
            })
            .catch(error => {
                alert('erreur')
            });   
    }

    render() {
        const { friends} = this.state
        return (
            <div className="container-fluid">
                <div className="row align-middle">
                    <div className="col-lg-8 ml-auto mr-auto align-bottom">
                        {friends.length > 0 
                        ? <MyFriendsList friends={friends} />
                        :(<p>Liste d'amis vide</p>)
                        }
                    </div>
                </div>
            </div>
        );
    }
}

