import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import type { FileMetaDataResponseDto } from "../types";

export const fileApi = createApi({
  reducerPath: "fileApi",
  baseQuery: fetchBaseQuery({
    baseUrl: "http://localhost:8080/api/v1/files",
  }),
  tagTypes: ["Files"],
  endpoints: (builder) => ({
    getFiles: builder.query<{ data: FileMetaDataResponseDto[] }, void>({
      query: () => "",
      providesTags: ["Files"],
    }),
    uploadFile: builder.mutation<void, FormData>({
      query: (formData) => ({
        url: "/upload",
        method: "POST",
        body: formData,
      }),
      invalidatesTags: ["Files"],
    }),
  }),
});

export const { useGetFilesQuery, useUploadFileMutation } = fileApi;
