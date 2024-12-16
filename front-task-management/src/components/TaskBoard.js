import React, { useState, useEffect } from 'react';
import TaskColumn from './TaskColumn';
import axios from 'axios';
import Cookies from 'js-cookie';

const TaskBoard = () => {
  const [tasks, setTasks] = useState([]);
  const [error, setError] = useState('');
  const [showAddTask, setShowAddTask] = useState(true);

  const token = Cookies.get('token');

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
      setTasks(data);
    } catch (error) {
      setError('Failed to fetch tasks');
      console.error('Failed to fetch tasks:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const getTasksByStatus = (status) => tasks.filter((task) => task.taskStatus === status);

  return (
    <div className="task-board">
      {error && <p className="error">{error}</p>}
      {showAddTask && (
        <div className="add-task-button-container">
          <button onClick={() => setShowAddTask(false)}>Add Task</button>
        </div>
      )}
      <div className="task-columns">
        <TaskColumn title="TODO" tasks={getTasksByStatus('TODO')} fetchTasks={fetchTasks} />
        <TaskColumn title="IN_PROGRESS" tasks={getTasksByStatus('IN_PROGRESS')} fetchTasks={fetchTasks} />
        <TaskColumn title="COMPLETED" tasks={getTasksByStatus('COMPLETED')} fetchTasks={fetchTasks} />
        <TaskColumn title="ON_HOLD" tasks={getTasksByStatus('ON_HOLD')} fetchTasks={fetchTasks} />
      </div>
    </div>
  );
};

export default TaskBoard;
