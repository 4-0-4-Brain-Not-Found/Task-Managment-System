
import React from 'react';
import '../admin_styles/navbar.css'; 

const Navbar = () => {
  return (
    <div className="navbar">
      <h2>Admin Panel</h2>
      <div className="welcome">
        <span>Hoşgeldin, Admin</span>
      </div>
    </div>
  );
};

export default Navbar;
