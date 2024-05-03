import { alarmHandler } from '@/mocks/api/handlers/alarmHandlers';
import { authHandler } from '@/mocks/api/handlers/authHandlers';
import { userHandlers } from './api/handlers/userHandlers';

export const handlers = [...alarmHandler, ...authHandler, ...userHandlers];
