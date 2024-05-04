import { getRecode, getTodayRecodeList } from '@/mocks/api/data/recode';
import { HttpResponse, http } from 'msw';

export const recodeHandlers = [
  http.get('/statistics/today/reviews', () => {
    return HttpResponse.json(getTodayRecodeList, {
      status: 200,
    });
  }),

  http.get('/problems/recode/:codeId', () => {
    return HttpResponse.json(getRecode, {
      status: 200,
    });
  }),
];
