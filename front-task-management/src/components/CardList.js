import React from 'react';
import Card from './Card';

const CardList = ({ tasks, fetchTasks }) => {
  return (
    <div className="card-list">
      {tasks.map((task) => (
        <div key={task.id} className="task-card">
          <Card task={task} fetchTasks={fetchTasks} />
        </div>
      ))}
    </div>
  );
};

export default CardList;
