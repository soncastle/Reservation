import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./userSlice"; // ✔ 이 이름이 정말 중요함!

export const store = configureStore({
  reducer: {
    user: userReducer,  // ✔ 여기도 userReducer
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
