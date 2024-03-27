type EnvironmentModeType = 'development' | 'production';

export const ENVIRONMENT_MODE = import.meta.env.MODE as EnvironmentModeType;
