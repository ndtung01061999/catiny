import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import messageGroup from 'app/entities/message-group/message-group.reducer';
// prettier-ignore
import messageContent from 'app/entities/message-content/message-content.reducer';
// prettier-ignore
import hanhChinhVN from 'app/entities/hanh-chinh-vn/hanh-chinh-vn.reducer';
// prettier-ignore
import masterUser from 'app/entities/master-user/master-user.reducer';
// prettier-ignore
import userProfile from 'app/entities/user-profile/user-profile.reducer';
// prettier-ignore
import accountStatus from 'app/entities/account-status/account-status.reducer';
// prettier-ignore
import deviceStatus from 'app/entities/device-status/device-status.reducer';
// prettier-ignore
import friend from 'app/entities/friend/friend.reducer';
// prettier-ignore
import followUser from 'app/entities/follow-user/follow-user.reducer';
// prettier-ignore
import followGroup from 'app/entities/follow-group/follow-group.reducer';
// prettier-ignore
import followPage from 'app/entities/follow-page/follow-page.reducer';
// prettier-ignore
import fileInfo from 'app/entities/file-info/file-info.reducer';
// prettier-ignore
import pagePost from 'app/entities/page-post/page-post.reducer';
// prettier-ignore
import pageProfile from 'app/entities/page-profile/page-profile.reducer';
// prettier-ignore
import groupPost from 'app/entities/group-post/group-post.reducer';
// prettier-ignore
import post from 'app/entities/post/post.reducer';
// prettier-ignore
import postComment from 'app/entities/post-comment/post-comment.reducer';
// prettier-ignore
import postLike from 'app/entities/post-like/post-like.reducer';
// prettier-ignore
import groupProfile from 'app/entities/group-profile/group-profile.reducer';
// prettier-ignore
import newsFeed from 'app/entities/news-feed/news-feed.reducer';
// prettier-ignore
import rankUser from 'app/entities/rank-user/rank-user.reducer';
// prettier-ignore
import rankGroup from 'app/entities/rank-group/rank-group.reducer';
// prettier-ignore
import notification from 'app/entities/notification/notification.reducer';
// prettier-ignore
import album from 'app/entities/album/album.reducer';
// prettier-ignore
import video from 'app/entities/video/video.reducer';
// prettier-ignore
import image from 'app/entities/image/image.reducer';
// prettier-ignore
import videoStream from 'app/entities/video-stream/video-stream.reducer';
// prettier-ignore
import videoLiveStreamBuffer from 'app/entities/video-live-stream-buffer/video-live-stream-buffer.reducer';
// prettier-ignore
import topicInterest from 'app/entities/topic-interest/topic-interest.reducer';
// prettier-ignore
import todoList from 'app/entities/todo-list/todo-list.reducer';
// prettier-ignore
import event from 'app/entities/event/event.reducer';
// prettier-ignore
import baseInfo from 'app/entities/base-info/base-info.reducer';
// prettier-ignore
import permission from 'app/entities/permission/permission.reducer';
// prettier-ignore
import classInfo from 'app/entities/class-info/class-info.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  messageGroup,
  messageContent,
  hanhChinhVN,
  masterUser,
  userProfile,
  accountStatus,
  deviceStatus,
  friend,
  followUser,
  followGroup,
  followPage,
  fileInfo,
  pagePost,
  pageProfile,
  groupPost,
  post,
  postComment,
  postLike,
  groupProfile,
  newsFeed,
  rankUser,
  rankGroup,
  notification,
  album,
  video,
  image,
  videoStream,
  videoLiveStreamBuffer,
  topicInterest,
  todoList,
  event,
  baseInfo,
  permission,
  classInfo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
