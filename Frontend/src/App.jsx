import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./UI/components/layout/Layout/Layout.jsx";
import HomePage from "./UI/pages/HomePage/HomePage.jsx";
import BooksPage from "./UI/pages/BooksPage/BooksPage.jsx";
import AuthorsPage from "./UI/pages/AuthorsPage/AuthorsPage.jsx";
import CountriesPage from "./UI/pages/CountriesPage/CountriesPage.jsx";
import BookDetails from "./UI/components/books/BookDetails/BookDetails.jsx"

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="books" element={<BooksPage />} />
                    <Route path="books/:id" element={<BookDetails/>}/>
                    <Route path="authors" element={<AuthorsPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;
