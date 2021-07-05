import React, {Fragment} from "react";
import Header from '../components/Header';
import LeftNav from '../components/LeftNav';
import RightChat from '../components/RightChat';
import AppFooter from '../components/AppFooter';
import PopupChat from '../components/PopupChat';

import ProfileDetail from '../components/ProfileDetail';
import ProfilePhoto from '../components/ProfilePhoto';
import ProfileCardOne from '../components/ProfileCardOne';
import CreatePost from '../components/CreatePost';
import Events from '../components/Events';
import PostView from '../components/PostView';
import Load from '../components/Load';


const GroupPage = () => (
  <Fragment>
    <Header/>
    <LeftNav/>
    <RightChat/>

    <div className="main-content right-chat-active">
      <div className="middle-sidebar-bottom">
        <div className="middle-sidebar-left pe-0">
          <div className="row">
            <div className="col-xl-4 col-xxl-3 col-lg-4 pe-0">
              <ProfileCardOne/>
              <ProfileDetail/>
              <ProfilePhoto/>
              <Events/>
            </div>
            <div className="col-xl-8 col-xxl-9 col-lg-8 mt-3">
              <CreatePost/>
              <PostView id="32" postvideo="" postimage="post.png" avater="user.png" user="Surfiya Zakir" time="22 min ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <PostView id="31" postvideo="" postimage="post.png" avater="user.png" user="David Goria" time="22 min ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <PostView id="33" postvideo="" postimage="post.png" avater="user.png" user="Anthony Daugloi" time="2 hour ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <Load/>
            </div>
          </div>
        </div>
      </div>
    </div>

    <PopupChat/>
    <AppFooter/>

  </Fragment>
);

export default GroupPage;