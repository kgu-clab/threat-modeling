export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export const END_POINT = {
  ATTACKS: (id: string) => `/attacks?attackIds=${id}`,
  FILES: '/files/jsons',
  MITIGATIONS: '/mitigations',
} as const;
