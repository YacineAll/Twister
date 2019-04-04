import React, { Component } from "react"
import { Card, Statistic, Icon, Avatar, Row, Col } from 'antd';

const { Meta } = Card;
export default class LeftContainer extends Component {
    constructor(props) {
        super(props)
        this.state={
            tweetsNumber:0,
            followrs:0,
        }   
    }
    
    
    
    render() {
        var nb = this.props.getNumberOfComments()
        return (
            <div className="container-fluid">
                <div style={{ background: '#ECECEC', padding: '30px' }}>
                    <Card
                        style={{ width: 300 }}
                        cover={<img alt="example" src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png" />}
                        actions={[<Icon type="home" onClick={(event) => this.props.setCurrentPage("ProfilePage")}/>,]}
                    >
                    <Meta
                        avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                        title={this.props.userName}
                        description="This is the description"
                    />
                    <p>sex : {this.props.sex}</p>
                    <Row gutter={16}>
                        <Col span={12}>
                                <Statistic title="Tweets" value={nb} />
                        </Col> <Col span={12}>
                            <Statistic title="Followers" value={0}/>
                        </Col>
                    </Row>
                    </Card>
                </div>
            </div>
        )
    }

}