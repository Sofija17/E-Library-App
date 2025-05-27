import axiosInstance from "../axios/axios.js";

const booksRepository = {
    findAll: async () => {
        return await axiosInstance.get("/books");
        //axiosInstance.get() vrakja promise bidejki e asinhrona funkcija
    },
    add: async (data) => {
        return await axiosInstance.post("/books/add", data);
    },
    findById: async (id) => {
        return await axiosInstance.get(`/books/${id}`);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/books/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/books/delete/${id}`);
    },
};

export default booksRepository;