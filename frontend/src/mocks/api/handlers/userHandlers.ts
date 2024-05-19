import { HttpResponse, http } from 'msw';
import { getUserRes, putUserRes } from '../data/user';

export const userHandlers = [
  http.get('/users', () => {
    return HttpResponse.json(getUserRes, {
      status: 200,
    });
  }),

  http.patch('/users/setting', () => {
    return HttpResponse.json(putUserRes, {
      status: 200,
    });
  }),
];
