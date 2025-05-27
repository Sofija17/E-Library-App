import {useCallback, useEffect, useState} from "react";
import authorsRepository from "../repository/authorsRepository.js"

const initialState = {
    "authors" : [],
    "loading" : true
};


const useAuthors = () => {
    const [state, setState] = useState(initialState);

    const fetchAuthors = useCallback(() => {
            authorsRepository
                .findAll()
                .then((response) => {
                    setState({
                        "authors": response.data,
                        "loading": false,
                    });
                })
                .catch((error) => console.log(error));
        }, []);

    const onAdd = useCallback((data) => {
        authorsRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new Author!");
                fetchAuthors()
            })
            .catch((error) => console.log(error))
    }, [fetchAuthors]);

    const onEdit = useCallback((id,data) => {
        authorsRepository
            .edit(id,data)
            .then(() => {
                console.log(`Successfully edited an existing Author with id ${id}!`);
                fetchAuthors()
            })
            .catch((error) => console.log(error))
    }, [fetchAuthors]);

    const onDelete = useCallback((id) => {
        authorsRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully delete the Author with id: ${id}!`);
                fetchAuthors()
            })
            .catch((error) => console.log(error))
    }, [fetchAuthors]);

    useEffect(() => {
        fetchAuthors();
    }, [fetchAuthors]);

    //useEffect runs after the component using this hook is mounted

    // It calls fetchAuthors() to load data from the server
    // only once (because fetchAuthors is memoized and won’t change)

    return {...state, onAdd:onAdd, onEdit:onEdit, onDelete:onDelete};

}

export default useAuthors;