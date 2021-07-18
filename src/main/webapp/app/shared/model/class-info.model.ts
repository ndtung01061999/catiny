import { IBaseInfo } from 'app/shared/model/base-info.model';

export interface IClassInfo {
  id?: number;
  uuid?: string;
  packageName?: string | null;
  fullName?: string;
  className?: string | null;
  baseInfos?: IBaseInfo[] | null;
}

export const defaultValue: Readonly<IClassInfo> = {};
