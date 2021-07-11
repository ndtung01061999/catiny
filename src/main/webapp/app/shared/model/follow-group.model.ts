import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IGroupPost } from 'app/shared/model/group-post.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IFollowGroup {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  followGroupDetails?: IGroupPost | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<IFollowGroup> = {};
