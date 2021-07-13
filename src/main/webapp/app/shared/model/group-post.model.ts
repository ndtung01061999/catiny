import { IGroupProfile } from 'app/shared/model/group-profile.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IGroupPost {
  id?: number;
  uuid?: string;
  name?: string;
  quickInfo?: string | null;
  profile?: IGroupProfile | null;
  baseInfo?: IBaseInfo | null;
  myPostInGroups?: IPost[] | null;
  topicInterests?: ITopicInterest[] | null;
  userInGroups?: IMasterUser[] | null;
}

export const defaultValue: Readonly<IGroupPost> = {};
