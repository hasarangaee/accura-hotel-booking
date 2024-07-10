import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createHotel } from '../services/App.service';
import '../css/HotelCreateForm.css';

const HotelCreateForm = () => {
    const [name, setName] = useState('');
    const [location, setLocation] = useState('');
    const [review, setReview] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);

        try {
            const formData = {
                name,
                location,
                review: parseFloat(review)
            };

            const response = await createHotel(formData);
            navigate('/hotel/me');
            setName('');
            setLocation('');
            setReview('');
        } catch (error) {
            console.error('Error creating hotel:', error);
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'name':
                setName(value);
                break;
            case 'location':
                setLocation(value);
                break;
            case 'review':
                setReview(value);
                break;
            default:
                break;
        }
    };

    return (
        <div className="hotel-create-form">
            <h2>Create Hotel</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name:</label>
                    <input
                        type="text"
                        name="name"
                        value={name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Location:</label>
                    <input
                        type="text"
                        name="location"
                        value={location}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Review:</label>
                    <input
                        type="number"
                        name="review"
                        value={review}
                        onChange={handleChange}
                        step="0.1"
                        min="0"
                        max="5"
                        required
                    />
                </div>
                <button type="submit" className="btn-submit" disabled={loading}>
                    {loading ? 'Creating...' : 'Create Hotel'}
                </button>
            </form>
        </div>
    );
};

export default HotelCreateForm;
