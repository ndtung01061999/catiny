import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { IPost } from 'app/shared/model/post.model';

export interface IPostComment {
  id?: number;
  uuid?: string;
  content?: string | null;
  baseInfo?: IBaseInfo | null;
  commentReplies?: IPostComment[] | null;
  userComment?: IMasterUser | null;
  post?: IPost | null;
  commentParent?: IPostComment | null;
}

export const defaultValue: Readonly<IPostComment> = {};
