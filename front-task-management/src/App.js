import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import TaskManager from './components/TaskManager';
import './styles/styles.css';
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
              <div className="app-container">
                <div className="form-toggle">
                  <button onClick={() => setShowLogin(true)} className={showLogin ? "active" : ""}>Login</button>
                  <button onClick={() => setShowLogin(false)} className={!showLogin ? "active" : ""}>Register</button>
                </div>
                {showLogin ? <Login /> : <Register />}
              </div>
            }
          />

          <Route path="/task-manager" element={<TaskManager />} />
        </Routes>

        <div className="nav-links">
          <Link to="/task-manager">Go to Task Manager</Link>
        </div>
      </div>
    </Router>
  );
}

export default App;
