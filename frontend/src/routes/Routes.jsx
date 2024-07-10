import React, { Suspense, lazy } from 'react';
import { Routes, Route } from 'react-router-dom';
import RoleBasedRoute from './RoleBasedRoute';
import AccessDenied from '../components/AccessDenied';

// For PUBLIC
const Login = lazy(() => import('../components/Login'));
const Signup = lazy(() => import('../components/Signup'));
const Home = lazy(() => import('../application/home'));
const Booking = lazy(() => import('../components/Booking'));

// For ADMIN
const UserList = lazy(() => import('../components/admin/UserList'));
const HotelList = lazy(() => import('../components/admin/HotelList'));

// For HOTEL
const HotelCreate = lazy(() => import('../components/HotelCreate'));
const MyHotels = lazy(() => import('../components/MyHotels'));
const RoomCreate = lazy(() => import('../components/RoomCreate'));

const RoutesComponent = () => {
    const routes = [
        { path: '/login', element: <Login /> },
        { path: '/signup', element: <Signup /> },
        { path: '*', element: <Home /> },
        { path: '/public/home', element: <Home /> },
        { path: '/public/booking/:hotelId', element: <Booking /> },

        { path: '/admin/users', element: <RoleBasedRoute requiredRoles={['ROLE_ADMIN']}><UserList /></RoleBasedRoute> },
        { path: '/admin/hotels', element: <RoleBasedRoute requiredRoles={['ROLE_ADMIN']}><HotelList /></RoleBasedRoute> },

        { path: '/hotel/create', element: <RoleBasedRoute requiredRoles={['ROLE_HOTEL']}><HotelCreate /></RoleBasedRoute> },
        { path: '/hotel/me', element: <RoleBasedRoute requiredRoles={['ROLE_HOTEL']}><MyHotels /></RoleBasedRoute> },
        { path: '/hotel/room/create', element: <RoleBasedRoute requiredRoles={['ROLE_HOTEL']}><RoomCreate /></RoleBasedRoute> },

        { path: '/access-denied', element: <AccessDenied /> },
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
