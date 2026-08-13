import { useState,useEffect } from "react";
import axiosClient from '../api/axiosClient';

function TransactionForm({onTransactionAdded,editingTransaction,onEditComplete}){
    const[amount,setAmount]=useState("");
    const[type,setType]=useState("INCOME");
    const[category,setCategory]=useState("");
    const[description,setDescription]=useState("");
    const[date,setDate]=useState("");
    const[error,setError]=useState("");
    useEffect(()=>{
        if(editingTransaction){
            console.log(editingTransaction);
            setAmount(editingTransaction.amount);
            setType(editingTransaction.type);
            setCategory(editingTransaction.category);
            setDescription(editingTransaction.description);
            setDate(editingTransaction.date);
            setError("");
        }
    },[editingTransaction]);
    const handleCancelEdit=()=>{
        onEditComplete();
        onTransactionAdded();
            setAmount("");
            setType("INCOME");
            setCategory("");
            setDescription("");
            setDate("");
    }
    const handleSubmit=async(e)=>{
        e.preventDefault();
        setError("");
        const transaction={
            amount,
            type,
            category,
            description,
            date
        };
        try{
            if(editingTransaction){
                await axiosClient.put(`/transactions/${editingTransaction.id}`,transaction);
                onEditComplete();
            }
            else{
            await axiosClient.post("/transactions",transaction);
            }
        }catch(error){
            console.log("Transaction Failed: ",error);
            setError(
                error.response?.data || "Something went wrong"
            )
        }
    };
    return(
        <>
        {error && (
            <p>{error}</p>
        )}
        <form onSubmit={handleSubmit}>
            <input type="number" placeholder="Amount" value={amount}
            onChange={(e)=>setAmount(e.target.value)}/>
            <br />
            <br />
            <select value={type} onChange={(e)=>setType(e.target.value)}>
                <option value="INCOME">INCOME</option>
                <option value="EXPENSE">EXPENSE</option>
            </select>
            <br /><br />
            <input type="text" placeholder="Category" value={category}
            onChange={(e)=>setCategory(e.target.value)}/>
            <br /><br />
            <input type="text" placeholder="Description" value={description}
            onChange={(e)=>setDescription(e.target.value)}/>
            <br /><br />
            <input type="date" placeholder="Date" value={date}
            onChange={(e)=>setDate(e.target.value)}/>
            <br /><br />
            <button type="submit">
               {editingTransaction? "Update Transaction":"Add Transaction"}
            </button>
            <br /> <br />
            {editingTransaction &&
            <button onClick={handleCancelEdit}>Cancel</button>}
        </form>
        </>
    );
}
export default TransactionForm;