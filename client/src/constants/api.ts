export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export const NOTION = {
  GUIDE: import.meta.env.VITE_NOTION_GUIDE,
  TERMS_OF_USE: import.meta.env.VITE_NOTION_TERMS_OF_USE,
} as const;

export const END_POINT = {
  ATTACKS: (id: string) => `/attacks?attackIds=${id}`,
  FILES: '/files/jsons',
  MITIGATIONS: '/mitigations',
} as const;
