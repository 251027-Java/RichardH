// --- INSTRUCTOR NOTE: This is your main src/App.jsx ---
// It holds the "Source of Truth" (the data).

import React from "react";
import ExpenseList from "./components/ExpenseList";

function App() {
  // Mock data simulates a database response
  const expenses = [
    {
      id: 'e1',
      title: 'Office Supplies',
      amount: 94.12,
      date: new Date(2023, 7, 14),
    },
    { 
      id: 'e2', 
      title: 'New Laptop', 
      amount: 799.49, 
      date: new Date(2024, 2, 12) 
    },
    {
      id: 'e3',
      title: 'Car Insurance',
      amount: 294.67,
      date: new Date(2024, 2, 28),
    },
    {
      id: 'e4',
      title: 'Team Lunch',
      amount: 120.50,
      date: new Date(2024, 5, 12),
    },
  ];

  return (
    <div className="min-h-screen bg-slate-900 py-12 px-4 font-sans">
      <header className="max-w-2xl mx-auto mb-8 text-center">
        <h1 className="text-3xl font-bold text-white mb-2">
          Expense Tracker
        </h1>
        <p className="text-slate-400">
          Phase 1: Static Components & Props
        </p>
      </header>
      
      {/* Pass data down via props */}
      <ExpenseList items={expenses} />
    </div>
  );
}

export default App;