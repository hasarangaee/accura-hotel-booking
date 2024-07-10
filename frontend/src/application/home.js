import React, { useEffect, useState } from 'react';
import '../css/App.css';
import HotelCard from '../components/HotelCard';
import { getAllHotels } from '../services/App.service';

function Home() {
    const [hotels, setHotels] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchHotels = async () => {
            try {
                const response = await getAllHotels();
                setHotels(response.data);
            } catch (error) {
                console.error('Error fetching hotels:', error);
                setError('Failed to fetch hotels.');
            } finally {
                setLoading(false);
            }
        };

        fetchHotels();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className="App">
            <section id="hotels" className="hotels">
                <div className="container">
                    <div className="hotel-list">
                        {hotels.map((hotel) => (
                            <HotelCard hotel={hotel} />
                        ))}
                    </div>
                </div>
            </section>
        </div>
    );
}

export default Home;
