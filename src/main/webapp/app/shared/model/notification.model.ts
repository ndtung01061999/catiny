import { IBaseInfo } from 'app/shared/model/base-info.model';
import { NotifyType } from 'app/shared/model/enumerations/notify-type.model';

export interface INotification {
  id?: number;
  uuid?: string;
  notifyType?: NotifyType | null;
  title?: string | null;
  content?: string | null;
  baseInfo?: IBaseInfo | null;
}

export const defaultValue: Readonly<INotification> = {};
