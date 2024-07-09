import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';
import {jwtDecode} from 'jwt-decode';

const RoleBasedRoute = ({ children, requiredRoles }) => {
    const { state } = useAuth();
    const { isAuthenticated } = state;
    const [userRoles, setUserRoles] = useState([]);
    const [accessDenied, setAccessDenied] = useState(false);

    useEffect(() => {
        const storedUser = localStorage.getItem('x-info');
        if (storedUser) {
            try {
                const parsedUser = JSON.parse(storedUser);
                const decodedToken = jwtDecode(parsedUser.token);
                const roles = decodedToken.roles.map(role => role.authority);
                setUserRoles(roles);
            } catch (error) {
                console.error('Error decoding or retrieving roles:', error);
                setUserRoles([]);
            }
        }
    }, []);

    useEffect(() => {
        if (userRoles.length > 0) {
            const canAccess = requiredRoles.some(role => userRoles.includes(role));
            setAccessDenied(!canAccess);
        }
    }, [userRoles, requiredRoles]);

    if (!isAuthenticated) {
        return <Navigate to="/login" />;
    }

    if (accessDenied) {
        return <Navigate to="/access-denied" />;
    }

    return children;
};

export default RoleBasedRoute;
