import React, { useState, useEffect } from 'react';
import '../admin_styles/admin_page.css'; 
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';  
import axios from 'axios'; 

const UserOperations = () => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState('');
  const [editingUser, setEditingUser] = useState(null);
  

  
  useEffect(() => {
    axios
      .get('/admin/users', {
        headers: {
          'Authorization': `Bearer ${Cookies.get('adminToken')}`, // Admin token'ı ile doğrulama
        },
      })
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => {
        setError('Bir hata oluştu.');
        console.error('Error fetching users:', error);
      });
  }, []);


  const handleEdit = (user) => {
    setEditingUser({ ...user });
  };

  // Kullanıcıyı güncelle
  const handleSave = (id) => {
    axios
      .put(`/admin/users/${id}`, editingUser, {
        headers: {
          'Authorization': `Bearer ${Cookies.get('adminToken')}`,
        },
      })
      .then((response) => {
     
        setUsers((prevUsers) => 
          prevUsers.map((user) => (user.id === id ? response.data : user))
        );
        setEditingUser(null); 
      })
      .catch((error) => {
        setError('Bir hata oluştu.');
        console.error('Error updating user:', error);
      });
  };

  // Kullanıcıyı silme işlemi
  const handleDelete = (id) => {
    const confirmDelete = window.confirm("Bu kullanıcıyı silmek istediğinizden emin misiniz?");
    if (confirmDelete) {
      axios
        .delete(`/admin/users/${id}`, {
          headers: {
            'Authorization': `Bearer ${Cookies.get('adminToken')}`,
          },
        })
        .then(() => {
          setUsers((prevUsers) => prevUsers.filter((user) => user.id !== id));
        })
        .catch((error) => {
          setError('Bir hata oluştu.');
          console.error('Error deleting user:', error);
        });
    }
  };

  return (
    <div className="user-operations">
      <h3>User Operations</h3>
      {error && <div className="error-message">{error}</div>}
      <table>
        <thead>
          <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Phone No</th>
            <th>Password</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.length === 0 ? (
            <tr>
              <td colSpan="6">No users found</td>
            </tr>
          ) : (
            users.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>
                  {editingUser && editingUser.id === user.id ? (
                    <input
                      type="text"
                      value={editingUser.username}
                      onChange={(e) => setEditingUser({ ...editingUser, username: e.target.value })}
                    />
                  ) : (
                    user.username
                  )}
                </td>
                <td>
                  {editingUser && editingUser.id === user.id ? (
                    <input
                      type="email"
                      value={editingUser.email}
                      onChange={(e) => setEditingUser({ ...editingUser, email: e.target.value })}
                    />
                  ) : (
                    user.email
                  )}
                </td>
                <td>
                  {editingUser && editingUser.id === user.id ? (
                    <input
                      type="text"
                      value={editingUser.phoneNo}
                      onChange={(e) => setEditingUser({ ...editingUser, phoneNo: e.target.value })}
                    />
                  ) : (
                    user.phoneNo
                  )}
                </td>
                <td>
                  {editingUser && editingUser.id === user.id ? (
                    <input
                      type="text" 
                      value={editingUser.password}
                      onChange={(e) => setEditingUser({ ...editingUser, password: e.target.value })}
                    />
                  ) : (
                    '******' 
                  )}
                </td>
                <td>
                  {editingUser && editingUser.id === user.id ? (
                    <button onClick={() => handleSave(user.id)}>Save</button>
                  ) : (
                    <>
                      <button onClick={() => handleEdit(user)}>Edit</button>
                      <button onClick={() => handleDelete(user.id)}>Delete</button>
                    </>
                  )}
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default UserOperations;
