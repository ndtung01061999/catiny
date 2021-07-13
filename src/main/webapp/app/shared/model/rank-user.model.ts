import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IRankGroup } from 'app/shared/model/rank-group.model';

export interface IRankUser {
  id?: number;
  uuid?: string;
  ratingPoints?: number | null;
  baseInfo?: IBaseInfo | null;
  rankGroup?: IRankGroup | null;
}

export const defaultValue: Readonly<IRankUser> = {};
