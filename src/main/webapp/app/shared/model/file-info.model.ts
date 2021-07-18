import { IBaseInfo } from 'app/shared/model/base-info.model';

export interface IFileInfo {
  id?: number;
  uuid?: string;
  nameFile?: string | null;
  typeFile?: string | null;
  path?: string | null;
  dataSize?: number | null;
  baseInfo?: IBaseInfo | null;
}

export const defaultValue: Readonly<IFileInfo> = {};
