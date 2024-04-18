import { getEmailDuplicateRes } from '@/mocks/api/data/test';
import { HttpResponse, http } from 'msw';

export const testHandlers = [
  http.get('/api/email', ({ request }) => {
    const url = new URL(request.url);
    const email = url.searchParams.get('email');

    if (!email) {
      return new HttpResponse(null, { status: 404 });
    }

    const success = HttpResponse.json(getEmailDuplicateRes, { status: 200 });
    return success;
  }),
];
