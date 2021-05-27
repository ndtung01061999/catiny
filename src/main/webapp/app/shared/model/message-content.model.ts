export interface IMessageContent
{
  id?: number;
  uuid?: string;
  groupId?: string;
  content?: string | null;
  sender?: string | null;
  status?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IMessageContent> = {};
