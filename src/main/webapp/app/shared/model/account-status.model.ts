import dayjs from 'dayjs';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IDeviceStatus } from 'app/shared/model/device-status.model';
import { StatusName } from 'app/shared/model/enumerations/status-name.model';

export interface IAccountStatus {
  id?: number;
  uuid?: string;
  accountStatus?: StatusName | null;
  lastVisited?: string | null;
  statusComment?: string | null;
  baseInfo?: IBaseInfo | null;
  deviceStatuses?: IDeviceStatus[] | null;
}

export const defaultValue: Readonly<IAccountStatus> = {};
