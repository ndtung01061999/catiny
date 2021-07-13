import { IFileInfo } from 'app/shared/model/file-info.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IVideoStream } from 'app/shared/model/video-stream.model';
import { IEvent } from 'app/shared/model/event.model';

export interface IVideo {
  id?: number;
  uuid?: string;
  name?: string | null;
  fileInfo?: IFileInfo | null;
  baseInfo?: IBaseInfo | null;
  videoProcesseds?: IVideo[] | null;
  videoStream?: IVideoStream | null;
  videoOriginal?: IVideo | null;
  event?: IEvent | null;
}

export const defaultValue: Readonly<IVideo> = {};
