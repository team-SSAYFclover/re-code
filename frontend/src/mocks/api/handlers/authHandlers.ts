import { postLoginRes } from '@/mocks/api/data/auth';
import { HttpResponse, http } from 'msw';

export const authHandler = [
  // @TODO: 헤더 안에 토큰 추가
  http.post('/api/auth/login', () => {
    return HttpResponse.json(postLoginRes, {
      status: 200,
      headers: {
        accessToken: '',
        refreshToken: '',
      },
    });
  }),
];
