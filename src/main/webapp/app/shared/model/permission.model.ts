import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IPermission {
  id?: number;
  read?: boolean | null;
  write?: boolean | null;
  share?: boolean | null;
  delete?: boolean | null;
  add?: boolean | null;
  level?: number | null;
  baseInfo?: IBaseInfo | null;
  masterUser?: IMasterUser | null;
}

export const defaultValue: Readonly<IPermission> = {
  read: false,
  write: false,
  share: false,
  delete: false,
  add: false,
};
