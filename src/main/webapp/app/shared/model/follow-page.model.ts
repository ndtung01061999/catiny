import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPagePost } from 'app/shared/model/page-post.model';

export interface IFollowPage {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  followPageDetails?: IPagePost | null;
}

export const defaultValue: Readonly<IFollowPage> = {};
