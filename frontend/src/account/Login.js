import React, { useState } from 'react';    
const BASE_URL = "https://firebase-practice-kcf3.onrender.com";

const Login = () => {
    // const username, setUsername = useState('');
    // const password, setPassword = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const username = formData.get('username');
        const password = formData.get('password');
        // encoding
        const encodedPassword = encodeURIComponent(password);
        const res = await fetch(`${BASE_URL}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: encodedPassword
            }),
        });
        const cookie = res.headers.get('Set-Cookie');
        if (cookie) {
            document.cookie = cookie;
            Login('success');
        }else{
            Login('failure');
            alert('Login failed. Please check your username and password.');
            return;
        }
        console.log('Username:', username, 'Password:', password);
    };

    return (
        <form>
        <div>
            <label htmlFor="username">Username:</label>
            <input type="text" id="username" name="username" required />
        </div>
        <div>
            <label htmlFor="password">Password:</label>
            <input type="password" id="password" name="password" required />
        </div>
        <button type="submit" onSubmit={handleSubmit}>Login</button>
        </form>
    );
}