import { IUser } from 'app/shared/model/user.model';
import { IRankUser } from 'app/shared/model/rank-user.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPermission } from 'app/shared/model/permission.model';
import { ITopicInterest } from 'app/shared/model/topic-interest.model';

export interface IMasterUser {
  id?: number;
  uuid?: string;
  fullName?: string;
  nickname?: string | null;
  avatar?: string | null;
  quickInfo?: string | null;
  user?: IUser | null;
  myRank?: IRankUser | null;
  baseInfo?: IBaseInfo | null;
  myBaseInfoCreateds?: IBaseInfo[] | null;
  myBaseInfoModifieds?: IBaseInfo[] | null;
  ownerOfs?: IBaseInfo[] | null;
  permissions?: IPermission[] | null;
  topicInterests?: ITopicInterest[] | null;
}

export const defaultValue: Readonly<IMasterUser> = {};
