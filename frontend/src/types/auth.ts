import { ISettingInfo } from './model';

export interface IPostLoginReq {
  authorizationCode: string;
}

export interface IPostLoginRes {
  name: string;
  avatar_url: string;
  settings: ISettingInfo;
}
