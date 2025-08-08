import React, { useState } from 'react';
import Login from './account/Login';
import Register from './account/Register';
import Chat from './main/Chat';
import { BrowserRouter, Route, Routes as Routers } from 'react-router-dom';
import { Navigate } from 'react-router-dom';
import PrivateRoute from './main/PrivateRoute';
import { checkAuth } from './utils/checkAuth';

function App() {

  return (
    <BrowserRouter>
      <Routers>
        <Route path="/" element={<Navigate to = {checkAuth() != 0 ? '/chat' : '/login'} />} />
        {/* Public routes */}
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        {/* Private routes */}
        <Route path="/chat" element={<PrivateRoute><Chat /></PrivateRoute>} />
        {/* Else routes*/}
        <Route path="*" element={<div>404 Not Found</div>} />
      </Routers>
    </BrowserRouter>
  );
}

export default App;
