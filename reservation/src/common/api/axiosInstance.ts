import axios, { AxiosError } from "axios";
import { handleApiError } from "../utils/handleApiError";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true,
});

api.interceptors.response.use(
  (res) => res,
  (err: AxiosError) => {
    throw handleApiError(err);
  }
);

export default api;