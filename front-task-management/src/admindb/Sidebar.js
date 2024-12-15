import React from 'react';
import '../admin_styles/sidebar.css';

const Sidebar = () => {
  const adminName = "Admin";  // Admin'in ismini burada gösterebilirsiniz

  return (

    
    <div className="sidebar">

            {/* Admin Bilgileri */}
            <div className="admin-info">
        <p>Hoşgeldin, {adminName}</p>
        <p>Admin Paneli</p>
      </div>
      
      <a href="/" className="active">Dashboard</a>
      <a href="/admin/users">User Operations</a>
      <a href="/admin/tasks">Task Operations</a>
      

    </div>
  );
};

export default Sidebar;
