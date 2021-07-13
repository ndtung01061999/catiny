import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { FriendType } from 'app/shared/model/enumerations/friend-type.model';

export interface IFriend {
  id?: number;
  uuid?: string;
  friendType?: FriendType | null;
  baseInfo?: IBaseInfo | null;
  friendDetails?: IMasterUser | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<IFriend> = {};
