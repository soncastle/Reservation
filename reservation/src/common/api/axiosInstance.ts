import axios, { AxiosError } from "axios";
import { handleApiError } from "./errorHandler";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
  withCredentials: true,
    headers: {
    "Content-Type": "application/json"
  }
});

api.interceptors.response.use(
  (res) => res,
  (err) => {
    throw handleApiError(err);
  }
);

export default api;