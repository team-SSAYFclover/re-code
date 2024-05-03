import { HttpResponse, http } from 'msw';
import { getUserRes } from '../data/user';

export const userHandlers = [
  http.get('/statistics/today/reviews', () => {
    return HttpResponse.json(getUserRes, {
      status: 200,
    });
  }),
];
