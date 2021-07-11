import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { IPost } from 'app/shared/model/post.model';

export interface IPostLike {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  userLike?: IMasterUser | null;
  post?: IPost | null;
}

export const defaultValue: Readonly<IPostLike> = {};
