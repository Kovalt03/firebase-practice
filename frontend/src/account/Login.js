import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
const BASE_URL = "https://firebase-practice-kcf3.onrender.com";

const Login = () => {
    const navigate = useNavigate();
    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const username = formData.get('username');
        const password = formData.get('password');
        // encoding
        const encodedPassword = password;
        const res = await fetch(`${BASE_URL}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({
                username: username,
                password: password
            }),
        });
        console.log(res.body);
        if (res.ok) {
            // Login('success');
            alert('Login successful!')
            navigate('/chat');
            return;
        }else{
            alert('Login failed. Please check your username and password.');
            window.location.href = '/';
            return;
        }
    };

    return (
        <div>
            <h1>Login to Start Chatting!</h1>
            <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" required />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password" required />
            </div>
            <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;