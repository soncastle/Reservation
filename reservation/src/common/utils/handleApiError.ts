import { ApiError } from "../types/ErrorResponse";

export function handleApiError(error: any): ApiError {
  if (!error.response) {
    return {
      code: "NETWORK_ERROR",
      message: "서버와 연결할 수 없습니다.",
      status: 0,
    };
  }

  const { status, data } = error.response;

  return {
    code: data.code ?? "UNKNOWN_ERROR",
    message: data.message ?? "알 수 없는 오류가 발생했습니다.",
    status,
  };
}
