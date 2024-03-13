export const PATH = {
  ROOT: '',
  HOME: '/',
  GUIDE: '/guide',
  EXAMPLE: '/example',
  RESULT: '/result/:id',
} as const;

export const PATH_FINDER = {
  RESULT: (id: string) => `/result/${id}`,
} as const;
