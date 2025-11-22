// --- INSTRUCTOR NOTE: Create this in src/components/ExpenseItem.jsx ---
// This is a "Stateless" or "Dumb" component.
// It only cares about displaying the data passed to it via 'props'.

import ExpenseDate from './ExpenseDate';

const ExpenseItem = ({ title, amount, date }) => {
    return (
        <div className="flex items-center justify-between p-3 my-3 bg-white rounded-xl shadow-sm hover:shadow-md transition-shadow duration-300 border-l-4 border-indigo-500">
            <div className="flex items-center gap-4">
                <ExpenseDate date={date} />
                <h2 className="text-lg font-semibold text-slate-800">{title}</h2>
            </div>
            <div className="bg-indigo-100 text-indigo-700 font-bold py-2 px-4 rounded-lg border border-indigo-200">
                ${amount.toFixed(2)}
            </div>
        </div>
    );
};

export default ExpenseItem;