import dayjs from 'dayjs';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IAccountStatus } from 'app/shared/model/account-status.model';
import { DeviceType } from 'app/shared/model/enumerations/device-type.model';
import { StatusName } from 'app/shared/model/enumerations/status-name.model';

export interface IDeviceStatus {
  id?: number;
  uuid?: string;
  deviceName?: string | null;
  deviceType?: DeviceType | null;
  deviceStatus?: StatusName | null;
  lastVisited?: string | null;
  statusComment?: string | null;
  baseInfo?: IBaseInfo | null;
  accountStatus?: IAccountStatus | null;
}

export const defaultValue: Readonly<IDeviceStatus> = {};
