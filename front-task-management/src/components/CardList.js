import React from 'react';

const CardList = ({ tasks }) => {
  return (
    <div className="card-list">
      {tasks.map((task) => (
        <div key={task.id} className="task-card">
          <h3>{task.title}</h3>
          <p>{task.description}</p>
          <small>{task.createdAt}</small>
        </div>
      ))}
    </div>
  );
};

export default CardList;
