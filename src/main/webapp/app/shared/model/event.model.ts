import dayjs from 'dayjs';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { EventType } from 'app/shared/model/enumerations/event-type.model';

export interface IEvent {
  id?: number;
  uuid?: string;
  title?: string | null;
  avatar?: string | null;
  content?: string | null;
  type?: EventType | null;
  description?: string | null;
  startTime?: string | null;
  endTime?: string | null;
  tagLine?: string | null;
  imageCollection?: string | null;
  videoCollection?: string | null;
  baseInfo?: IBaseInfo | null;
}

export const defaultValue: Readonly<IEvent> = {};
