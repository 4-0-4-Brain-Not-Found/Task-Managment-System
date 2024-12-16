import React from 'react';
import CardList from './CardList'; 
import Cookies from 'js-cookie';
import axios from "axios";

const TaskColumn = ({ title, tasks, fetchTasks }) => {
  const addTask = async () => {
    const token = Cookies.get('token');
    if (!token) {
      console.error('No token found. Please log in again.');
      return;
    }

    try {
      const newTask = { 
        title: `New Task in ${title}`, 
        description: 'New description', 
        taskStatus: title 
      };

      await axios.post('/tasks', newTask, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      fetchTasks(); // Fetch updated tasks after adding a new one
    } catch (error) {
      console.error('Error adding task:', error);
      if (error.response) {
        console.error('Server Response:', error.response.data);
      } else if (error.message) {
        console.error('Error Message:', error.message);
      }
    }
  };

  return (
    <div className="task-column">
      <h2>{title}</h2>
      <CardList tasks={tasks} fetchTasks={fetchTasks} /> {/* Pass tasks and fetchTasks to CardList */}
      <button onClick={addTask}>Add Task</button>
    </div>
  );
};

export default TaskColumn;
