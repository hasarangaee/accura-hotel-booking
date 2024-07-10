import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/HotelCard.css';

const HotelCard = ({ hotel }) => {
    const navigate = useNavigate();

    const handleBookNow = () => {
        navigate(`/public/booking/${hotel.id}`);
    };

    return (
        <div className="hotel-card">
            <h3>{hotel.name}</h3>
            <p>Total Rooms: {hotel.totalRooms}</p>
            <p>Available Rooms: {hotel.availableRooms}</p>
            <button onClick={handleBookNow} className="btn">Book Now</button>
        </div>
    );
};

export default HotelCard;
