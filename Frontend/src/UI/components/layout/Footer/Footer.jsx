import React from 'react';
import { Box, Typography } from '@mui/material';

const Footer = () => {
    return (
        <Box
            component="footer"
            sx={{
                width: '100%',
                py: 2,
                px: 4,
                mt: 'auto',
                backgroundColor: '#e57373',
                color: 'white',
                textAlign: 'center',
            }}
        >
            <Typography variant="body2">
                © {new Date().getFullYear()} E-Library. All rights reserved.
            </Typography>
        </Box>
    );
};

export default Footer;




