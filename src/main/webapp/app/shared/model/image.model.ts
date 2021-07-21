import { IFileInfo } from 'app/shared/model/file-info.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IAlbum } from 'app/shared/model/album.model';

export interface IImage {
  id?: number;
  uuid?: string;
  name?: string | null;
  width?: number | null;
  height?: number | null;
  quality?: number | null;
  pixelSize?: number | null;
  priorityIndex?: number | null;
  dataSize?: number | null;
  fileInfo?: IFileInfo | null;
  baseInfo?: IBaseInfo | null;
  imageProcesseds?: IImage[] | null;
  imageOriginal?: IImage | null;
  albums?: IAlbum[] | null;
}

export const defaultValue: Readonly<IImage> = {};
