import React, { Component } from 'react'

import Woman from '../assets/images/woman.png'
import Men from '../assets/images/man.png'

import { Card, Avatar, PageHeader, Row, Button , Col, Divider } from 'antd';

import FriendsLists from './FriendsList';
import CommentsList from './CommentsList';


const DescriptionItem = ({ title, content }) => (
    <div
        style={{
            fontSize: 14,
            lineHeight: '22px',
            marginBottom: 7,
            color: 'rgba(0,0,0,0.65)',
        }}
    >
        <p
            style={{
                marginRight: 8,
                display: 'inline-block',
                color: 'rgba(0,0,0,0.85)',
            }}
        >
            {title}:
    </p>
        {content}
    </div>
);

const tabListNoTitle = [{
    key: 'Profile',
    tab: 'Profile',
}, {
    key: 'Friends',
    tab: 'Friends',
}, {
    key: 'Comments',
    tab: 'Comments',
}];

var user = '';

export default class UserPage extends Component {
    contentListNoTitle = {
        Profile: 
            <div className="container-fluid">
                <Row align="middle" type="flex" justify="space-between" >
                    <Col span={4} order={3}></Col>
                    <Col span={16} order={2}>
                        <div className="row align-middle">
                            <Avatar className="col-lg-5 ml-auto mr-auto align-bottom "
                                src={this.props.user.Sex === "M" ? Men : Woman}
                                style={{ width: 50, height: 50, display: 'block', margin: 'auto', position: 'relative' }}
                            />
                        </div>
                    </Col>
                    <Col span={4} order={1}></Col>
                </Row>
                <Divider type="horizontal" />
                <Row>
                    <Col span={12}><DescriptionItem title="LastName" content={this.props.user.nom} /></Col>
                    <Col span={12}><DescriptionItem title="Name" content={this.props.user.prenom} /></Col>
                    <Col span={12}><DescriptionItem title="Birth Day" content={this.props.user.DateNaiss} /></Col>
                    <Col span={12}><DescriptionItem title="Since" content={this.props.user.Depuis} /></Col>
                    <Col span={12}><DescriptionItem title="Sex" content={this.props.user.Sex} /></Col>
                </Row>
            </div>
        ,
        Friends: <p>Friends content</p>,
        Comments:<p>Comments content</p>,
    };
    constructor(props) {
        super(props)
        this.state = {
            u:null,
            noTitleKey: 'Profile',
        }
        
    }
    

    componentDidMount(){
        user = this.props.user
        this.setState({u:user})
        console.log(user)
    }
    onTabChange = (key, type) => {
        this.setState({ [type]: key });
    }

    render() {
        return (
            <div>
                <PageHeader
                    onBack={() => this.props.setCurrentPage('mur')}
                    title={this.props.user.nom + " " + this.props.user.prenom}
                    subTitle="Page Profile"
                    extra={[<Button key="1" type="primary" icon="user-add" >Add</Button>]}
                />
                <Card
                    style={{ width: '100%' }}
                    tabList={tabListNoTitle}
                    activeTabKey={this.state.noTitleKey}
                    onTabChange={(key) => { this.onTabChange(key, 'noTitleKey'); }}
                >
                    {this.contentListNoTitle[this.state.noTitleKey]}
                </Card>
            </div>
        );
    }
}