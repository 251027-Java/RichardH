// --- INSTRUCTOR NOTE: Create this in src/components/ExpenseList.jsx ---
// This component handles the list logic. 
// Ideally, it should handle "Empty States" (if no expenses exist).

import ExpenseItem from './ExpenseItem';

const ExpenseList = ({ items }) => {
    if (items.length === 0) {
        return <p className="text-center text-slate-400 italic mt-4">No expenses found.</p>;
    }

    return (
        <div className="w-full max-w-2xl mx-auto bg-slate-50 p-6 rounded-2xl shadow-inner">
            <h3 className="text-slate-500 font-medium mb-4 border-b pb-2">Expense History</h3>

            {/* TEACHING POINT: The .map() method.
         React prefers mapping arrays to JSX over "for loops".
         Always mention the 'key' prop for performance! */}
            {items.map((expense) => (
                <ExpenseItem
                    key={expense.id}
                    title={expense.title}
                    amount={expense.amount}
                    date={expense.date}
                />
            ))}
        </div>
    );
};

export default ExpenseList;