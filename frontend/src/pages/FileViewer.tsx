import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import {
  CircularProgress,
  Alert,
  Typography,
  Box,
  Button,
} from "@mui/material";

const FileViewer: React.FC = () => {
  const { id } = useParams();
  const [fileUrl, setFileUrl] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [fileName, setFileName] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    const fetchFile = async () => {
      try {
        const res = await fetch(
          `http://localhost:8080/api/v1/files/${id}/content`
        );
        if (!res.ok) throw new Error("Failed to load file content");
        const blob = await res.blob();
        const contentDisposition = res.headers.get("Content-Disposition");
        const nameMatch = contentDisposition?.match(/filename="(.+)"/);
        if (nameMatch) setFileName(nameMatch[1]);

        setFileUrl(URL.createObjectURL(blob));
      } catch (err: any) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchFile();
  }, [id]);

  if (loading) return <CircularProgress />;
  if (error) return <Alert severity="error">{error}</Alert>;

  return (
    <Box>
      <Typography variant="h5" gutterBottom>
        {fileName || "File Viewer"}
      </Typography>
      <Button
        variant="outlined"
        onClick={() => navigate("/")}
        sx={{
          mb: 2,
          "&:hover": {
            backgroundColor: "primary.main",
            color: "white",
          },
        }}
      >
        ← Back
      </Button>
      <iframe
        src={fileUrl}
        title="File preview"
        width="100%"
        height="600px"
        style={{ border: "1px solid #ccc", borderRadius: "8px" }}
      />
    </Box>
  );
};

export default FileViewer;
