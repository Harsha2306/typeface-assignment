import React, { useRef } from "react";
import { Button } from "@mui/material";
import UploadFileIcon from "@mui/icons-material/UploadFile";
import { useUploadFileMutation } from "../services/api";

const allowedTypes = ["image/jpeg", "image/png", "application/pdf"];

const FileUploadButton: React.FC = () => {
  const [uploadFile, { isLoading }] = useUploadFileMutation();
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    if (!allowedTypes.includes(file.type)) {
      alert("Only JPG, PNG, and PDF files are allowed.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      await uploadFile(formData).unwrap();
      alert("File uploaded successfully!");
    } catch (err: any) {
      alert(err?.data?.message || "Failed to upload file.");
    }
  };

  return (
    <>
      <input
        type="file"
        ref={fileInputRef}
        style={{ display: "none" }}
        accept=".jpg,.png,.pdf"
        onChange={handleFileChange}
      />
      <Button
        variant="contained"
        startIcon={<UploadFileIcon />}
        onClick={() => fileInputRef.current?.click()}
        disabled={isLoading}
        sx={{ mb: 3 }}
      >
        {isLoading ? "Uploading..." : "Upload File"}
      </Button>
    </>
  );
};

export default FileUploadButton;
