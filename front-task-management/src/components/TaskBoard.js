import React, { useState, useEffect } from 'react';
import TaskColumn from './TaskColumn';
import axios from 'axios';
import Cookies from 'js-cookie';


const TaskBoard = () => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState('');

  // Token'ı çerezlerden alma
  const token = Cookies.get('token');

  // Verileri fetch etme
  const fetchTasks = async () => {
    try {
      const response = await fetch('/tasks', {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setTasks(data); // Veriyi state'e kaydet
    } catch (error) {
      setError('Failed to fetch tasks');
      console.error('Failed to fetch tasks:', error);
    }
  };

  useEffect(() => {
    fetchTasks(); // Component mount olduğunda görevleri çek
  }, []);

  // taskStatus'e göre görevleri filtreleme
  const getTasksByStatus = (status) => tasks.filter((task) => task.taskStatus === status);

  return (
    <div className="task-board">
      {error && <p className="error">{error}</p>}
      <TaskColumn title="TODO" tasks={getTasksByStatus('TODO')} fetchTasks={fetchTasks} />
      <TaskColumn title="IN_PROGRESS" tasks={getTasksByStatus('IN_PROGRESS')} fetchTasks={fetchTasks} />
      <TaskColumn title="COMPLETED" tasks={getTasksByStatus('COMPLETED')} fetchTasks={fetchTasks} />
    </div>
  );
};

export default TaskBoard;
