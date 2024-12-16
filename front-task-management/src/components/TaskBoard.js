import React, { useState, useEffect } from 'react';
import TaskColumn from './TaskColumn';
import axios from 'axios';
import Cookies from 'js-cookie';
import '../styles/TaskBoard.css'; // CSS dosyasını ekledik

const TaskBoard = () => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState('');

  const token = Cookies.get('token');

  const fetchTasks = async () => {
    try {
      const response = await axios.get('/tasks', {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      setTasks(response.data);
    } catch (error) {
      setError('Failed to fetch tasks');
      console.error('Failed to fetch tasks:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const addTask = async (taskStatus) => {
    try {
      const newTask = {
        title: '',
        description: '',
        taskStatus,
      };

      await axios.post('/tasks', newTask, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      fetchTasks(); // Yeni task eklendikten sonra listeyi yenile
    } catch (error) {
      console.error('Error adding task:', error);
    }
  };

  const getTasksByStatus = (status) => tasks.filter((task) => task.taskStatus === status);

  return (
    <div className="task-board">
      {error && <p className="error">{error}</p>}
      <div className="task-columns">
        {['TODO', 'IN_PROGRESS', 'COMPLETED', 'ON_HOLD'].map((status) => (
          <TaskColumn
            key={status}
            title={status}
            tasks={getTasksByStatus(status)}
            fetchTasks={fetchTasks}
            addTask={() => addTask(status)} // Add Task fonksiyonunu gönder
          />
        ))}
      </div>
    </div>
  );
};

export default TaskBoard;
