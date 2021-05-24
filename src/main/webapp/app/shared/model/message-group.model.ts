import dayjs from 'dayjs';

export interface IMessageGroup {
  id?: number;
  uuid?: string;
  userId?: number;
  groupId?: string;
  groupName?: string | null;
  addBy?: string | null;
  lastContent?: string | null;
  searchField?: string | null;
  role?: string | null;
  createdDate?: string | null;
  modifiedDate?: string | null;
  createdBy?: string | null;
  modifiedBy?: string | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IMessageGroup> = {};
