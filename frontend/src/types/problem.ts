export interface IGetProbListParams {
  page: number;
  size: number;
  start?: number;
  end?: number;
  tag?: string[];
  keyword?: string;
}

export interface IProbDetailInfo {
  id: number;
  problemNo: number;
  title: string;
  level: number;
  reviewCount: number;
  tags: string[];
  content: string;
  codeResLists: ICodeResList[];
}

export interface ICodeResList {
  id: number;
  name: string;
  content: string;
  submitTime: string;
  reviewStatus: boolean;
}
