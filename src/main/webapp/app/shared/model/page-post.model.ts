import { IPageProfile } from 'app/shared/model/page-profile.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';

export interface IPagePost {
  id?: number;
  uuid?: string;
  name?: string;
  quickInfo?: string | null;
  profile?: IPageProfile | null;
  baseInfo?: IBaseInfo | null;
  myPostInPages?: IPost[] | null;
  masterUser?: IMasterUser | null;
  topicInterests?: ITopicInterest[] | null;
}

export const defaultValue: Readonly<IPagePost> = {};
