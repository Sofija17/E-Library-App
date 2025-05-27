import axiosInstance from "../axios/axios.js";

const authorsRepository = {
    findAll: async () => {
        return await axiosInstance.get("/authors");
        //axiosInstance.get() vrakja promise bidejki e asinhrona funkcija
    },
    add: async (data) => {
        return await axiosInstance.post("/authors/add", data);
    },
    findById: async (id) => {
        return await axiosInstance.get(`/authors/${id}`);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/authors/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/authors/delete/${id}`);
    },
};

export default authorsRepository;