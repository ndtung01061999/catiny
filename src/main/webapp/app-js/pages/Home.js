import React, {Fragment} from "react";

import Header from '../components/Header';
import LeftNav from '../components/LeftNav';
import RightChat from '../components/RightChat';
import AppFooter from '../components/AppFooter';
import PopupChat from '../components/PopupChat';


import Friends from '../components/Friends';
import Contacts from '../components/Contacts';
import Group from '../components/Group';
import Events from '../components/Events';
import CreatePost from '../components/CreatePost';
import MemberSlider from '../components/MemberSlider';
import FriendSilder from '../components/FriendSilder';
import StorySlider from '../components/StorySlider';
import PostView from '../components/PostView';
import Load from '../components/Load';
import ProfilePhoto from '../components/ProfilePhoto';


const Home = () => (
  <Fragment>
    <Header/>
    <LeftNav/>
    <RightChat/>

    <div className="main-content right-chat-active">
      <div className="middle-sidebar-bottom">
        <div className="middle-sidebar-left">
          <div className="row feed-body">
            <div className="col-xl-8 col-xxl-9 col-lg-8">
              <StorySlider/>
              <CreatePost/>
              <PostView id="32" postvideo="" postimage="post.png" avater="user.png" user="Surfiya Zakir" time="22 min ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <PostView id="31" postvideo="" postimage="post.png" avater="user.png" user="David Goria" time="22 min ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <PostView id="33" postvideo="" postimage="post.png" avater="user.png" user="Anthony Daugloi" time="2 hour ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <MemberSlider/>
              <PostView id="35" postvideo="" postimage="post.png" avater="user.png" user="Victor Exrixon" time="3 hour ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <FriendSilder/>
              <PostView id="36" postvideo="" postimage="post.png" avater="user.png" user="Victor Exrixon" time="12 hour ago"
                        des="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nulla dolor, ornare at commodo non, feugiat non nisi. Phasellus faucibus mollis pharetra. Proin blandit ac massa sed rhoncus."/>
              <Load/>
            </div>
            <div className="col-xl-4 col-xxl-3 col-lg-4 ps-lg-0">
              <Friends/>
              <Contacts/>
              <Group/>
              <Events/>
              <ProfilePhoto/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <PopupChat/>
    <AppFooter/>
  </Fragment>
);

export default Home;