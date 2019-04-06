
import React, { Component } from "react"

import SearchBar from './SearchBar';
import { Row, Col} from 'antd';

import RightContainer from "./RightContainer"
import LeftContainer from "./LeftContainer"
import CenterContainer from "./CenterContainer"
import PageProfile from './ProfilePage'



export default class Mur extends Component {

    constructor(props) {
        super(props);
        this.state={
            current:'mur',
            numberOfcomments:0,
            userName: this.props.getValues().prenom,
            userLastName: this.props.getValues().nom,
            sex: this.props.getValues().Sex,
            DateNaiss: this.props.getValues().DateNaiss,
            Depuis: this.props.getValues().Depuis
        }
        this.setCurrentPage = this.setCurrentPage.bind(this)
        this.setAddComments = this.setAddComments.bind(this)
        this.getNumberOfComments = this.getNumberOfComments.bind(this)
    }
    

    setCurrentPage(page){
        this.setState({current: page})
    }

    setAddComments(){
        var number = this.state.numberOfcomments + 1
        this.setState({ numberOfcomments: number})
    }
    
    getNumberOfComments() {
        return this.state.numberOfcomments
    }

    renderAffiche(){
        switch (this.state.current) {
            case "ProfilePage":
                return (
                    <PageProfile
                        setCurrentPage={this.setCurrentPage}
                        userName={this.state.userName}
                        userLastName={this.state.userLastName}
                    >
                    </PageProfile>
                );
            default:
                return (
                    <div className="container-fluid" >
                        <div className="container-fluid">
                            <Row align="top" justify="center">
                                <SearchBar setLogout={this.props.setLogout} getValues={this.props.getValues} ></SearchBar>
                            </Row>
                            <Row style={{ "padding": "0px" }} type="flex" justify="center" className="container-fluid">
                                <Col span={12} push={6}>
                                    <CenterContainer 
                                        setAddComments={this.setAddComments}
                                        userName={this.state.userName}
                                    >
                                    </CenterContainer>
                                </Col>
                                <Col span={6} push={6}>
                                    <RightContainer
                                        userName={this.state.userName}
                                    ></RightContainer>
                                </Col>
                                <Col span={6} pull={18}>
                                    <LeftContainer 
                                        setCurrentPage={this.setCurrentPage}
                                        getNumberOfComments={this.getNumberOfComments}
                                        userName={this.state.userName}
                                        sex={this.state.sex}
                                    
                                    >

                                    </LeftContainer>
                                </Col>
                            </Row>

                        </div>
                    </div>
                );
        }
    }
    
    render() {
    return this.renderAffiche()
}

}