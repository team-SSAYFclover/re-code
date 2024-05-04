import { IProblemInfo, IRecodeProblemInfo } from './model';

export interface IGetTodayRecodeListRes {
  todayProblems: IProblemInfo[];
}

export interface IGetRecodeRes {
  problem: IRecodeProblemInfo;
  recode: string;
  answers: string[];
}
