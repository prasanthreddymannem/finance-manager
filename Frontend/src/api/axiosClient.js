import axios from "axios";

const axiosClient = axios.create({
    baseURL: "http://localhost:8080"
});

axiosClient.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    const publicUrls=[
        "/users/login",
        "/users/register"
    ]
    if (token && !publicUrls.includes(config.url)) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

export default axiosClient;