import React, { Component } from 'react'

import { Card, PageHeader, Button } from 'antd';


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

const contentListNoTitle = {
    Profile: <p>Profile content</p>,
    Friends: <p>Friends content</p>,
    Comments:<p>Comments content</p>,
};

export default class UserPage extends Component {
    state = {
        noTitleKey: 'Profile',
    }

    onTabChange = (key, type) => {
        console.log(key, type);
        this.setState({ [type]: key });
    }

    render() {
        return (
            <div>
                <PageHeader
                    onBack={() => this.props.setCurrentPage('mur')}
                    title="User"
                    subTitle="Page Profile"
                    extra={[
                        <Button key="1" type="primary" icon="user-add" >
                            Add
                        </Button>,
                    ]}
                />
                <Card
                    style={{ width: '100%' }}
                    tabList={tabListNoTitle}
                    activeTabKey={this.state.noTitleKey}
                    onTabChange={(key) => { this.onTabChange(key, 'noTitleKey'); }}
                >
                    {contentListNoTitle[this.state.noTitleKey]}
                </Card>
            </div>
        );
    }
}