import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/auth/Auth.service';
import {useAuth} from '../contexts/AuthContext';

import '../css/Login.css';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { dispatch } = useAuth();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await AuthService.login(username, password, dispatch);
            navigate('/dashboard');
        } catch (error) {
            setError('Invalid username or password');
        }
    };

    return (
        <div className="login-container">
            <h2>Login (System Admin / Hotel Admin)</h2>
            <form onSubmit={handleLogin} className="form">
                <div className="form-group">
                    <label className="label">Username</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="input"
                    />
                </div>
                <div className="form-group">
                    <label className="label">Password</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="input"
                    />
                </div>
                {error && <p className="error">{error}</p>}
                <button type="submit" className="button">Login</button>
            </form>
            <h3>This login is for System Admin or Hotel Admin.</h3>
            <p><strong>Hotel Admin:</strong> Can add, update, delete, and manage rooms, and view bookings.</p>
            <br/>
            <p><strong>System Admin:</strong> Can view all hotels and booking details.</p>
            <p><strong>System Admin Username:</strong> admin</p>
            <p><strong>System Admin Password:</strong> admin</p>
        </div>
    );
};

export default Login;
