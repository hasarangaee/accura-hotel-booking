import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import AuthService from '../services/auth/Auth.service';
import {useAuth} from '../contexts/AuthContext';
import {jwtDecode} from "jwt-decode";

import '../css/App.css';

const Navigation = () => {
    const {state, dispatch, hasRole} = useAuth();
    const {isAuthenticated, user} = state;
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
        } else {
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
                    <Link to="/public/home" className="navbar-brand">Hotel Booking</Link>
                    <ul className="navbar-nav">

                        <li className="nav-item-link"><Link to="/public/home">Home</Link></li>

                        {roles.includes('ROLE_HOTEL') && (
                            <>
                                <li className="nav-item-link"><Link to="/hotel/me">My Hotel</Link></li>
                            </>
                        )}
                        {roles.includes('ROLE_ADMIN') && (
                            <>
                                <li className="nav-item-link"><Link to="/admin/users">Hotel Admin List</Link></li>
                                <li className="nav-item-link"><Link to="/admin/hotels">Hotel List</Link></li>
                            </>
                        )}
                        {isAuthenticated ? (
                            <li className="nav-item">
                                <button onClick={handleLogout}>Logout</button>
                            </li>
                        ) : (
                            <>
                                <li className="nav-item"><Link to="/login">Login</Link></li>
                                <li className="nav-item"><Link to="/signup">SingUp</Link></li>
                            </>
                        )}
                    </ul>
                </div>
            </nav>
        </header>
    );
};

export default Navigation;
