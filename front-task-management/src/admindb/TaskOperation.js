import React from 'react';
import '../admin_styles/admin_page.css'; // Task Operations stilini import et

const TaskOperations = () => {
  return (
    <div className="task-operations">
      <h3>Task Operations</h3>
      <table>
        <thead>
          <tr>
            <th>Task ID</th>
            <th>Task Name</th>
            <th>Status</th>
            <th>Actions</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {}
          <tr>
            <td>1</td>
            <td>Update User Roles</td>
            <td>In Progress</td>
            <td><button>Edit</button></td>
            <td><button>Delete</button></td>
          </tr>
          <tr>
            <td>2</td>
            <td>Fix Bugs</td>
            <td>Completed</td>
            <td><button>Edit</button></td>
            <td><button>Delete</button></td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default TaskOperations;
