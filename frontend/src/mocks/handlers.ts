import { alarmHandler } from '@/mocks/api/handlers/alarmHandlers';
import { authHandler } from '@/mocks/api/handlers/authHandlers';

export const handlers = [...alarmHandler, ...authHandler];
