import dayjs from 'dayjs';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IImage } from 'app/shared/model/image.model';
import { IVideo } from 'app/shared/model/video.model';
import { EventType } from 'app/shared/model/enumerations/event-type.model';

export interface IEvent {
  id?: number;
  uuid?: string;
  title?: string | null;
  content?: string | null;
  type?: EventType | null;
  description?: string | null;
  startTime?: string | null;
  endTime?: string | null;
  tagLine?: string | null;
  baseInfo?: IBaseInfo | null;
  otherImages?: IImage[] | null;
  otherVideos?: IVideo[] | null;
}

export const defaultValue: Readonly<IEvent> = {};
