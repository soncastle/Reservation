// src/store/userSlice.ts
import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../api/axiosInstance";

export interface UserState {
  isLoggedIn: boolean;
  info: {
    email: string | null;
    name?: string | null;
  } | null;
  loading: boolean;
  error: string | null;
}

const initialState: UserState = {
  isLoggedIn: false,
  info: null,
  loading: false,
  error: null,
};

export const checkSession = createAsyncThunk(
  "user/checkSession",
  async (_, thunkAPI) => {
    try {
      const res = await api.get("/user/checkSession");
      return res.data;
    } catch (err: any) {
      return thunkAPI.rejectWithValue("세션이 없거나 만료되었습니다.");
    }
  }
);

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    logout(state) {
      state.isLoggedIn = false;
      state.info = null;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(checkSession.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(checkSession.fulfilled, (state, action) => {
        state.loading = false;
        state.isLoggedIn = true;
        state.info = action.payload;
      })
      .addCase(checkSession.rejected, (state, action) => {
        state.loading = false;
        state.isLoggedIn = false;
        state.info = null;
        state.error = (action.payload as string) ?? "현재 세션에 문제가 있습니다.";
      });
  },
});

export const { logout } = userSlice.actions;
export default userSlice.reducer;
