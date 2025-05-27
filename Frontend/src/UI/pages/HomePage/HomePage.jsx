import React from 'react';
import { Box, Typography } from '@mui/material';

const HomePage = () => {
    return (
        <Box  sx={{
            width: '100%',
            px: 4,
            py: 3,
            boxSizing: 'border-box',
        }}>

            <Typography variant="h4" gutterBottom>
                Welcome to E-Library App! 👋
            </Typography>
            <Typography variant="body1" sx={{ mb: 4 }}>
                This is the home page.
            </Typography>
        </Box>
    );
};



export default HomePage;
