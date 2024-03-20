export const PATH = {
  ROOT: '',
  HOME: '/',
  GUIDE: '/guide',
  RESULT: '/result/:id',
  TERMS_OF_USE: '/terms-of-use',
} as const;

export const PATH_FINDER = {
  RESULT: (id: string) => `/result/${id}`,
} as const;
