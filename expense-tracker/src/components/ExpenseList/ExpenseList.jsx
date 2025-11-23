// --- INSTRUCTOR NOTE: Create this in src/components/ExpenseList.jsx ---
// This component handles the list logic. 
// Ideally, it should handle "Empty States" (if no expenses exist).

import ExpenseItem from './ExpenseItem';

const ExpenseList = ({ items }) => {
    if (items.length === 0) {
        return <p className="text-center text-slate-400 italic mt-4">No expenses found for this year.</p>;
    }
    return (
        <ul className="mt-4">
            {items.map((expense) => (
                <ExpenseItem
                    key={expense.id}
                    title={expense.title}
                    amount={expense.amount}
                    date={expense.date}
                />
            ))}
        </ul>
    );
};

export default ExpenseList;