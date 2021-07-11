import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPagePost } from 'app/shared/model/page-post.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IFollowPage {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  followPageDetails?: IPagePost | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<IFollowPage> = {};
