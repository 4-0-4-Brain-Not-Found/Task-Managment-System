import { useState } from 'react';

export const useTasks = () => {
  const [tasks, setTasks] = useState({
    todo: [],
    doing: [],
    done: []
  });

  const addTask = (column) => {
    const newTask = { id: Date.now(), title: 'New Task',description: 'Task description here...' };
    setTasks((prevTasks) => ({
      ...prevTasks,
      [column.toLowerCase()]: [...prevTasks[column.toLowerCase()], newTask]
    }));
  };

  const updateTask = (id, newTitle, newDescription) => {
    setTasks((prevTasks) => {
      const updatedTasks = { ...prevTasks };
      for (const column in updatedTasks) {
        updatedTasks[column] = updatedTasks[column].map((task) =>
          task.id === id ? { ...task, title: newTitle, description: newDescription} : task
        );
      }
      return updatedTasks;
    });
  };

  const deleteTask = (id) => {
    setTasks((prevTasks) => {
      const updatedTasks = { ...prevTasks };
      for (const column in updatedTasks) {
        updatedTasks[column] = updatedTasks[column].filter(
          (task) => task.id !== id
        );
      }
      return updatedTasks;
    });
  };

  return { tasks, addTask, updateTask, deleteTask };
};
