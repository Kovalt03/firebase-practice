import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
const BASE_URL = "https://firebase-practice-kcf3.onrender.com";

const Register = () => {
    const navigate = useNavigate();

    const [isUnique, setIsUnique] = useState(false);
    
    const checkUnique = async (username) => {
        if(!username) {
            alert('Please enter a username.');
            return;
        }
        const res = await fetch(`${BASE_URL}/checkUnique?username=${username}`);
        if (res.ok) {
            const data = await res.json();
            if (data.unique) {
                setIsUnique(true);
                alert('Username is available.');
            } else {
                alert('Username is already taken. Please choose another one.');
            }
        }
    }

    const checkPasswordRule = (password) => {
        if(password.lenghth < 8) {
            alert('Password must be at least 8 characters long.');
            return false;
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const username = formData.get('username');
        const password = formData.get('password');
        const confirmPassword = formData.get('confirmPassword');
        if(!isUnique) {
            alert('Please check if the username is unique.');
            return;
        }
        if(!username || !password || !confirmPassword) {
            alert('Please fill in all fields.');
            return;
        }
        if(password !== confirmPassword) {
            alert('Passwords do not match.');
            return;
        }
        if(!checkPasswordRule(password)){
            alert('Password Rules error.');
            return;
        }
        const res = await fetch(`${BASE_URL}/register`, {
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
        if (res.ok) {
            alert('Registration successful!');
            navigate('/login');
        } else {
            alert('Registration failed. Please try again.');
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" required />
                <div>
                    <button
                        type="button"
                        onClick={() => {
                            const username = document.getElementById('username').value;
                            checkUnique(username);
                    }}
                    >
                    Check Username</button>
                </div>
            </div>

            <div>
                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password" required />
            </div>
            <div>
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required />
            </div>
            <button type="submit">Register</button>
        </form>
    );
}
export default Register;