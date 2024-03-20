export const PATH = {
  ROOT: '',
  HOME: '/',
  GUIDE: '/guide',
  RESULT: '/r/:id',
  TERMS_OF_USE: '/terms-of-use',
  ERROR: '/error',
} as const;

export const PATH_FINDER = {
  RESULT: (id: string) => `/r/${id}`,
} as const;
