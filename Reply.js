import React, { Component } from "react"

import {
    Comment, Icon, Tooltip, Avatar
} from 'antd';


export class Reply extends Component {
    state = {
        likes: 0,
        dislikes: 0,
        action: null,
        value: '',
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
        ];
        return (
            <Comment
                actions={actions}
                author={<a href="/">{this.props.author}</a>}
                avatar={(
                    <Avatar
                        src={this.props.avatar}
                        alt={this.props.author}
                    />
                )}
                content={this.props.content}
                datetime={(
                    <Tooltip title={this.props.datetime}>
                        <span>{this.props.datetime}</span>
                    </Tooltip>
                )}
            >
            </Comment>
        )
    }


}