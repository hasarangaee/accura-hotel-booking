import React from 'react';
import {BrowserRouter as Router} from 'react-router-dom';
import Navigation from './components/Navigation';
import {AuthProvider} from './contexts/AuthContext';
import RoutesComponent from './routes/Routes';

const App = () => {
    return (
        <Router>
            <AuthProvider>
                <Navigation/>
                <RoutesComponent/>
            </AuthProvider>
        </Router>
    );
};

export default App;
