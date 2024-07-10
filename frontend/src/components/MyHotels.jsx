import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getMyHotels, myAllRooms } from '../services/App.service';
import MyRooms from './MyRooms';
import '../css/MyHotel.css';

const MyHotels = () => {
    const [hotel, setHotel] = useState(null);
    const [rooms, setRooms] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchMyHotel = async () => {
            try {
                const response = await getMyHotels();
                setHotel(response.data[0]);
                if (response.data[0]) {
                    const roomResponse = await myAllRooms(response.data[0].id);
                    setRooms(roomResponse.data);
                }
            } catch (error) {
                console.error('Error fetching my hotel:', error);
                setError('Failed to fetch hotel information.');
            } finally {
                setLoading(false);
            }
        };

        fetchMyHotel();
    }, []);

    const handleAddNewHotel = () => {
        navigate('/hotel/create');
    };

    const handleRoomUpdate = (updatedRoom) => {
        setRooms(rooms.map(room => (room.id === updatedRoom.id ? updatedRoom : room)));
    };

    const handleRoomDelete = (roomId) => {
        setRooms(rooms.filter(room => room.id !== roomId));
    };

    const handleAddNewRoom = () => {
        navigate('/hotel/room/create');
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <>
            <div className="my-hotel-view">
                <h2>My Hotel</h2>
                {hotel ? (
                    <div className="hotel-details">
                        <p><strong>Name:</strong> {hotel.name}</p>
                        <p><strong>Location:</strong> {hotel.location}</p>
                        <p><strong>Review:</strong> {hotel.review}</p>
                    </div>
                ) : (
                    <p>You have not added any hotel yet.</p>
                )}
                <button
                    className="btn-add-new"
                    onClick={handleAddNewHotel}
                    disabled={!!hotel}
                >
                    Add New Hotel
                </button>
            </div>
            <br />
            <div className="my-hotel-view">
                <h3>Rooms</h3>
                <button onClick={handleAddNewRoom}>Add New Room</button>
                {rooms.map(room => (
                    <MyRooms
                        key={room.id || Math.random()} // Temporary key for new rooms
                        room={room}
                        onRoomUpdate={handleRoomUpdate}
                        onRoomDelete={handleRoomDelete}
                    />
                ))}
            </div>
        </>
    );
};

export default MyHotels;
