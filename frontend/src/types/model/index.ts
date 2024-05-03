// 공통 응답 객체 타입
export interface APIResponse<T> {
  status: number;
  message: string;
  data: T;
}

export type difficultyLevelType = 1 | 2 | 3; // 1 : 하, 2 : 중, 3 : 상

// 설정 정보
export interface ISettingInfo {
  difficulty: difficultyLevelType;
  notificationStatus: boolean;
  notificationTime: string;
}
