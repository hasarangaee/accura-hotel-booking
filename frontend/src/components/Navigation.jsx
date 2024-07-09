import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import AuthService from '../services/auth/Auth.service';
import { useAuth } from '../contexts/AuthContext';
import '../css/App.css';
import {jwtDecode} from "jwt-decode";

const Navigation = () => {
    const { state, dispatch, hasRole } = useAuth();
    const { isAuthenticated, user } = state;
    const [roles, setRoles] = useState([]);

    useEffect(() => {
        const storedUser = localStorage.getItem('x-info');
        if (storedUser) {
            try {
                const parsedUser = JSON.parse(storedUser);
                const decodedToken = jwtDecode(parsedUser.token);
                const roles = decodedToken.roles.map(role => role.authority);
                setRoles(roles);
            } catch (error) {
                console.error('Error decoding or retrieving roles:', error);
                setRoles([]);
            }
        }else{
            setRoles([]);
        }
    }, [isAuthenticated]);

    const handleLogout = () => {
        AuthService.logout(dispatch);
    };

    return (
        <header className="App-header">
            <nav className="navbar">
                <div className="container">
                    <a href="/" className="navbar-brand">Hotel Booking</a>
                    <ul className="navbar-nav">
                        {roles.includes('ROLE_USER') && (
                            <li className="nav-item"><Link to="/dashboard">Dashboard</Link></li>
                        )}
                        {roles.includes('ROLE_ADMIN') && (
                            <>
                                <li className="nav-item"><Link to="/admin">Admin Panel</Link></li>
                                <li className="nav-item"><Link to="/bookings">My Bookings</Link></li>
                            </>
                        )}
                        {isAuthenticated ? (
                            <li className="nav-item">
                                <button onClick={handleLogout}>Logout</button>
                            </li>
                        ) : (
                            <li className="nav-item"><Link to="/login">Login</Link></li>
                        )}
                    </ul>
                </div>
            </nav>
        </header>
    );
};

export default Navigation;