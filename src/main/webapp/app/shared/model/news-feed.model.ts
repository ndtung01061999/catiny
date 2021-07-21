import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPost } from 'app/shared/model/post.model';

export interface INewsFeed {
  id?: number;
  uuid?: string;
  priorityIndex?: number | null;
  baseInfo?: IBaseInfo | null;
  post?: IPost | null;
}

export const defaultValue: Readonly<INewsFeed> = {};
