// --- INSTRUCTOR NOTE: This is your main src/App.jsx ---
// It holds the "Source of Truth" (the data).

import { useState } from "react";
import ExpenseForm from "./components/ExpenseForm";
import ExpenseList from "./components/ExpenseList";

// Mock data simulates a database response
const DUMMY_EXPENSES = [
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

function App() {
  // TEACHING POINT: Initialize state with dummy data
  const [expenses, setExpenses] = useState(DUMMY_EXPENSES);

  const addExpenseHandler = (expense) => {
    // Add a random ID (in a real app, the backend does this)
    const expenseWithId = { ...expense, id: Math.random().toString() };

    // TEACHING POINT: Updating state based on previous state
    // We pass a function to setExpenses to ensure we have the latest snapshot
    setExpenses((prevExpenses) => {
      return [expenseWithId, ...prevExpenses];
    });
  };

  return (
    <div className="min-h-screen bg-slate-900 py-12 px-4 font-sans">
      <header className="max-w-2xl mx-auto mb-8 text-center">
        <h1 className="text-3xl font-bold text-white mb-2">
          Expense Tracker
        </h1>
        <p className="text-slate-400">
          Phase 2: State & Events
        </p>
      </header>

      {/* Pass the handler DOWN to the form */}
      <ExpenseForm onSaveExpenseData={addExpenseHandler} />

      {/* Pass the state DOWN to the list */}
      <ExpenseList items={expenses} />
    </div>
  );
}

export default App;