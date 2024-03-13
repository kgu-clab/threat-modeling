import { API_BASE_URL } from '@constants/api';
import ServerChain from '@gwansikk/server-chain';

export const server = ServerChain({
  key: 'tm',
  mode: 'development',
  baseURL: API_BASE_URL,
});
