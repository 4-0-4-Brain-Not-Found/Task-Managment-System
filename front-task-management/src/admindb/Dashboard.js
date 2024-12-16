import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';  // For reading cookies
import axios from 'axios';  // Import Axios
import '../admin_styles/admin_page.css'; 

const Dashboard = () => {
  const [logs, setLogs] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchLogs = async () => {
      const token = Cookies.get('adminToken'); 

      try {
        const response = await axios.get('/admin/logs', {
          headers: {
            'Authorization': `Bearer ${token}`,  
          },
        });

        setLogs(response.data);  
      } catch (err) {
        setError('Bir hata oluştu.');
        console.error('Error fetching logs:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchLogs();
  }, []);

  return (
    <div className="user-operations">
      <h3>Dashboard</h3>
      {loading && <p>Loading logs...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && logs.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Message</th>
              <th>Timestamp</th>
              <th>Username</th>
            </tr>
          </thead>
          <tbody>
            {logs.map((log, index) => (
              <tr key={index}>
                <td>{log.message}</td>
                <td>{log.timestamp}</td>
                <td>{log.username}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No logs found.</p>
      )}
    </div>
  );
};

export default Dashboard;
