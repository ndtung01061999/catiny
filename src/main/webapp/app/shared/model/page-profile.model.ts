import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IPagePost } from 'app/shared/model/page-post.model';

export interface IPageProfile {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  page?: IPagePost | null;
}

export const defaultValue: Readonly<IPageProfile> = {};
