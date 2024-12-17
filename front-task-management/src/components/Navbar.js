import React from 'react';
import Cookies from 'js-cookie';
import '../styles/Navbar.css'; 

const Navbar = ({ onLogout }) => {
  const handleLogout = () => {
    Cookies.remove('token'); 
    Cookies.remove('role');
    if (onLogout) onLogout();
  };

  return (
    <nav className="navbar">
      <div className="navbar-title">Task Management System</div>
      <button className="logout-button" onClick={handleLogout}>
        Logout
      </button>
    </nav>
  );
};

export default Navbar;
