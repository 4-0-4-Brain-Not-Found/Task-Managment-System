import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie'; 
import '../admin_styles/admin-login.css'; 

const AdminLogin = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/admin/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.statusCode === 200) {
          // Token'ı ve rolü çerezde sakla
          Cookies.set('adminToken', data.token, { expires: 1 }); 
          Cookies.set('role', data.role, { expires: 1 }); // Store role in cookie
          alert('Hoşgeldiniz, Admin!');
          navigate('/admin'); 
        } else {
          setError('Kullanıcı adı veya şifre yanlış!');
        }
      })
      .catch((error) => {
        setError('Bir hata oluştu.');
        console.error('Login error:', error);
      });
  };

  return (
    <div className="login-body">
      <div className="container">
        <h2>Admin Login</h2>
        <form onSubmit={handleLogin}>
          {/* Hata mesajı */}
          {error && <div className="error-message">{error}</div>}

          <input
            type="text"
            placeholder="Kullanıcı Adı"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <input
            type="password"
            placeholder="Şifre"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit">Giriş Yap</button>
        </form>

      </div>
    </div>
  );
};

export default AdminLogin;
