import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMessageGroup } from 'app/shared/model/message-group.model';

export interface IMessageContent {
  id?: number;
  uuid?: string;
  senderName?: string | null;
  attach?: string | null;
  content?: string | null;
  status?: string | null;
  searchField?: string | null;
  baseInfo?: IBaseInfo | null;
  messageGroup?: IMessageGroup | null;
}

export const defaultValue: Readonly<IMessageContent> = {};
