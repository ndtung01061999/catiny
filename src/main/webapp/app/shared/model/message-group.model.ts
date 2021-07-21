import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMessageContent } from 'app/shared/model/message-content.model';

export interface IMessageGroup {
  id?: number;
  uuid?: string;
  groupName?: string | null;
  avatar?: string | null;
  addBy?: string | null;
  baseInfo?: IBaseInfo | null;
  messageContents?: IMessageContent[] | null;
}

export const defaultValue: Readonly<IMessageGroup> = {};
