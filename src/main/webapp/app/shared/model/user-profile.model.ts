import { IBaseInfo } from 'app/shared/model/base-info.model';

export interface IUserProfile {
  id?: number;
  uuid?: string;
  work?: string | null;
  education?: string | null;
  placesLived?: string | null;
  contactInfo?: string | null;
  webSocialLinks?: string | null;
  basicInfo?: string | null;
  relationshipInfo?: string | null;
  family?: string | null;
  detailAbout?: string | null;
  lifeEvents?: string | null;
  hobbies?: string | null;
  featured?: string | null;
  baseInfo?: IBaseInfo | null;
}

export const defaultValue: Readonly<IUserProfile> = {};
