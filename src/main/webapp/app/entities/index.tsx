import React from 'react';
import {Switch} from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MessageGroup from './message-group';
import MessageContent from './message-content';
import HanhChinhVN from './hanh-chinh-vn';
import MasterUser from './master-user';
import UserProfile from './user-profile';
import AccountStatus from './account-status';
import DeviceStatus from './device-status';
import Friend from './friend';
import FollowUser from './follow-user';
import FollowGroup from './follow-group';
import FollowPage from './follow-page';
import FileInfo from './file-info';
import PagePost from './page-post';
import PageProfile from './page-profile';
import GroupPost from './group-post';
import Post from './post';
import PostComment from './post-comment';
import PostLike from './post-like';
import GroupProfile from './group-profile';
import NewsFeed from './news-feed';
import RankUser from './rank-user';
import RankGroup from './rank-group';
import Notification from './notification';
import Album from './album';
import Video from './video';
import Image from './image';
import VideoStream from './video-stream';
import VideoLiveStreamBuffer from './video-live-stream-buffer';
import TopicInterest from './topic-interest';
import TodoList from './todo-list';
import Event from './event';
import BaseInfo from './base-info';
import Permission from './permission';
import ClassInfo from './class-info';
import HistoryUpdate from './history-update';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}message-group`} component={MessageGroup} />
      <ErrorBoundaryRoute path={`${match.url}message-content`} component={MessageContent} />
      <ErrorBoundaryRoute path={`${match.url}hanh-chinh-vn`} component={HanhChinhVN} />
      <ErrorBoundaryRoute path={`${match.url}master-user`} component={MasterUser} />
      <ErrorBoundaryRoute path={`${match.url}user-profile`} component={UserProfile} />
      <ErrorBoundaryRoute path={`${match.url}account-status`} component={AccountStatus} />
      <ErrorBoundaryRoute path={`${match.url}device-status`} component={DeviceStatus} />
      <ErrorBoundaryRoute path={`${match.url}friend`} component={Friend} />
      <ErrorBoundaryRoute path={`${match.url}follow-user`} component={FollowUser} />
      <ErrorBoundaryRoute path={`${match.url}follow-group`} component={FollowGroup} />
      <ErrorBoundaryRoute path={`${match.url}follow-page`} component={FollowPage} />
      <ErrorBoundaryRoute path={`${match.url}file-info`} component={FileInfo} />
      <ErrorBoundaryRoute path={`${match.url}page-post`} component={PagePost} />
      <ErrorBoundaryRoute path={`${match.url}page-profile`} component={PageProfile} />
      <ErrorBoundaryRoute path={`${match.url}group-post`} component={GroupPost} />
      <ErrorBoundaryRoute path={`${match.url}post`} component={Post} />
      <ErrorBoundaryRoute path={`${match.url}post-comment`} component={PostComment} />
      <ErrorBoundaryRoute path={`${match.url}post-like`} component={PostLike} />
      <ErrorBoundaryRoute path={`${match.url}group-profile`} component={GroupProfile} />
      <ErrorBoundaryRoute path={`${match.url}news-feed`} component={NewsFeed} />
      <ErrorBoundaryRoute path={`${match.url}rank-user`} component={RankUser} />
      <ErrorBoundaryRoute path={`${match.url}rank-group`} component={RankGroup} />
      <ErrorBoundaryRoute path={`${match.url}notification`} component={Notification} />
      <ErrorBoundaryRoute path={`${match.url}album`} component={Album} />
      <ErrorBoundaryRoute path={`${match.url}video`} component={Video} />
      <ErrorBoundaryRoute path={`${match.url}image`} component={Image} />
      <ErrorBoundaryRoute path={`${match.url}video-stream`} component={VideoStream} />
      <ErrorBoundaryRoute path={`${match.url}video-live-stream-buffer`} component={VideoLiveStreamBuffer} />
      <ErrorBoundaryRoute path={`${match.url}topic-interest`} component={TopicInterest} />
      <ErrorBoundaryRoute path={`${match.url}todo-list`} component={TodoList} />
      <ErrorBoundaryRoute path={`${match.url}event`} component={Event} />
      <ErrorBoundaryRoute path={`${match.url}base-info`} component={BaseInfo} />
      <ErrorBoundaryRoute path={`${match.url}permission`} component={Permission} />
      <ErrorBoundaryRoute path={`${match.url}class-info`} component={ClassInfo} />
      <ErrorBoundaryRoute path={`${match.url}history-update`} component={HistoryUpdate} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
