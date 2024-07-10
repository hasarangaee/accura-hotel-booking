import React, { useState, useEffect } from 'react';
import {createRoom, getMyHotels} from '../services/App.service';
import { useNavigate } from 'react-router-dom';
import '../css/RoomCreateForm.css';

const RoomCreateForm = () => {
    const [hotels, setHotels] = useState([]);
    const [hotelId, setHotelId] = useState('');
    const [type, setType] = useState('');
    const [number, setNumber] = useState('');
    const [price, setPrice] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();


    useEffect(() => {
        const fetchHotels = async () => {
            try {
                const response = await getMyHotels();
                console.log(response.data);
                setHotels(response.data);
            } catch (error) {
                console.error('Error fetching hotels:', error);
            }
        };

        fetchHotels();
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);

        try {
            const formData = {
                hotelId: parseInt(hotelId),
                type,
                number: parseInt(number),
                price: parseFloat(price)
            };

            const response = await createRoom(formData);

            setHotelId('');
            setType('');
            setNumber('');
            setPrice('');
            navigate('/hotel/me');
        } catch (error) {
            console.error('Error creating room:', error);
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'hotelId':
                setHotelId(value);
                break;
            case 'type':
                setType(value);
                break;
            case 'number':
                setNumber(value);
                break;
            case 'price':
                setPrice(value);
                break;
            default:
                break;
        }
    };

    return (
        <div className="room-create-form">
            <h2>Create Room</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Hotel:</label>
                    <select name="hotelId" value={hotelId} onChange={handleChange} required>
                        <option value="">Select Hotel</option>
                        {hotels.length > 0 ? (
                            hotels.map((hotel) => (
                                <option key={hotel.id} value={hotel.id}>
                                    {hotel.name}
                                </option>
                            ))
                        ) : (
                            <option disabled>Loading hotels...</option>
                        )}
                    </select>
                </div>
                <div className="form-group">
                    <label>Type:</label>
                    <input
                        type="text"
                        name="type"
                        value={type}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Room Number:</label>
                    <input
                        type="number"
                        name="number"
                        value={number}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Price:</label>
                    <input
                        type="number"
                        name="price"
                        value={price}
                        onChange={handleChange}
                        step="0.01"
                        required
                    />
                </div>
                <button type="submit" className="btn-submit" disabled={loading}>
                    {loading ? 'Creating...' : 'Create Room'}
                </button>
            </form>
        </div>
    );
};

export default RoomCreateForm;
