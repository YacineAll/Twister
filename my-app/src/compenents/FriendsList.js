import React, {Component} from 'react'
import {
    List, Avatar, Popconfirm, Button, Icon, Alert} from 'antd';

import axios from 'axios'

const MyFriendsList = ({ friends, onDelete }) => (
    <List
        dataSource={friends}
        header={`${friends.length} ${friends.length > 1 ? 'Friend' : 'Friends'}`}
        itemLayout="horizontal"
        renderItem={item => (
            <List.Item key={item.id}
                actions={[
                    <Popconfirm onConfirm={(event) => onDelete(item.idFriend)} title="Are you sure？" icon={<Icon type="question-circle-o" style={{ color: 'red' }} />}>
                        <Button type="danger" size="small" icon="user-delete">Delete</Button>
                    </Popconfirm>,
                ]}
            >
                <List.Item.Meta
                    avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                    title={item.name}
                />
            </List.Item>
        )}
    />
);

export default class FriendsLists extends Component {
    constructor(props) {
        super(props)
        this.state = {
            friends: [],
        }
        this.onDelete=this.onDelete.bind(this)
    }
    

    componentDidMount() {
        this.setState({ friends: this.props.getFriends() })                    
    }

    onDelete(id) {
        var params = new URLSearchParams();
        params.append("key", this.props.getValues().Key);
        params.append("id_friend", id);
        var request = {
            params: params
        };
        axios.get('http://localhost:8080/Twitter/removeFriend', request)
        .then(response => {
            if (response.data.code === -1) {
                    this.props.deleteFriend(id)
                    this.setState({ friends: this.state.friends.filter((item) => { return item.idFriend !== id }) })
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
                        ? <MyFriendsList friends={friends} onDelete={this.onDelete} />
                            
                        : (<Alert
                                message="List Empty"
                                description="Your friends list is empty"
                                type="info"
                            />
                            )
                        }
                    </div>
                </div>
            </div>
        );
    }
}

