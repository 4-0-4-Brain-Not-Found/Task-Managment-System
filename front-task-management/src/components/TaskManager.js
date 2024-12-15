import React, { useState } from 'react';
import TaskColumn from './TaskColumn';
import { useTasks } from '../hooks/useTasks';
import '../styles/styles.css';

const TaskManager = () => {
  const { tasks, addTask, updateTask, deleteTask } = useTasks();

  return (
    <div className="task-manager">
      <TaskColumn
        title="Todo"
        tasks={tasks.todo}
        addTask={addTask}
        updateTask={updateTask}
        deleteTask={deleteTask}
      />
      <TaskColumn
        title="Doing"
        tasks={tasks.doing}
        addTask={addTask}
        updateTask={updateTask}
        deleteTask={deleteTask}
      />
      <TaskColumn
        title="Done"
        tasks={tasks.done}
        addTask={addTask}
        updateTask={updateTask}
        deleteTask={deleteTask}
      />
    </div>
  );
};

export default TaskManager;

