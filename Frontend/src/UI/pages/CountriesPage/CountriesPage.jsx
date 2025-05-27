import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import ProductsGrid from "../../components/books/BooksGrid/BooksGrid.jsx";
import "./CountriesPage.css";
import useCountries from "../../../hooks/useCountries.js";
import AddCountryDialog from "../../components/countries/AddCountryDialog/AddCountryDialog.jsx"
import CountriesGrid from "../../components/countries/CountriesGrid/CountriesGrid.jsx";
import addCountryDialog from "../../components/countries/AddCountryDialog/AddCountryDialog.jsx";

const CountriesPage = () => {
    const {countries, loading, onAdd, onEdit, onDelete} = useCountries();
    const [addCountryDialogOpen, setAddCountryDialogOpen] = useState(false);

    return (
        <>
            <Box className="countries-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="error" onClick={() => setAddCountryDialogOpen(true)}>
                                Add new Country
                            </Button>
                        </Box>
                        <CountriesGrid countries={countries} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddCountryDialog
                open={addCountryDialogOpen}
                onClose={() => setAddCountryDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default CountriesPage;