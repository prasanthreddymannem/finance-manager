import { useState } from "react";
import axiosClient from '../api/axiosClient';

function Login({onLoginSucess}){
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");

    const handleLogin=async(e)=>{
        e.preventDefault();
        try{
            const response=await axiosClient.post("/users/login",
                {
                    email:email,
                    password:password
                }
            );
            localStorage.setItem("token",response.data.token);
            onLoginSucess();
        }catch(error){
            console.log("Login Failed: ",error);
        }
    };
    return(
        <>
        <h1>Login</h1>
        <form onSubmit={handleLogin}>
            <div>
                <label>Email</label>
                <input type="email" value={email} onChange={(e)=>setEmail(e.target.value)}/>
            </div>
            <div>
                <label>Password</label>
                <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
            </div>
            <button type="submit">
                Login
            </button>
        </form>
        </>
    );
}

export default Login;