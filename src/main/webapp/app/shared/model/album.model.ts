import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IImage } from 'app/shared/model/image.model';

export interface IAlbum {
  id?: number;
  uuid?: string;
  name?: string;
  baseInfo?: IBaseInfo | null;
  images?: IImage[] | null;
}

export const defaultValue: Readonly<IAlbum> = {};
