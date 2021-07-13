import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IFollowUser {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  followUserDetails?: IMasterUser | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<IFollowUser> = {};
