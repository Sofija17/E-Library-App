import React from 'react';
import { Box } from '@mui/material';
import Header from '../Header/Header.jsx';
import Footer from '../Footer/Footer.jsx'
import { Outlet } from 'react-router';
import './Layout.css';

const Layout = () => {
    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                minHeight: '100vh', // ✅ full screen height
                width: '100%',
            }}
        >
            <Header />
            <Box
                sx={{
                    flex: 1,               // ✅ this makes the content take remaining space
                    px: 4,
                    py: 2,
                    width: '100%',
                    boxSizing: 'border-box',
                }}
            >
                <Outlet />
            </Box>
            <Footer />  {/* ✅ will always stick at the bottom */}
        </Box>

    );
};

export default Layout;
