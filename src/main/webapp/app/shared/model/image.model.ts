import { IFileInfo } from 'app/shared/model/file-info.model';
import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IEvent } from 'app/shared/model/event.model';
import { IAlbum } from 'app/shared/model/album.model';

export interface IImage {
  id?: number;
  uuid?: string;
  name?: string | null;
  fileInfo?: IFileInfo | null;
  baseInfo?: IBaseInfo | null;
  imageProcesseds?: IImage[] | null;
  imageOriginal?: IImage | null;
  event?: IEvent | null;
  albums?: IAlbum[] | null;
}

export const defaultValue: Readonly<IImage> = {};
