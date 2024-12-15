import React, { useState } from 'react';
import '../styles/login-register.css'
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; 
import Cookies from 'js-cookie';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate(); 

  const handleLogin = async () => {
    const loginData = { username, password };
    try {
      const response = await axios.post('http://localhost:8080/login', loginData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.status === 200) {
        // tokenı Çerezde sakla
        Cookies.set('token', response.data.token, { secure: true, sameSite: 'Strict' });
  
        // İstenirse ayrıca role bilgisi de çerezde saklanabilir
        Cookies.set('role', response.data.role, { secure: true, sameSite: 'Strict' });
  
        navigate('/task-manager'); // yönlendirme
      } else {
        setError(response.data.message || 'Login failed');
      }
    } catch (error) {
      setError('An error occurred while logging in');
    }
  };
  

  return (
    
    <div className="form-container">
      <h2>Login</h2>
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
        <label>Password</label>
        <input
          type="password"
          placeholder="Enter password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      {error && <p className="error">{error}</p>}
      <button onClick={handleLogin} className="submit-btn">Login</button>
    </div>
  
  );
}

export default Login;
