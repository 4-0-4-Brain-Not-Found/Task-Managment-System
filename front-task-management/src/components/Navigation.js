import { Link } from 'react-router-dom';

function Navigation() {
  return (
    <nav className="navigation">
      <Link to="/login">Login</Link>
      <Link to="/register">Register</Link>
      <Link to="/task-manager">Task Manager</Link>
    </nav>
  );
}

export default Navigation;
