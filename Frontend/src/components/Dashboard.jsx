import { useEffect,useState } from "react";
import { getProfile } from "../Services/authService";
import TransactionForm from "./Transactions";
import axiosClient from "../api/axiosClient";
function Dashboard({onLogout}){
    const[email,setEmail]=useState("");
    const[transactions,setTransactions]=useState([]);
    const[editingTransaction,setEditingTransaction]=useState(null);
    const totalIncome=transactions.filter((transaction)=>transaction.type==="INCOME")
    .reduce((total,transaction)=>total+Number(transaction.amount),0);

    const totalExpense=transactions.filter((transaction)=>transaction.type==="EXPENSE")
    .reduce((total,transaction)=>total+Number(transaction.amount),0);

    const balance=totalIncome-totalExpense;
    const fetchTransactions=async()=>{
        try{
            const response=await axiosClient.get("/transactions");
            setTransactions(response.data);
        }
        catch(error){
            console.log("Transaction error",error);
        }
    };
    const handleDelete=async(id)=>{
        try{
            await axiosClient.delete(`/transactions/${id}`);
            fetchTransactions();
        }
        catch(error){
            console.log("Delete Failed",error);
        }
    };
    useEffect(()=>{
        axiosClient.get("/users/profile").then(
            (response)=>{
                console.log(response);
                setEmail(response.data);
            }).catch((error)=>{
                console.log("Profile error:",error);
            });
       fetchTransactions();
    },[]);
    return(
        <>
        <header>
        <h1>Dashboard</h1>
        <p>Welcome {email}</p>
        <p>Balance : {balance}</p>
        <p>TotalIncome: {totalIncome}</p>
        <p>TotalExpense: {totalExpense}</p>
        <button onClick={onLogout}>Log Out</button>
        </header>
        <main>
            <h2>Overview</h2>
            <TransactionForm onTransactionAdded={fetchTransactions}
            editingTransaction={editingTransaction}
            onEditComplete={()=>setEditingTransaction(null)}/>
            <h2>Transaction</h2>
            {transactions.length===0?(
                <p>No Transactions Yet</p>
            ):(
                <div>
                    {transactions.map((transaction)=>(
                        <div key={transaction.id}>
                            <p>Amount: {transaction.amount}</p>
                            <p>Type: {transaction.type}</p>
                            <p>Category: {transaction.category}</p>
                            <p>Description: {transaction.description}</p>
                            <p>Date: {transaction.date}</p>
                            <button onClick={()=>{
                                setEditingTransaction(transaction)}
                                }>Edit</button>
                            <button onClick={()=>handleDelete(transaction.id)}>Delete</button>
                        </div>
                    ))}
                </div>
            )
            }
        </main>
        </>
    );
}
export default Dashboard;