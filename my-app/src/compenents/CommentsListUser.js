import React, { Component } from 'react'
import {
    List, Avatar,  Tooltip,Alert, Popconfirm, Icon, Button
} from 'antd';

import axios from 'axios'
import moment from 'moment';

const MyCommentsList = ({comments,onDelete}) => (
    <List
        dataSource={comments}
        header={`${comments.length} ${comments.length > 1 ? 'Post' : 'Posts'}`}
        itemLayout="horizontal"
        renderItem={item => (
            <List.Item key={item.id} 
                actions={[
                    <Popconfirm onConfirm={(event) => onDelete(item.idComment)} title="Are you sure？" icon={<Icon type="question-circle-o" style={{ color: 'red' }} />}>
                                <Button type="danger" size="small" icon="delete">Delete</Button>
                            </Popconfirm>
                        ]} 
            >
                <List.Item.Meta
                    avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                    title={item.author}
                    description={item.content}
                />
                <Tooltip title={item.datetime}>
                    <span>{item.datetime}</span>
                </Tooltip>
            </List.Item>
        )}
    />
);

export default class CommentsListUser extends Component {
    constructor(props) {
        super(props)
        this.state = {
            comments: [],
        }
        this.onDelete = this.onDelete.bind(this)
    }
    

    componentDidMount() {
        var params = new URLSearchParams();
        params.append("key", this.props.getValues().Key);
        var request = {
            params: params
        };
        axios.get('http://localhost:8080/Twitter/userComments', request)
            .then(response => {
                if (response.data.code === -1) {
                    console.log(response.data)
                    const comentaires = response.data.Comments
                    var cms = comentaires.map((comment) => { 
                        return { author: comment.nom + " " + comment.prenom, 
                            content: comment.comment, 
                            idComment: comment.id,
                            datetime: moment(comment.date, "YYYY/MM/DD HH:mm:ss").fromNow() } })

                    const sorted = cms.sort((a, b) => { return (b.datetime > a.datetime) ? -1 : 1 })
                    this.setState({ comments: sorted })
                }
            })
            .catch(error => {
                alert('erreur')
            });   
    }

    onDelete(id){
        var params = new URLSearchParams();
        params.append("key", this.props.getValues().Key);
        params.append("id", id);
        var request = {
            params: params
        };
        axios.get('http://localhost:8080/Twitter/removeComment', request)
            .then(response => {
                if (response.data.code === -1) {
                    this.setState({ comments: this.state.comments.filter((item) => { return item.idComment !== id })})
                }
            })
            .catch(error => {
                alert('erreur')
            });  
    }

    render() {
        const { comments } = this.state
        return (
            <div className="container-fluid">
                <div className="row align-middle">
                    <div className="col-lg-8 ml-auto mr-auto align-bottom">
                        {comments.length > 0
                            ? <MyCommentsList comments={comments} onDelete={this.onDelete} />
                            : (<Alert
                                message="List Empty"
                                description="Your Comments list is empty"
                                type="info"
                            />)
                        }
                    </div>
                </div>
            </div>
        );
    }
}

