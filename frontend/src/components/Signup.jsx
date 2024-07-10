import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../services/auth/Auth.service';
import '../css/Signup.css';

const Signup = () => {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSignup = async (e) => {
        e.preventDefault();
        try {
            await AuthService.register(name, username, password);
            navigate('/login');
        } catch (error) {
            setError('Failed to sign up. Please try again.');
        }
    };

    return (
        <div className="signup-container">
            <h2>Sign Up</h2>
            <form onSubmit={handleSignup} className="form">
                <div className="form-group">
                    <label className="label">Name</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        className="input"
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="label">Username</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="input"
                        required
                    />
                </div>
                <div className="form-group">
                    <label className="label">Password</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="input"
                        required
                    />
                </div>
                {error && <p className="error">{error}</p>}
                <button type="submit" className="button">Sign Up</button>
            </form>
            <h3>This registration for Hotel Admin.</h3>
            <p><strong>Hotel Admin:</strong> Can add, update, delete, and manage rooms, and view bookings.</p>
        </div>
    );
};

export default Signup;
