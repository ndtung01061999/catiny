export interface IHanhChinhVN {
  id?: number;
  name?: string;
  slug?: string;
  type?: string;
  nameWithType?: string;
  code?: string;
  parentCode?: string;
  path?: string | null;
  pathWithType?: string | null;
}

export const defaultValue: Readonly<IHanhChinhVN> = {};
