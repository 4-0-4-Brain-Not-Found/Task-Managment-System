import React from 'react';

const Navbar = () => {
  const navbarStyle = {
    display: 'flex',
    justifyContent: 'center',   // Centers the text horizontally
    alignItems: 'center',       // Centers the text vertically
    height: '60px',             // Height of the navbar
    backgroundColor: '#333',    // Dark background for the navbar
  };

  const headingStyle = {
    color: 'white',             // White text color
    fontSize: '24px',           // Font size
    textAlign: 'center',       // Ensure text is centered
  };

  return (
    <div style={navbarStyle}>
      <h2 style={headingStyle}>Task Management Admin Panel</h2>
    </div>
  );
};

export default Navbar;
