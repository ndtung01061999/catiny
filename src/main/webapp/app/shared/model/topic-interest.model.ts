import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { IPagePost } from 'app/shared/model/page-post.model';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface ITopicInterest {
  id?: number;
  uuid?: string;
  title?: string | null;
  content?: string | null;
  baseInfo?: IBaseInfo | null;
  posts?: IPost[] | null;
  pagePosts?: IPagePost[] | null;
  groupPosts?: IGroupPost[] | null;
  masterUsers?: IMasterUser[] | null;
}

export const defaultValue: Readonly<ITopicInterest> = {};
