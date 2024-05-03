import { APIResponse } from '@/types/model';
import { IGetTodayRecodeListRes } from '@/types/recode';

export const getUserRes: APIResponse<IGetTodayRecodeListRes> = {
  status: 200,
  message: '오늘의 복습리스트 조회에 성공했습니다.',
  data: {
    todayProblems: [
      {
        problemId: 3302,
        codeId: 10,
        name: 'Job Scheduling',
        reviewCnt: 2,
        completed: true,
      },
      {
        problemId: 11779,
        codeId: 12,
        name: '최소비용 구하기 2',
        reviewCnt: 2,
        completed: true,
      },
      {
        problemId: 1486,
        codeId: 3,
        name: '등산',
        reviewCnt: 1,
        completed: false,
      },
      {
        problemId: 1261,
        codeId: 5,
        name: '알고스팟',
        reviewCnt: 2,
        completed: true,
      },
      {
        problemId: 11559,
        codeId: 13,
        name: 'Puyo Puyo',
        reviewCnt: 1,
        completed: false,
      },
      {
        problemId: 1197,
        codeId: 13,
        name: '최소 스패닝 트리',
        reviewCnt: 3,
        completed: false,
      },
      {
        problemId: 17471,
        codeId: 29,
        name: '게리맨더링',
        reviewCnt: 1,
        completed: true,
      },
    ],
  },
};
