import { IGroupProfile } from 'app/shared/model/group-profile.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';

export interface IGroupPost {
  id?: number;
  uuid?: string;
  name?: string;
  avatar?: string | null;
  quickInfo?: string | null;
  profile?: IGroupProfile | null;
  baseInfo?: IBaseInfo | null;
  myPostInGroups?: IPost[] | null;
  topicInterests?: ITopicInterest[] | null;
}

export const defaultValue: Readonly<IGroupPost> = {};
