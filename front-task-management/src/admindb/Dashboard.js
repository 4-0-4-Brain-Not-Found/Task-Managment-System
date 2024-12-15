import React from 'react';
import '../admin_styles/dashboard.css'; // Dashboard stilini import et

const Dashboard = () => {
  // Örnek log kayıtları
  const logs = [
    { id: 1, message: 'Admin logged in successfully.', date: '2024-12-10 08:30:00' },
    { id: 2, message: 'New user created: John Doe.', date: '2024-12-10 09:00:00' },
    { id: 3, message: 'Task "Report Generation" completed.', date: '2024-12-10 09:15:00' },
    { id: 4, message: 'User "Jane Smith" updated.', date: '2024-12-10 09:30:00' },
  ];

  return (
    <div className="dashboard">
      <h3>Dashboard</h3>
      <p>Welcome to the admin dashboard.</p>

      {/* Log Kayıtları */}
      <div className="logs">
        <h4>Recent Log Entries</h4>
        {logs.map((log) => (
          <div className="log-item" key={log.id}>
            <span>{log.message}</span>
            <p>{log.date}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
  