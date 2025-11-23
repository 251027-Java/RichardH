// --- INSTRUCTOR NOTE: This is your main src/App.jsx ---
// It holds the "Source of Truth" (the data).

import { useState, useEffect } from "react";
import ExpenseForm from "./components/ExpenseForm/ExpenseForm";
import ExpenseList from "./components/ExpenseList/ExpenseList";
import ExpensesChart from "./components/Expenses/ExpensesChart";
import ExpensesFilter from "./components/Expenses/ExpensesFilter";
import ReportSummary from "./components/ReportSummary";
import SavedReportsList from "./components/SavedReportsList";

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
  const [filteredYear, setFilteredYear] = useState('2024');
  const [selectedIds, setSelectedIds] = useState([]);
  const [isReportVisible, setIsReportVisible] = useState(false);

  // --- TEACHING POINT 1: Lazy Initialization ---
  // We initialize the state by reading from LocalStorage.
  // This function only runs ONCE (on mount), so it's efficient.
  const [savedReports, setSavedReports] = useState(() => {
    try {
      const storedReports = localStorage.getItem('mySavedReports');
      return storedReports ? JSON.parse(storedReports) : [];
    } catch (e) {
      console.warn("Failed to read local storage", e);
      return [];
    }
  });

    // --- TEACHING POINT 2: useEffect for Persistence ---
  // Every time [savedReports] changes (Add OR Delete), we write to LocalStorage.
  useEffect(() => {
    localStorage.setItem('mySavedReports', JSON.stringify(savedReports));
  }, [savedReports]);

  const addExpenseHandler = (expense) => {
    const expenseWithId = { ...expense, id: Math.random().toString() };
    setExpenses((prev) => [expenseWithId, ...prev]);
  };

  const filterChangeHandler = (selectedYear) => {
    setFilteredYear(selectedYear);
  };

  const toggleExpenseHandler = (id) => {
    setSelectedIds((prevSelected) => {
      if (prevSelected.includes(id)) {
        return prevSelected.filter(item => item !== id);
      } else {
        return [...prevSelected, id];
      }
    });
  };

  const filteredExpenses = expenses.filter((expense) => {
    return expense.date.getFullYear().toString() === filteredYear;
  });

  // NEW LOGIC: Handling the Save Action
  const saveReportHandler = (reportData) => {
    setSavedReports((prevReports) => [reportData, ...prevReports]);
    setIsReportVisible(false); // Close modal on save
    setSelectedIds([]); // Optional: Clear selection after saving
  };

  const deleteReportHandler = (reportId) => {
    setSavedReports(prev => prev.filter(r => r.id !== reportId));
  };

  const reportExpenses = expenses.filter(expense => selectedIds.includes(expense.id));

  return (
    <div className="min-h-screen bg-slate-900 py-12 px-4 font-sans">
      <header className="max-w-2xl mx-auto mb-8 text-center">
        <div>
          <h1 className="text-3xl font-bold text-slate-900">Expense Tracker</h1>
          <p className="text-slate-500">Phase 4: Reports & Persistence</p>
        </div>

        <button
          onClick={() => setIsReportVisible(true)}
          disabled={selectedIds.length === 0}
          className={`px-6 py-3 rounded-xl font-bold transition-all shadow-md flex items-center gap-2 ${selectedIds.length > 0
            ? 'bg-indigo-600 text-white hover:bg-indigo-700 translate-y-0'
            : 'bg-slate-300 text-slate-500 cursor-not-allowed'
            }`}
        >
          <span>Generate Report</span>
          {selectedIds.length > 0 && (
            <span className="bg-indigo-800 text-xs px-2 py-1 rounded-full">{selectedIds.length}</span>
          )}
        </button>
      </header>

      <div className="w-full max-w-2xl mx-auto">
        <ExpenseForm onSaveExpenseData={addExpenseHandler} />

        <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-200">
          {/* Controlled Component: Value and Change Handler passed from parent */}
          <ExpensesFilter
            selected={filteredYear}
            onChangeFilter={filterChangeHandler}
          />

          {/* Visual Report based on filtered data */}
          <ExpensesChart expenses={filteredExpenses} /><h3 className="text-slate-500 font-bold border-b pb-2 mb-2 uppercase text-xs tracking-wider">
            Select items to include in report
          </h3>
          <ExpenseList
            items={expenses}
            selectedIds={selectedIds}
            onToggleItem={toggleExpenseHandler}
          />
        </div>

        {/* Updated List with Delete functionality to show sync */}
        <SavedReportsList reports={savedReports} onDelete={deleteReportHandler} />
      </div>

      {isReportVisible && (
        <ReportSummary
          selectedExpenses={reportExpenses}
          onClose={() => setIsReportVisible(false)}
          onSave={saveReportHandler} // Pass the save handler down
        />
      )}
    </div>
  );
}

export default App;