export const BASE_URL = "http://localhost:8080"

export const API_ROUTES = {
    SIGN_UP: `${BASE_URL}/interview/api/auth/signup`,
    SIGN_IN: `${BASE_URL}/interview/api/auth/signin`,

    CREATE_HOTEL: `${BASE_URL}/interview/api/hotel`,
    SEARCH_HOTELS: `${BASE_URL}/interview/api/hotel/search`,
    GET_ALL_HOTELS: `${BASE_URL}/interview/api/hotel/all`,
    GET_HOTEL: `${BASE_URL}/interview/api/hotel/`,
    GET_MY_HOTELS: `${BASE_URL}/interview/api/hotel/me`,

    CREATE_BOOKING: `${BASE_URL}/interview/api/booking`,
    SEARCH_BOOKINGS: `${BASE_URL}/interview/api/booking/search`,

    CREATE_ROOM: `${BASE_URL}/interview/api/room`,
    GET_MY_ROOMS: `${BASE_URL}/interview/api/room/all/`,
    GET_ROOMS: `${BASE_URL}/interview/api/room/all/`,

    GET_ALL_USERS: `${BASE_URL}/interview/api/user/all`
};
