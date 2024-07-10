import React, {createContext, useContext, useReducer, useEffect, useState} from 'react';
import {useNavigate, useLocation} from 'react-router-dom';
import {jwtDecode} from 'jwt-decode';

const AuthContext = createContext(undefined);

const authReducer = (state, action) => {
    switch (action.type) {
        case 'LOGIN':
            return {
                ...state,
                user: action.payload.user,
                isAuthenticated: true,
            };
        case 'LOGOUT':
            return {
                ...state,
                user: null,
                isAuthenticated: false,
            };
        default:
            return state;
    }
};

export const AuthProvider = ({children}) => {
    const navigate = useNavigate();
    const currentLocation = useLocation();
    const [state, dispatch] = useReducer(authReducer, {
        user: null,
        isAuthenticated: false,
    });
    const [loading, setLoading] = useState(true);

    const publicRoutes = ['/login', '/signup', '/public', '/public/*'];
    const isPublicRoute = publicRoutes.some(route => currentLocation.pathname.startsWith(route));

    useEffect(() => {
        const authenticate = () => {
            const storedUser = localStorage.getItem('x-info');
            if (storedUser) {
                const parsedUser = JSON.parse(storedUser);
                const decodedToken = jwtDecode(parsedUser.token);
                const roles = decodedToken.roles.map(role => role.authority);
                parsedUser.roles = roles || [];
                if (!state.isAuthenticated || state.user === null) {
                    dispatch({type: 'LOGIN', payload: {user: parsedUser}});
                }
            } else {
                if (!isPublicRoute) {
                    navigate('/login');
                }
            }
            setLoading(false);
        };

        authenticate();
    }, [navigate, state.isAuthenticated, state.user]);

    useEffect(() => {
        if (!loading && !state.isAuthenticated && !isPublicRoute) {
            navigate('/login');
        }
    }, [loading, state.isAuthenticated, navigate]);

    const hasRole = (role) => {
        return state.user?.roles?.includes(role);
    };

    return (
        <AuthContext.Provider value={{state, dispatch, hasRole}}>
            {!loading && children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};
