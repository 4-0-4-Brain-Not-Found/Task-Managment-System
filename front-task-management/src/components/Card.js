import React, { useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

const Card = ({ task, fetchTasks }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [newTitle, setNewTitle] = useState(task.title);
  const [newDescription, setNewDescription] = useState(task.description);
  const [newStatus, setNewStatus] = useState(task.taskStatus);

  const token = Cookies.get('token');

  const updateTask = async () => {
    try {
      const updatedTask = { ...task, title: newTitle, description: newDescription, taskStatus: newStatus };

      await axios.put(`/tasks/${task.id}`, updatedTask, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      fetchTasks(); // Fetch updated task list after update
      setIsEditing(false); // Exit edit mode
    } catch (error) {
      console.error('Error updating task:', error);
    }
  };

  const deleteTask = async () => {
    try {
      await axios.delete(`/tasks/${task.id}`, {
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });

      fetchTasks(); // Fetch updated task list after delete
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  return (
    <div className="card">
      {isEditing ? (
        <div>
          <input 
            value={newTitle} 
            onChange={(e) => setNewTitle(e.target.value)} 
            placeholder="Task Title" 
          />
          <textarea 
            value={newDescription} 
            onChange={(e) => setNewDescription(e.target.value)} 
            placeholder="Task Description"
          />
          <select value={newStatus} onChange={(e) => setNewStatus(e.target.value)}>
            <option value="TODO">TODO</option>
            <option value="IN_PROGRESS">IN_PROGRESS</option>
            <option value="COMPLETED">COMPLETED</option>
            <option value="ON_HOLD">ON_HOLD</option>
          </select>
          <button onClick={updateTask}>Save</button>
        </div>
      ) : (
        <div>
          <h3>{task.title}</h3>
          <p>{task.description}</p>
          <small>{task.createdAt}</small>
          <button onClick={() => setIsEditing(true)}>Edit</button>
          <button onClick={deleteTask}>Delete</button>
        </div>
      )}
    </div>
  );
};

export default Card;
