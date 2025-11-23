// --- INSTRUCTOR NOTE: This is your main src/App.jsx ---
// It holds the "Source of Truth" (the data).

import { useState } from "react";
import ExpenseForm from "./components/ExpenseForm";
import ExpenseList from "./components/ExpenseList/ExpenseList";
import ExpensesChart from "./components/Expenses/ExpensesChart";
import ExpensesFilter from "./components/Expenses/ExpensesFilter";

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
  const [expenses, setExpenses] = useState(DUMMY_EXPENSES);
  // 1. State for the Filter
  const [filteredYear, setFilteredYear] = useState('2024');

  const addExpenseHandler = (expense) => {
    const expenseWithId = { ...expense, id: Math.random().toString() };
    setExpenses((prev) => [expenseWithId, ...prev]);
  };

  const filterChangeHandler = (selectedYear) => {
    setFilteredYear(selectedYear);
  };

  // 2. DERIVED STATE (Filtering)
  // We do not create a new "state" for filtered expenses.
  // We just calculate it on the fly. This keeps data in sync.
  const filteredExpenses = expenses.filter((expense) => {
    return expense.date.getFullYear().toString() === filteredYear;
  });

  return (
    <div className="min-h-screen bg-slate-900 py-12 px-4 font-sans">
      <header className="max-w-2xl mx-auto mb-8 text-center">
        <h1 className="text-3xl font-bold text-white mb-2">Expense Tracker</h1>
        <p className="text-slate-400">Phase 3: Reporting & Filtering</p>
      </header>

      <ExpenseForm onSaveExpenseData={addExpenseHandler} />

      <div className="w-full max-w-2xl mx-auto bg-slate-50 p-6 rounded-2xl shadow-inner mt-6">
        {/* Controlled Component: Value and Change Handler passed from parent */}
        <ExpensesFilter
          selected={filteredYear}
          onChangeFilter={filterChangeHandler}
        />

        {/* Visual Report based on filtered data */}
        <ExpensesChart expenses={filteredExpenses} />

        {/* List showing only filtered items */}
        <ExpenseList items={filteredExpenses} />
      </div>
    </div>
  );
}

export default App;