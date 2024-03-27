import { API_BASE_URL } from '@constants/api';
import { ENVIRONMENT_MODE } from '@constants/common';
import ServerChain from '@gwansikk/server-chain';

export const server = ServerChain({
  key: 'tm',
  /**
   * development mode는 http 통신도 허용합니다.
   */
  mode: 'development',
  /**
   * API 서버의 기본 URL을 설정합니다.
   * 개발환경일 경우 서버 URL를 사용하고, 프로덕션 환경일 경우 프록시 URL을 사용합니다.
   */
  baseURL: ENVIRONMENT_MODE === 'development' ? API_BASE_URL : '/api/v1',
});
