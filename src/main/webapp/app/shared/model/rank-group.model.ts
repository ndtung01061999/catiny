import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IRankUser } from 'app/shared/model/rank-user.model';

export interface IRankGroup {
  id?: number;
  uuid?: string;
  baseInfo?: IBaseInfo | null;
  rankUsers?: IRankUser[] | null;
}

export const defaultValue: Readonly<IRankGroup> = {};
