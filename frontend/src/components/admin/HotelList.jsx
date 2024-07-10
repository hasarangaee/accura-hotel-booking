import React, { useState, useEffect } from 'react';
import { getAllHotels } from '../../services/App.service';

const HotelList = () => {
    const [hotels, setHotels] = useState([]);

    useEffect(() => {
        const fetchHotels = async () => {
            try {
                const fetchedHotels = await getAllHotels();
                setHotels(fetchedHotels.data);
            } catch (error) {
                console.error('Error fetching hotels:', error);
            }
        };

        fetchHotels();
    }, []);

    return (
        <div>
            <h2>Hotel List</h2>
            <ul>
                {hotels.map(hotel => (
                    <li key={hotel.id}>{hotel.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default HotelList;
