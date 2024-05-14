// 공통 응답 객체 타입
export interface APIResponse<T> {
  status: number;
  message: string;
  data: T;
}

export interface APIBasicResponse {
  status: number;
  message: string;
}

export type difficultyLevelType = 1 | 2 | 3; // 1 : 하, 2 : 중, 3 : 상

// 설정 정보
export interface ISettingInfo {
  difficulty: difficultyLevelType;
  notificationStatus: boolean;
  notificationTime: string;
}

// 문제 정보
export interface IProblemInfo {
  problemNo: number;
  codeId: number;
  title: string;
  reviewCnt: number;
  completed: boolean;
}

// 레코드 조회 시 주는 문제 정보
export interface IRecodeProblemInfo {
  problemNo: number;
  title: string;
  reviewCnt: number;
  level: number;
  tags: string[];
  content: string;
}

// 메인화면 조회 모든 정보
export interface IMainInfo {
  sequence: number;
  ranking: number;
  weekReviews: IWeekReviews;
  todayProblems: IProblemInfo[];
  supplementaryQuestion: number;
  supplementaryTitle: string;
  randomQuestion: number;
  randomTitle: string;
  algoReview: IAlgoReview;
}

interface IWeekReviews {
  mon: number;
  tue: number;
  wed: number;
  thu: number;
  fri: number;
  sat: number;
  sun: number;
}

interface IAlgoReview {
  [key: string]: number;
  math: number;
  implementation: number;
  greedy: number;
  string: number;
  data_structures: number;
  graphs: number;
  dp: number;
  geometry: number;
}

// 문제 조회
export interface IProblemRes {
  content: {
    problemNo: number;
    title: string;
    level: number;
    reviewCount: number;
    tags: string[];
  }[];
  first: boolean;
  last: boolean;
  empty: boolean;
}
