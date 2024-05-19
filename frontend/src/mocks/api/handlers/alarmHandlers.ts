import { HttpResponse, http } from 'msw';
import { getAlarmRes } from '../data/alarm';

export const alarmHandler = [
  http.get('/notifications', () => {
    return HttpResponse.json(getAlarmRes, {
      status: 200,
    });
  }),
];
