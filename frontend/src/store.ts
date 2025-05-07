import { configureStore } from "@reduxjs/toolkit";
import { fileApi } from "./services/api";

export const store = configureStore({
  reducer: {
    [fileApi.reducerPath]: fileApi.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(fileApi.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
