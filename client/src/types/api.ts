export interface BaseResponse<T = unknown> {
  readonly success: boolean;
  data: T;
}
