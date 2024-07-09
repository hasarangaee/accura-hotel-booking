import axios from 'axios';
import { API_ROUTES } from '../../constants/api/BackendApi';

const AuthService = {

    register: async (name, email, password, role = "hotel") => {
        const user = {
            name: name,
            username: name,
            password: email,
            role: [role]
        }
        try {
            const response = await axios.post(API_ROUTES.SIGN_UP + role, user);

            return response;
        } catch (error) {
        }
    },
    login: async (email, password, dispatch) => {
        const data = {
            username: email,
            password: password
        }
        try {
            const response = await axios.post(API_ROUTES.SIGN_IN, data);

            return AuthService.handleAuthenticationResponse(response, dispatch);
        }catch (error) {
        }
    },
    handleAuthenticationResponse: (response, dispatch) => {
        if (response.data.token) {
            dispatch({ type: 'LOGIN', payload: { user: response } });
            localStorage.setItem("x-jwtt", response.data.token);
            localStorage.setItem("x-info", JSON.stringify(response.data));
        }

        return response;
    },

    logout: async (dispatch) => {
        dispatch({ type: 'LOGOUT' });
        localStorage.removeItem("x-info");
        localStorage.removeItem("x-jwtt");
        return true;
    },
};

export default AuthService;