import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../admin_styles/admin_page.css'; 
import Cookies from 'js-cookie';  

const TaskOperations = () => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState('');
  const [editingTaskId, setEditingTaskId] = useState(null);
  const [newStatus, setNewStatus] = useState('');


  const adminToken = Cookies.get('adminToken');

  const axiosInstance = axios.create({
    baseURL: '/admin/tasks', 
    headers: {
      Authorization: `Bearer ${adminToken}`, 
    },
  });

  const fetchTasks = async () => {
    try {
      const response = await axiosInstance.get('/');
      setTasks(response.data);
    } catch (error) {
      setError('Failed to fetch tasks');
      console.error('Error fetching tasks:', error);
    }
  };

  const deleteTask = async (taskId) => {
    try {
      await axiosInstance.delete(`/${taskId}`);
      fetchTasks(); // Refresh the list after deleting
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  const editTask = async (taskId) => {
    if (!newStatus) {
      alert('Please select a status before saving.');
      return;
    }

    try {
      await axiosInstance.put(`/${taskId}`, { status: newStatus });
      fetchTasks(); // Refresh tasks after update
      setEditingTaskId(null); // Exit edit mode
      setNewStatus(''); // Clear status
    } catch (error) {
      console.error('Error editing task:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  return (
    <div className="task-operations">
      <h3>Task Operations</h3>
      {error && <p className="error">{error}</p>}
      <table>
        <thead>
          <tr>
            <th>Task ID</th>
            <th>Task Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>User</th>
            <th>Actions</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {tasks.length > 0 ? (
            tasks.map((task) => (
              <tr key={task.id}>
                <td>{task.id}</td>
                <td>{task.title}</td>
                <td>{task.description}</td>
                <td>
                  {editingTaskId === task.id ? (
                    <select
                      value={newStatus}
                      onChange={(e) => setNewStatus(e.target.value)}
                    >
                      <option value="">Select Status</option>
                      <option value="In Progress">In Progress</option>
                      <option value="Completed">Completed</option>
                      <option value="Pending">Pending</option>
                    </select>
                  ) : (
                    task.status
                  )}
                </td>
                <td>{task.userName}</td>
                <td>
                  {editingTaskId === task.id ? (
                    <button onClick={() => editTask(task.id)}>Save</button>
                  ) : (
                    <button onClick={() => setEditingTaskId(task.id)}>Edit</button>
                  )}
                </td>
                <td>
                  <button onClick={() => deleteTask(task.id)}>Delete</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No tasks available</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default TaskOperations;
