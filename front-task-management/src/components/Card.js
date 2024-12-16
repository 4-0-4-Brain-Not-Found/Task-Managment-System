import React, { useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
const Card = ({ task, fetchTasks }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [newTitle, setNewTitle] = useState(task.title);
  const [newDescription, setNewDescription] = useState(task.description);

    // Token'ı çerezlerden alma
  const token = Cookies.get('token');
  const updateTask = async () => {
    try {
      const updatedTask = { ...task, title: newTitle, description: newDescription };

      await axios.put(`/tasks/${task.id}`, updatedTask,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        }
      );
      fetchTasks();
      setIsEditing(false);
    } catch (error) {
      console.error('Error updating task:', error);
    }
  };

  const deleteTask = async () => {
    try {
      await axios.delete(`/tasks/${task.id}`,{
      headers: {
        'Authorization': `Bearer ${token}`,
      },});
      fetchTasks();
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  return (
    <div className="card">
      {isEditing ? (
        <div>
          <input value={newTitle} onChange={(e) => setNewTitle(e.target.value)} />
          <textarea value={newDescription} onChange={(e) => setNewDescription(e.target.value)} />
          <button onClick={updateTask}>Save</button>
        </div>
      ) : (
        <div>
          <h3>{task.title}</h3>
          <p>{task.description}</p>
          <button onClick={() => setIsEditing(true)}>Edit</button>
          <button onClick={deleteTask}>Delete</button>
        </div>
      )}
    </div>
  );
};

export default Card;
