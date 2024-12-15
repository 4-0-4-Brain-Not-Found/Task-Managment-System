import React from 'react';
import CardList from './CardList';
import '../styles/styles.css';

const TaskColumn = ({ title, tasks, addTask, updateTask, deleteTask }) => {
  return (
    <div className="task-column">
      <h2>{title}</h2>
      <CardList
        tasks={tasks}
        updateTask={updateTask}
        deleteTask={deleteTask}
      />
      <button onClick={() => addTask(title)}>Add Task</button>
    </div>
  );
};

export default TaskColumn;
