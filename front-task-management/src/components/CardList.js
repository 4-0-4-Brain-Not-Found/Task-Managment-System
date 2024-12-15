import React from 'react';
import Card from './Card';
import '../styles/styles.css';

const CardList = ({ tasks, updateTask, deleteTask }) => {
  return (
    <div className="card-list">
      {tasks.map((task) => (
        <Card
          key={task.id}
          task={task}
          updateTask={updateTask}
          deleteTask={deleteTask}
        />
      ))}
    </div>
  );
};

export default CardList;
