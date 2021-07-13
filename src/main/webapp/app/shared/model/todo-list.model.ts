import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface ITodoList {
  id?: number;
  uuid?: string;
  title?: string | null;
  content?: string | null;
  baseInfo?: IBaseInfo | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<ITodoList> = {};
