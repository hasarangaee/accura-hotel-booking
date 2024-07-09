import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from '../contexts/AuthContext';
import RoleBasedRoute from './RoleBasedRoute';
import AccessDenied from '../components/AccessDenied';

const Login = lazy(() => import('../components/Login'));
const Dashboard = lazy(() => import('../components/Dashboard'));
const AdminPanel = lazy(() => import('../components/AdminPanel'));
const Bookings = lazy(() => import('../components/Bookings'));
const Home = lazy(() => import('../application/home'));

const RoutesComponent = () => {
    const routes = [
        { path: '/login', element: <Login /> },
        { path: '/dashboard', element: <RoleBasedRoute requiredRoles={['user', 'ROLE_ADMIN']}><Dashboard /></RoleBasedRoute> },
        { path: '/admin', element: <RoleBasedRoute requiredRoles={['ROLE_ADMIN']}><AdminPanel /></RoleBasedRoute> },
        { path: '/bookings', element: <RoleBasedRoute requiredRoles={['user']}><Bookings /></RoleBasedRoute> },
        { path: '/access-denied', element: <AccessDenied /> },
        { path: '/home', element: <Home /> },
        { path: '*', element: <RoleBasedRoute requiredRoles={['user', 'ROLE_ADMIN']}><Dashboard /></RoleBasedRoute> },
    ];

    return (
        <Suspense fallback={<div>Loading...</div>}>
            <Routes>
                {routes.map((route, index) => (
                    <Route key={index} path={route.path} element={route.element} />
                ))}
            </Routes>
        </Suspense>
    );
};

export default RoutesComponent;
