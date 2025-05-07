import React from "react";
import { Box, Typography } from "@mui/material";
import FileUploadButton from "../components/FileUploadButton";
import FileList from "../components/FileList";

const UserHome: React.FC = () => {
  return (
    <Box p={4}>
      <Typography variant="h4" gutterBottom>
        Your Uploaded Files
      </Typography>
      <FileUploadButton />
      <FileList />
    </Box>
  );
};

export default UserHome;
