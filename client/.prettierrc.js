export default {
  singleQuote: true,
  semi: true,
  useTabs: false,
  tabWidth: 2,
  trailingComma: 'all',
  printWidth: 80,
  importOrder: [
    '^react(.*)',
    '^@tanstack/(.*)$',
    '<THIRD_PARTY_MODULES>',
    '^@assets/(.*)$',
    '^[./]',
  ],
  importOrderSeparation: true,
  importOrderSortSpecifiers: true,
  plugin: ['@trivago/prettier-plugin-sort-imports'],
};
