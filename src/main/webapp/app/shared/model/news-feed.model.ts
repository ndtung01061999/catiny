import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface INewsFeed {
  id?: number;
  uuid?: string;
  score?: number | null;
  baseInfo?: IBaseInfo | null;
  post?: IPost | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<INewsFeed> = {};
