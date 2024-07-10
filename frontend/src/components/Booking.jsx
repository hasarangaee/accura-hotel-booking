import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getHotelDetails, getRoomDetails, createBooking } from '../services/App.service';
import '../css/Booking.css';

const Booking = () => {
    const { hotelId } = useParams();
    const [hotel, setHotel] = useState(null);
    const [rooms, setRooms] = useState([]);
    const [bookingData, setBookingData] = useState({
        checkIn: '',
        checkOut: '',
        roomId: '',
        name: '',
        phoneNumber: ''
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchDetails = async () => {
            try {
                const hotelResponse = await getHotelDetails(hotelId);
                setHotel(hotelResponse.data);
                const roomResponse = await getRoomDetails(hotelId);
                setRooms(roomResponse.data);
            } catch (error) {
                console.error('Error fetching details:', error);
                setError('Failed to fetch details.');
            } finally {
                setLoading(false);
            }
        };

        fetchDetails();
    }, [hotelId]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setBookingData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const formattedData = {
                ...bookingData,
                checkIn: `${bookingData.checkIn}T00:00:00`,
                checkOut: `${bookingData.checkOut}T00:00:00`,
                hotelId
            };
            await createBooking(formattedData);
            alert('Booking successful!');
        } catch (error) {
            console.error('Error creating booking:', error);
            alert('Failed to create booking.');
        }
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="booking-page">
            <div className="booking-container">
                <h2>Book Your Stay at {hotel.name}</h2>
                <div className="hotel-details">
                    <p><strong>Location:</strong> {hotel.location}</p>
                    <p><strong>Review:</strong> {hotel.review}</p>
                </div>
                <div className="room-details">
                    <h3>Available Rooms</h3>
                    <select name="roomId" onChange={handleInputChange} required>
                        <option value="">Select a room</option>
                        {rooms.map((room) => (
                            <option key={room.id} value={room.id}>
                                {room.number} - ${room.price} per night
                            </option>
                        ))}
                    </select>
                </div>
                <form onSubmit={handleSubmit} className="booking-form">
                    <div className="form-group">
                        <label htmlFor="checkIn">Check-in Date</label>
                        <input
                            type="date"
                            id="checkIn"
                            name="checkIn"
                            value={bookingData.checkIn}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="checkOut">Check-out Date</label>
                        <input
                            type="date"
                            id="checkOut"
                            name="checkOut"
                            value={bookingData.checkOut}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="guestName">Guest Name</label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={bookingData.name}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="guestPhone">Phone Number</label>
                        <input
                            type="tel"
                            id="phoneNumber"
                            name="phoneNumber"
                            value={bookingData.phoneNumber}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <button type="submit" className="btn">Book Now</button>
                </form>
            </div>
        </div>
    );
};

export default Booking;
