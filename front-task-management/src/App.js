import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import TaskBoard from './components/TaskBoard';
import Navbar from './admindb/AdminNavbar';
import Sidebar from './admindb/Sidebar';
import Dashboard from './admindb/Dashboard';
import UserOperations from './admindb/UserOperations';
import TaskOperations from './admindb/TaskOperation';
import AdminLogin from './admindb/AdminLogin';
import PrivateRoute from './components/PrivateRoute'; // PrivateRoute import
import ErrorPage from './components/ErrorPage'; // Import the error page
import './styles/login-register.css';

function App() {
  const [showLogin, setShowLogin] = useState(true);

  const renderAdminPage = (Component) => (
    <PrivateRoute element={
      <>
        <Navbar />
        <Sidebar />
        {Component}
      </>
    } />
  );

  return (
    <Router>
      <div className="main-container">
        <Routes>
          {/* Kullanıcı girişi veya kayıt sayfası */}
          <Route
            path="/"
            element={
              <div className="login-reg-body">
                <div className="app-container">
                  <div className="form-toggle">
                    <button onClick={() => setShowLogin(true)} className={showLogin ? "active" : ""}>
                      Login
                    </button>
                    <button onClick={() => setShowLogin(false)} className={!showLogin ? "active" : ""}>
                      Register
                    </button>
                  </div>
                  {showLogin ? <Login /> : <Register />}
                </div>
              </div>
            }
          />

          {/* TaskManager */}
          <Route path="/task-manager" element={<TaskBoard />} />

          {/* Admin login sayfası */}
          <Route path="/admin/login" element={<AdminLogin />} />

          {/* Admin için özel yönlendirme (PrivateRoute kullanımı) */}
          <Route path="/admin/" element={renderAdminPage(<Dashboard />)} />
          <Route path="/admin/users" element={renderAdminPage(<UserOperations />)} />
          <Route path="/admin/tasks" element={renderAdminPage(<TaskOperations />)} />

          {/* Error Page for Unauthorized Access */}
          <Route path="/error" element={<ErrorPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
