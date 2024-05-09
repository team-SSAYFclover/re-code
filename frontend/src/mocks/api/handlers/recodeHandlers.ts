import { completeRecode, getRecode, getTodayRecodeList } from '@/mocks/api/data/recode';
import { HttpResponse, delay, http } from 'msw';

export const recodeHandlers = [
  http.get('/statistics/today/reviews', async () => {
    await delay(2000);
    return HttpResponse.json(getTodayRecodeList, {
      status: 200,
    });
  }),

  http.get('/problems/recode/:codeId', () => {
    return HttpResponse.json(getRecode, {
      status: 200,
    });
  }),

  http.put('/problems/recode/:codeId', () => {
    return HttpResponse.json(completeRecode, {
      status: 201,
    });
  }),
];
