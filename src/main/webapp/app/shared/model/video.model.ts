import { IFileInfo } from 'app/shared/model/file-info.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IVideoStream } from 'app/shared/model/video-stream.model';

export interface IVideo {
  id?: number;
  uuid?: string;
  name?: string | null;
  width?: number | null;
  height?: number | null;
  qualityImage?: number | null;
  qualityAudio?: number | null;
  quality?: number | null;
  pixelSize?: number | null;
  priorityIndex?: number | null;
  dataSize?: number | null;
  fileInfo?: IFileInfo | null;
  baseInfo?: IBaseInfo | null;
  videoProcesseds?: IVideo[] | null;
  videoStream?: IVideoStream | null;
  videoOriginal?: IVideo | null;
}

export const defaultValue: Readonly<IVideo> = {};
