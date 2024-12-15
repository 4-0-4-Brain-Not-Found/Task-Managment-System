import React, { useState } from 'react';
import '../styles/login-register.css'
import axios from 'axios';

function Register() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleRegister = async () => {
    
    const registrationData = {
      username,
      email,
      password,
      phoneNumber,
      
    };

    try {
      const response = await axios.post('http://localhost:8080/register', registrationData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.status === 200) {
        setSuccess('Registration successful, please login!');
        setError('');
      } else {
        setError(response.data.message || 'Registration failed');
      }
    } catch (error) {
      console.error(error.response ? error.response.data : error);
      setError('An error occurred while registering');
    }
  };

  return (
    <div className="form-container">
      <h2>Register</h2>
      <div className="form-group">
        <label>Username</label>
        <input
          type="text"
          placeholder="Enter username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Email</label>
        <input
          type="email"
          placeholder="Enter email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Password</label>
        <input
          type="password"
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <div className="form-group">
        <label>Phone Number</label>
        <input
          type="tel"
          placeholder="Enter phone number"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
        />
      </div>
      {error && <p className="error">{error}</p>}
      {success && <p className="success">{success}</p>}
      <button onClick={handleRegister} className="submit-btn">Register</button>
    </div>
  );
}

export default Register;
