import axios from 'axios';

const axiosServices = axios.create();

axiosServices.interceptors.request.use(
    (config) => {
        config.headers.Authorization = `Bearer ` + localStorage.getItem("x-jwtt");

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Interceptor for HTTP response
axiosServices.interceptors.response.use(
    (response) => response,
    (error) => Promise.reject((error.response && error.response.data) || 'Wrong Services')
);

export default axiosServices;
