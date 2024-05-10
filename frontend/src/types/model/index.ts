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
