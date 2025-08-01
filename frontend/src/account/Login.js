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
        const encodedPassword = password;
        const res = await fetch(`${BASE_URL}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify({
                username: username,
                password: encodedPassword
            }),
        });
        console.log(res.body);
        if (res.ok) {
            // Login('success');
            alert('Login successful!');
            const res2 = await fetch(`${BASE_URL}/usr`, {
                method: 'GET',
                credentials: 'include',
            });
            if (res2.ok) {
                const data = await res2.json();
                <h1>{data}</h1>
            }else{
                alert('Failed to fetch user data.');
                window.location.href = '/';
                return;
            }
        }else{
            // Login('failure');
            alert('Login failed. Please check your username and password.');
            window.location.href = '/';
            return;
        }
        console.log('Username:', username, 'Password:', password);
    };

    return (
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
    );
}

export default Login;