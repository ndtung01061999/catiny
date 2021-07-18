import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPostComment } from 'app/shared/model/post-comment.model';
import { IPostLike } from 'app/shared/model/post-like.model';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { IPagePost } from 'app/shared/model/page-post.model';
import { INewsFeed } from 'app/shared/model/news-feed.model';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { PostInType } from 'app/shared/model/enumerations/post-in-type.model';
import { PostType } from 'app/shared/model/enumerations/post-type.model';

export interface IPost {
  id?: number;
  uuid?: string;
  postInType?: PostInType | null;
  postType?: PostType | null;
  content?: string | null;
  searchField?: string | null;
  baseInfo?: IBaseInfo | null;
  comments?: IPostComment[] | null;
  likes?: IPostLike[] | null;
  postShareChildren?: IPost[] | null;
  groupPost?: IGroupPost | null;
  pagePost?: IPagePost | null;
  postShareParent?: IPost | null;
  newsFeeds?: INewsFeed[] | null;
  topicInterests?: ITopicInterest[] | null;
}

export const defaultValue: Readonly<IPost> = {};
