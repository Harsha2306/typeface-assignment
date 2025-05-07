import React from "react";
import {
  Grid,
  Card,
  CardContent,
  Typography,
  Alert,
  Skeleton,
  Box,
} from "@mui/material";
import InsertDriveFileIcon from "@mui/icons-material/InsertDriveFile";
import type { FileMetaDataResponseDto } from "../types";
import { useGetFilesQuery } from "../services/api";
import { useNavigate } from "react-router-dom";

const FileList: React.FC = () => {
  const { data, isLoading, isError, error } = useGetFilesQuery();
  const navigate = useNavigate();

  if (isLoading) {
    return (
      <Grid container spacing={2}>
        {[...Array(6)].map((_, idx) => (
          <Grid size={{ xs: 12, sm: 6, md: 4 }} key={idx}>
            <Skeleton variant="rectangular" height={100} animation="wave" />
          </Grid>
        ))}
      </Grid>
    );
  }

  if (isError)
    return (
      <Alert severity="error">
        {(error as any)?.data?.message || "Error loading files."}
      </Alert>
    );

  return (
    <Grid container spacing={2}>
      {data?.data.map((file: FileMetaDataResponseDto) => (
        <Grid size={12} key={file.id}>
          <Box
            onClick={() => navigate(`/files/${file.id}/view`)}
            sx={{
              cursor: "pointer",
              transition: "transform 0.2s, box-shadow 0.2s",
              "&:hover": {
                transform: "scale(1.02)",
                boxShadow: 4,
                borderRadius: 3,
              },
            }}
          >
            <Card
              elevation={3}
              sx={{
                display: "flex",
                alignItems: "center",
                p: 2,
                borderRadius: 3,
              }}
            >
              <InsertDriveFileIcon
                color="primary"
                fontSize="large"
                sx={{ mr: 2 }}
              />
              <CardContent sx={{ p: 0 }}>
                <Typography variant="subtitle1" fontWeight={600}>
                  {file.originalName}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {(file.size / 1024).toFixed(2)} KB • {file.fileType}
                </Typography>
              </CardContent>
            </Card>
          </Box>
        </Grid>
      ))}
    </Grid>
  );
};

export default FileList;
