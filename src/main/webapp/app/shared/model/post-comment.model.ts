import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPostLike } from 'app/shared/model/post-like.model';
import { IPost } from 'app/shared/model/post.model';

export interface IPostComment {
  id?: number;
  uuid?: string;
  content?: string | null;
  baseInfo?: IBaseInfo | null;
  likes?: IPostLike[] | null;
  commentReplies?: IPostComment[] | null;
  post?: IPost | null;
  commentParent?: IPostComment | null;
}

export const defaultValue: Readonly<IPostComment> = {};
