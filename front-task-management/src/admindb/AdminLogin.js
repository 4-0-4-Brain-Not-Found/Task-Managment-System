import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../admin_styles/admin-login.css'; // Login stilini import et

const AdminLogin = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    // Basit bir kontrol, gerçek projelerde API ile doğrulama yapılmalı
    if (username === 'admin' && password === 'admin123') {
      alert('Hoşgeldiniz, Admin!');
      navigate('/'); // Başarılı giriş sonrası ana sayfaya yönlendirme
    } else {
      setError('Kullanıcı adı veya şifre yanlış!');
    }
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
        <a href="#" className="forgot-password">
          Şifrenizi mi unuttunuz?
        </a>
      </div>
    </div>
  );
};

export default AdminLogin;
