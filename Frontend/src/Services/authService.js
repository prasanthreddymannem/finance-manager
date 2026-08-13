import axiosClient from "../api/axiosClient";
import axios from "axios";

export function login(loginRequest){
    return axiosClient.post("/users/login",loginRequest);
}
export function getProfile() {
    console.log("axiosClient", axiosClient.defaults.baseURL);
    return axiosClient.get("/users/profile");
}