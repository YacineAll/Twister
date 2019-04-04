import React, { Component } from "react"

import {
    Comment, Icon, Tooltip, Avatar, List,Form,Button,Input
} from 'antd';
import moment from 'moment';

const Replies = ({ comments }) => (
    <List
        dataSource={comments}
        header={`${comments.length} ${comments.length > 1 ? 'Reply' : 'Replies'}`}
        itemLayout="horizontal"
        renderItem={props => <Post {...props} />}
    />
);


export default class Post extends Component {
    state = {
        likes: 0,
        dislikes: 0,
        action: null,
        comments: [],
        submitting: false,
        value: '',
    }


    handleSubmit = () => {
        if (!this.state.value) {
            return;
        }

        this.setState({
            submitting: true,
        });

        setTimeout(() => {
            this.setState({
                submitting: false,
                value: '',
                comments: [
                    {
                        author: 'Han Solo',
                        avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
                        content: <p>{this.state.value}</p>,
                        datetime: Date.now(),
                    },
                    ...this.state.comments,
                ],
            });
        }, 100);
    }

    handleChange = (e) => {
        this.setState({
            value: e.target.value,
        });
    }

    like = () => {
        this.setState({
            likes: 1,
            dislikes: 0,
            action: 'liked',
        });
    }

    dislike = () => {
        this.setState({
            likes: 0,
            dislikes: 1,
            action: 'disliked',
        });
    }

    render() {
        const { likes, dislikes, action } = this.state;
        const { comments, submitting, value } = this.state;

        const actions = [
            <span>
                <Tooltip title="Like">
                    <Icon
                        type="like"
                        theme={action === 'liked' ? 'filled' : 'outlined'}
                        onClick={this.like}
                    />
                </Tooltip>
                <span style={{ paddingLeft: 8, cursor: 'auto' }}>
                    {likes}
                </span>
            </span>,
            <span>
                <Tooltip title="Dislike">
                    <Icon
                        type="dislike"
                        theme={action === 'disliked' ? 'filled' : 'outlined'}
                        onClick={this.dislike}
                    />
                </Tooltip>
                <span style={{ paddingLeft: 8, cursor: 'auto' }}>
                    {dislikes}
                </span>
            </span>,
            <TextAreaReply onChange={this.handleChange} onSubmit={this.handleSubmit} submitting={submitting} value={value}></TextAreaReply>,
        ];

        return (
            <Comment
                actions={actions}
                author={<a href="/">Han Solo</a>}
                avatar={(
                    <Avatar
                        src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                        alt="Han Solo"
                    />
                )}
                content={this.props.content}
                datetime={(
                    <Tooltip title={moment().format('YYYY-MM-DD HH:mm:ss')}>
                        <span>{moment().fromNow()}</span>
                    </Tooltip>
                )}
            >
            {comments.length > 0 && <Replies comments={comments} />}
            </Comment>
        );
    }
}

class TextAreaReply extends Component{
    constructor(props) {
        super(props);
        this.state={
            visible:false,
        }
        this.setOnVisible=this.setOnVisible.bind(this)
        this.onClick  = this.onClick.bind(this)
    }

    onClick(){
        this.props.onSubmit()
        this.setState({visible:false})
    }

    setOnVisible(){
        this.setState({ visible: true })
    }

    render(){
        const TextArea = Input.TextArea;
        
        return(
                <span>
                    {this.state.visible ? (
                        <div>
                            <Form.Item>
                                <TextArea rows={4} onChange={this.props.onChange} value={this.props.value} />
                            </Form.Item>
                            <Form.Item>
                                <Button
                                    htmlType="submit"
                                    loading={this.props.submitting}
                                    onClick={this.onClick}
                                >
                                    Reply
                                </Button>
                            </Form.Item>
                        </div>
                    ) : (
                        <span onClick={this.setOnVisible} >
                        reply
                        </span>
                    )}
                </span>

        );
    }
    
}