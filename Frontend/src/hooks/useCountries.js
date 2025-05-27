import {useCallback, useEffect, useState} from "react";
import countriesRepository from "../repository/countriesRepository.js";

const initialState = {
    "countries": [],
    "loading": true,
};

const useCountries = () => {
    const [state, setState] = useState(initialState);

    const fetchCountries = useCallback(() => {
        countriesRepository
            .findAll()
            .then((response) => {
                setState({
                    "countries": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        countriesRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new country.");
                fetchCountries();
            })
            .catch((error) => console.log(error));
    }, [fetchCountries]);

    const onEdit = useCallback((id, data) => {
        countriesRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the country with ID ${id}.`);
                fetchCountries()
            })
            .catch((error) => console.log(error));
    }, [fetchCountries]);

    const onDelete = useCallback((id) => {
        countriesRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the country with ID ${id}.`);
                fetchCountries();
            })
            .catch((error) => console.log(error));
    }, [fetchCountries]);

    useEffect(() => {
        fetchCountries();
    }, [fetchCountries]);
//This useEffect runs the function fetchBooks() after the component is mounted or when fetchBooks changes.

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
};

export default useCountries;