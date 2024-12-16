// PrivateRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';
import Cookies from 'js-cookie';


const PrivateRoute = ({ element }) => {
  // Assuming the user role is stored in localStorage (or you can use context, Redux, etc.)
  const userRole = Cookies.get('role'); // Modify this based on your app's auth state

  // If the role is not 'ROLE_ADMIN', show the error page (access denied or 404)
  if (userRole !== 'ROLE_ADMIN') {
    return <Navigate to="/admin/login" />;
    
  }


  // If the user has the 'ROLE_ADMIN', render the requested admin page
  return element;
};

export default PrivateRoute;
