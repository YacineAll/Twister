import React, { Component } from "react"
import { Card, PageHeader, Icon, Avatar } from 'antd';


const {Meta} = Card;

export default class PageProfile extends Component {


    render(){
        const name = this.props.userName + " " + this.props.userLastName
        return(

            <div className="container-fluid profilePage">
                <PageHeader
                    onBack={() => this.props.setCurrentPage('mur')}
                    title={this.props.userName}
                    subTitle="Page Profile"
                />
                <div className="row align-middle">
                    <Card
                        className="ml-auto mr-auto align-bottom"
                        style={{width:500}}
                        cover={
                                <img alt="example" src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png" />
                            
                        }
                    >
                    <Meta
                            avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                            title={name}
                            description="This is the description"
                        />
                    </Card>
                </div>
            </div>
        )
    }

}