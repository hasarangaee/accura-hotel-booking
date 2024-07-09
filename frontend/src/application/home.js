import React from 'react';
import '../css/App.css';
import Navigation from '../components/Navigation';

function Home() {
    return (
        <div className="App">
            <main>
                <section className="hero">
                    <div className="container">
                        <h1>Welcome to Our Hotel</h1>
                        <p>Book your stay and enjoy luxury redefined at the best price.</p>
                        <a href="#booking" className="btn">Book Now</a>
                    </div>
                </section>
                <section id="booking" className="booking-form">
                    <div className="container">
                        <h2>Book Your Stay</h2>
                        <form>
                            <div className="form-group">
                                <label htmlFor="checkin">Check-in Date</label>
                                <input type="date" id="checkin" name="checkin" required />
                            </div>
                            <div className="form-group">
                                <label htmlFor="checkout">Check-out Date</label>
                                <input type="date" id="checkout" name="checkout" required />
                            </div>
                            <div className="form-group">
                                <label htmlFor="guests">Guests</label>
                                <input type="number" id="guests" name="guests" min="1" required />
                            </div>
                            <button type="submit" className="btn">Check Availability</button>
                        </form>
                    </div>
                </section>
                <section id="features" className="features">
                    <div className="container">
                        <h2>Features</h2>
                        <ul>
                            <li>Free Wi-Fi</li>
                            <li>24/7 Customer Support</li>
                            <li>Complimentary Breakfast</li>
                            <li>Swimming Pool</li>
                        </ul>
                    </div>
                </section>
                <section id="reviews" className="reviews">
                    <div className="container">
                        <h2>Customer Reviews</h2>
                        <div className="review">
                            <p>"Amazing experience! The staff were very friendly and the rooms were clean and comfortable."</p>
                            <p>- John Doe</p>
                        </div>
                        <div className="review">
                            <p>"Great location and excellent service. Highly recommend!"</p>
                            <p>- Jane Smith</p>
                        </div>
                    </div>
                </section>
            </main>
            <footer id="contact" className="footer">
                <div className="container">
                    <p>Contact Us: info@hotelbooking.com | +123 456 7890</p>
                </div>
            </footer>
        </div>
    );
}

export default Home;
