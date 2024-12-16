import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import Cookies from 'js-cookie'; 
import '../admin_styles/sidebar.css';

const Sidebar = () => {
  const navigate = useNavigate(); 


  const handleLogout = () => {
 
    Cookies.remove('adminToken');
    Cookies.remove('role');

   
    navigate('/admin/login');
  };

  return (
    <div className="sidebar">
      {/* Admin Bilgileri */}
      <div className="admin-info">
        <p>Admin Paneli</p>
      </div>
      
      <a href="/admin/" className="active">Dashboard</a>
      <a href="/admin/users">User Operations</a>
      <a href="/admin/tasks">Task Operations</a>
      
      {/* Logout Butonu */}
      <button onClick={handleLogout} className="logout-button">
        Logout
      </button>
    </div>
  );
};

export default Sidebar;
