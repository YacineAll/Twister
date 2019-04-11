
import React, { Component } from "react"

import SearchBar from './SearchBar';
import { Row, Col} from 'antd';

import RightContainer from "./RightContainer"
import LeftContainer from "./LeftContainer"
import CenterContainer from "./CenterContainer"
import PageProfile from './ProfilePage'
import UserPage from './UserPage';

export default class Mur extends Component {
    
    constructor(props) {
        super(props);
        this.state={
            current:'mur',
            numberOfcomments:0,
            numberOfFollowers: 0,
            User:null
        }
        this.setCurrentPage = this.setCurrentPage.bind(this)
        this.setAddComments = this.setAddComments.bind(this)
        this.getNumberOfComments = this.getNumberOfComments.bind(this)
        this.setAddFollowrs = this.setAddFollowrs.bind(this)
        this.getNumberOfFollowrs = this.getNumberOfFollowrs.bind(this)
        this.goToProfile = this.goToProfile.bind(this)
    }
    

    setCurrentPage(page){
        this.setState({current: page})
    }
    

    setAddComments(nbComments){
        this.setState({ numberOfcomments: nbComments})
    }

    setAddFollowrs(nbFollowers) {
        this.setState({ numberOfFollowers: nbFollowers })
    }

    
    getNumberOfFollowrs() {
        return this.state.numberOfFollowers
    }


    getNumberOfComments() {
        return this.state.numberOfcomments
    }

    goToProfile(user){
        if (parseInt(user.idUser) === this.props.getValues().ID){
            this.setCurrentPage("ProfilePage")
            return 
        }
        this.setState({ User: user})
        this.setCurrentPage("UserPage")
    }
    renderAffiche(){
        switch (this.state.current) {
            case "ProfilePage":
                return (
                    <PageProfile
                        setCurrentPage={this.setCurrentPage}
                        getValues={this.props.getValues}
                        setAddFollowrs={this.setAddFollowrs}                   
                    >
                    </PageProfile>
                );
            case "UserPage":
                    return(
                        <UserPage 
                            setCurrentPage={this.setCurrentPage}
                            getValues={this.props.getValues} 
                            user={this.state.User}
                        />
                    )
            default:
                return (
                    <div className="container-fluid" >
                        <div className="container-fluid">
                            <Row align="top" justify="center">
                                <SearchBar goToProfile={this.goToProfile} setLogout={this.props.setLogout} getValues={this.props.getValues} ></SearchBar>
                            </Row>
                            <Row style={{ "padding": "0px" }} type="flex" justify="center" className="container-fluid">
                                <Col span={12} push={6}>
                                    <CenterContainer 
                                        setAddComments={this.setAddComments}
                                        userName={this.state.userName}
                                        getValues={this.props.getValues}
                                    >
                                    </CenterContainer>
                                </Col>
                                <Col span={6} push={6}>
                                    <RightContainer
                                        userName={this.props.getValues().prenom +" "+ this.props.getValues().nom}
                                    ></RightContainer>
                                </Col>
                                <Col span={6} pull={18}>
                                    <LeftContainer 
                                        setCurrentPage={this.setCurrentPage}
                                        getNumberOfComments={this.getNumberOfComments}
                                        getNumberOfFollowrs={this.getNumberOfFollowrs}
                                        userName={this.props.getValues().prenom + " " + this.props.getValues().nom}                                        
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