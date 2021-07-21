import {IHistoryUpdate} from 'app/shared/model/history-update.model';
import {IClassInfo} from 'app/shared/model/class-info.model';
import {IUserProfile} from 'app/shared/model/user-profile.model';
import {IAccountStatus} from 'app/shared/model/account-status.model';
import {IDeviceStatus} from 'app/shared/model/device-status.model';
import {IFriend} from 'app/shared/model/friend.model';
import {IFollowUser} from 'app/shared/model/follow-user.model';
import {IFollowGroup} from 'app/shared/model/follow-group.model';
import {IFollowPage} from 'app/shared/model/follow-page.model';
import {IFileInfo} from 'app/shared/model/file-info.model';
import {IPagePost} from 'app/shared/model/page-post.model';
import {IPageProfile} from 'app/shared/model/page-profile.model';
import {IGroupPost} from 'app/shared/model/group-post.model';
import {IPost} from 'app/shared/model/post.model';
import {IPostComment} from 'app/shared/model/post-comment.model';
import {IPostLike} from 'app/shared/model/post-like.model';
import {IGroupProfile} from 'app/shared/model/group-profile.model';
import {INewsFeed} from 'app/shared/model/news-feed.model';
import {IMessageGroup} from 'app/shared/model/message-group.model';
import {IMessageContent} from 'app/shared/model/message-content.model';
import {IRankUser} from 'app/shared/model/rank-user.model';
import {IRankGroup} from 'app/shared/model/rank-group.model';
import {INotification} from 'app/shared/model/notification.model';
import {IAlbum} from 'app/shared/model/album.model';
import {IVideo} from 'app/shared/model/video.model';
import {IImage} from 'app/shared/model/image.model';
import {IVideoStream} from 'app/shared/model/video-stream.model';
import {IVideoLiveStreamBuffer} from 'app/shared/model/video-live-stream-buffer.model';
import {ITopicInterest} from 'app/shared/model/topic-interest.model';
import {ITodoList} from 'app/shared/model/todo-list.model';
import {IEvent} from 'app/shared/model/event.model';
import {IMasterUser} from 'app/shared/model/master-user.model';
import {IPermission} from 'app/shared/model/permission.model';
import {ProcessStatus} from 'app/shared/model/enumerations/process-status.model';

export interface IBaseInfo
{
  id?: number;
  uuid?: string;
  processStatus?: ProcessStatus | null;
  modifiedClass?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  notes?: string | null;
  deleted?: boolean | null;
  priorityIndex?: number | null;
  countUse?: number | null;
  historyUpdates?: IHistoryUpdate[] | null;
  classInfo?: IClassInfo | null;
  userProfile?: IUserProfile | null;
  accountStatus?: IAccountStatus | null;
  deviceStatus?: IDeviceStatus | null;
  friend?: IFriend | null;
  followUser?: IFollowUser | null;
  followGroup?: IFollowGroup | null;
  followPage?: IFollowPage | null;
  fileInfo?: IFileInfo | null;
  pagePost?: IPagePost | null;
  pageProfile?: IPageProfile | null;
  groupPost?: IGroupPost | null;
  post?: IPost | null;
  postComment?: IPostComment | null;
  postLike?: IPostLike | null;
  groupProfile?: IGroupProfile | null;
  newsFeed?: INewsFeed | null;
  messageGroup?: IMessageGroup | null;
  messageContent?: IMessageContent | null;
  rankUser?: IRankUser | null;
  rankGroup?: IRankGroup | null;
  notification?: INotification | null;
  album?: IAlbum | null;
  video?: IVideo | null;
  image?: IImage | null;
  videoStream?: IVideoStream | null;
  videoLiveStreamBuffer?: IVideoLiveStreamBuffer | null;
  topicInterest?: ITopicInterest | null;
  todoList?: ITodoList | null;
  event?: IEvent | null;
  createdBy?: IMasterUser | null;
  modifiedBy?: IMasterUser | null;
  owner?: IMasterUser | null;
  permissions?: IPermission[] | null;
}

export const defaultValue: Readonly<IBaseInfo> = {
  deleted: false,
};
