import React from "react";
import { Box, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const NotFound: React.FC = () => {
  const navigate = useNavigate();

  return (
    <Box textAlign="center" mt={10}>
      <Typography variant="h3" color="error" gutterBottom>
        404 - Page Not Found
      </Typography>
      <Typography variant="body1" gutterBottom>
        The page you're looking for doesn't exist or has been moved.
      </Typography>
      <Button variant="contained" onClick={() => navigate("/")}>
        Go to Home
      </Button>
    </Box>
  );
};

export default NotFound;
