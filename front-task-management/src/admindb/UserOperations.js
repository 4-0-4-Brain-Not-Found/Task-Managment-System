import React from 'react';
import '../admin_styles/admin_page.css'; // User Operations stilini import et

const UserOperations = () => {
  return (
    <div className="user-operations">
      <h3>User Operations</h3>
      <table>
        <thead>
          <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {/* Burada dinamik veri ile satır ekleyebilirsiniz */}
          <tr>
            <td>1</td>
            <td>john_doe</td>
            <td>john@example.com</td>
            <td><button>Edit</button></td>
          </tr>
          <tr>
            <td>2</td>
            <td>jane_doe</td>
            <td>jane@example.com</td>
            <td><button>Edit</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default UserOperations;
