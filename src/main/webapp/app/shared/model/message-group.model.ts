import { IBaseInfo } from 'app/shared/model/base-info.model';
import { IMessageContent } from 'app/shared/model/message-content.model';
import { IMasterUser } from 'app/shared/model/master-user.model';

export interface IMessageGroup {
  id?: number;
  uuid?: string;
  groupName?: string | null;
  addBy?: string | null;
  baseInfo?: IBaseInfo | null;
  messageContents?: IMessageContent[] | null;
  masterUsers?: IMasterUser[] | null;
}

export const defaultValue: Readonly<IMessageGroup> = {};
