import {useEffect, useState} from "react";
import bookRepository from "../repository/booksRepository.js";
import authorsRepository from "../repository/authorsRepository.js";

const useBookDetails = (id) => {
    const [state, setState] = useState({
        "book": null,
        "author": null,
    });

    useEffect(() => {
        bookRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "book": response.data}));
                authorsRepository
                    .findById(response.data.authorId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "author": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useBookDetails;