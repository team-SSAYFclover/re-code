import { IProblemInfo } from '@/types/model';
import { IRecodeProblemInfo } from './model';

export type IGetTodayRecodeListRes = IProblemInfo[];

export interface IGetRecodeRes {
  problemDto: IRecodeProblemInfo;
  recode: string;
  code: string;
  answers: string[];
}
