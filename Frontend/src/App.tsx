import { useState,useEffect } from "react";
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import axiosClient from './api/axiosClient';

function App(){
  const[loggedin,setLogged]=useState(false);
  const[auth,setAuth]=useState(true);
  useEffect(
    ()=>{
      const token=localStorage.getItem("token");
      console.log(token);
      if(!token){
        setAuth(false);
        return;
      }
      axiosClient.get("/users/profile")
      .then(()=>{
        setLogged(true);
      }).catch((err)=>{
        console.log(err);
        localStorage.removeItem("token");
        setLogged(false);
      }).finally(()=>{
        setAuth(false);
      });
      },[]);

  const handleLogin=()=>{
    setLogged(true);
  }
  const logOut=()=>{
    localStorage.removeItem("token");
    setLogged(false);
  }
  if(auth){
    return <h1>Checking Authentication</h1>;
  }
  if(loggedin){
    return <Dashboard onLogout={logOut}/>;
  }
  return (
    <Login onLoginSucess={handleLogin} />
  )
}
export default App;