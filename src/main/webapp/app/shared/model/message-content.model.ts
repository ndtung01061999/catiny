import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMasterUser } from 'app/shared/model/master-user.model';
import { IMessageGroup } from 'app/shared/model/message-group.model';

export interface IMessageContent {
  id?: number;
  uuid?: string;
  content?: string | null;
  status?: string | null;
  searchField?: string | null;
  baseInfo?: IBaseInfo | null;
  sender?: IMasterUser | null;
  messageGroup?: IMessageGroup | null;
}

export const defaultValue: Readonly<IMessageContent> = {};
