import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import TaskManager from './components/TaskManager';
import Navbar from './admindb/AdminNavbar';
import Sidebar from './admindb/Sidebar';
import Dashboard from './admindb/Dashboard';
import UserOperations from './admindb/UserOperations';
import TaskOperations from './admindb/TaskOperation';
import AdminLogin from './admindb/AdminLogin'; 
import './styles/login-register.css'


function App() {
  const [showLogin, setShowLogin] = useState(true);

  return (
    <Router>
      <div className="main-container">
        <Routes>
          <Route
            path="/"
            element={
              <div className='login-reg-body'>
              <div className="app-container">
                <div className="form-toggle">
                  <button onClick={() => setShowLogin(true)} className={showLogin ? "active" : ""}>Login</button>
                  <button onClick={() => setShowLogin(false)} className={!showLogin ? "active" : ""}>Register</button>
                </div>
                {showLogin ? <Login /> : <Register />}
              </div>
              </div>
            }
          />

          <Route path="/task-manager" element={<TaskManager />} />

          {/* Login sayfası için navbar ve sidebar yok */}
        <Route path="/admin/login" element={<AdminLogin />} />
        
        {/* Diğer sayfalarda navbar ve sidebar olacak */}
        <Route path="/admin/" element={<><Navbar /><Sidebar /><Dashboard /></>} />
        <Route path="/admin/users" element={<><Navbar /><Sidebar /><UserOperations /></>} />
        <Route path="/admin/tasks" element={<><Navbar /><Sidebar /><TaskOperations /></>} />

      </Routes>
          


      </div>
    </Router>
  );
}

export default App;
