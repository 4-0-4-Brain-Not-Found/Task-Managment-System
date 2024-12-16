import React from 'react';
import CardList from './CardList'; // Her bir görevi göstermek için
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
      const newTask = { title: `New Task in ${title}`, description: 'New description', taskStatus: title };

      await axios.post(
        '/tasks',
        newTask,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        }
      );

      await fetchTasks();
    } catch (error) {
      console.error('Error adding task:', error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="task-column">
      <h2>{title}</h2>
      <CardList tasks={tasks} />
      <button onClick={addTask}>Add Task</button>
    </div>
  );
};

export default TaskColumn;
