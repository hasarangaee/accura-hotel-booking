import axios from "../utils/AppAxios";
import {API_ROUTES} from '../constants/api/BackendApi';

export const createHotel = async (hotelData) => {
    try {
        const response = await axios.post(API_ROUTES.CREATE_HOTEL, hotelData);
        return response.data;
    } catch (error) {
        console.error('Error creating hotel:', error);
        throw error;
    }
};

export const searchHotels = async (location, review) => {
    try {
        const response = await axios.get(API_ROUTES.SEARCH_HOTELS, {
            params: {
                location,
                review
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error searching hotels:', error);
        throw error;
    }
};

export const getMyHotels = async () => {
    try {
        const response = await axios.get(API_ROUTES.GET_MY_HOTELS);
        return response.data;
    } catch (error) {
        console.error('Error getting all hotels:', error);
        throw error;
    }
};

export const getAllHotels = async () => {
    try {
        const response = await axios.get(API_ROUTES.GET_ALL_HOTELS);
        return response.data;
    } catch (error) {
        console.error('Error getting all hotels:', error);
        throw error;
    }
};

export const createBooking = async (bookingData) => {
    try {
        const response = await axios.post(API_ROUTES.CREATE_BOOKING, bookingData);
        return response.data;
    } catch (error) {
        console.error('Error creating booking:', error);
        throw error;
    }
};

export const searchBookings = async (location, checkIn, checkOut) => {
    try {
        const response = await axios.get(API_ROUTES.SEARCH_BOOKINGS, {
            params: {
                location,
                checkIn,
                checkOut
            }
        });
        return response.data;
    } catch (error) {
        console.error('Error searching bookings:', error);
        throw error;
    }
};

export const createRoom = async (roomData) => {
    try {
        const response = await axios.post(API_ROUTES.CREATE_ROOM, roomData);
        return response.data;
    } catch (error) {
        console.error('Error creating room:', error);
        throw error;
    }
};

export const myAllRooms = async (hotelId) => {
    try {
        const response = await axios.get(API_ROUTES.GET_MY_ROOMS + hotelId);
        return response.data;
    } catch (error) {
        console.error('Error creating room:', error);
        throw error;
    }
};

export const getAllUsers = async () => {
    try {
        const response = await axios.get(API_ROUTES.GET_ALL_USERS);
        return response.data;
    } catch (error) {
        console.error('Error getting all users:', error);
        throw error;
    }
};

export const getHotelDetails = async (hotelId) => {
    try {
        const response = await axios.get(`${API_ROUTES.GET_HOTEL}${hotelId}`);
        return response.data;
    } catch (error) {
        console.error('Error getting hotel details:', error);
        throw error;
    }
};

export const getRoomDetails = async (hotelId) => {
    try {
        const response = await axios.get(`${API_ROUTES.GET_ROOMS}${hotelId}`);
        return response.data;
    } catch (error) {
        console.error('Error getting room details:', error);
        throw error;
    }
};
