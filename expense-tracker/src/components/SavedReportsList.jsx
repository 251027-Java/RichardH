// --- NEW COMPONENT: SavedReportsList ---
// Displays the history of saved reports

const SavedReportsList = ({ reports }) => {
    if (reports.length === 0) return null;

    return (
        <div className="mt-8 bg-white p-6 rounded-2xl shadow-sm border border-slate-200">
            <h3 className="text-slate-500 font-bold border-b pb-2 mb-4 uppercase text-xs tracking-wider">
                Saved Reports History
            </h3>
            <div className="grid gap-3">
                {reports.map(report => (
                    <div key={report.id} className="flex justify-between items-center p-4 bg-slate-50 rounded-lg border border-slate-100 hover:border-indigo-200 transition-colors">
                        <div className="flex flex-col">
                            <span className="font-bold text-slate-700">Report #{report.id.substring(2, 6)}</span>
                            <span className="text-xs text-slate-500">Generated: {report.date} • {report.itemCount} items</span>
                        </div>
                        <div className="text-indigo-700 font-mono font-bold text-lg">
                            ${report.total.toFixed(2)}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default SavedReportsList;