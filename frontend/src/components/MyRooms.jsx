// MyRooms.js
import React, { useState } from 'react';
import { createRoom } from '../services/App.service';
import '../css/MyRooms.css';

const MyRooms = ({ room, onRoomUpdate, onRoomDelete }) => {
    const [editing, setEditing] = useState(false);
    const [roomData, setRoomData] = useState(room);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setRoomData({ ...roomData, [name]: value });
    };

    const handleSave = async () => {
        try {
            if (roomData.id) {
                // await updateRoom(roomData.id, roomData);
            } else {
                const newRoom = await createRoom(roomData);
                onRoomUpdate(newRoom);
            }
            setEditing(false);
        } catch (error) {
            console.error('Error saving room:', error);
        }
    };

    const handleDelete = async () => {
        // try {
        //     await deleteRoom(roomData.id);
        //     onRoomDelete(roomData.id);
        // } catch (error) {
        //     console.error('Error deleting room:', error);
        // }
    };

    return (
        <div className="room">
            {editing ? (
                <div className="room-form">
                    <input
                        type="text"
                        name="type"
                        value={roomData.type}
                        onChange={handleInputChange}
                        placeholder="Type"
                    />
                    <input
                        type="text"
                        name="number"
                        value={roomData.number}
                        onChange={handleInputChange}
                        placeholder="Number"
                    />
                    <input
                        type="number"
                        name="price"
                        value={roomData.price}
                        onChange={handleInputChange}
                        placeholder="Price"
                    />
                    <button onClick={handleSave}>Save</button>
                    <button onClick={() => setEditing(false)}>Cancel</button>
                </div>
            ) : (
                <div className="room-details">
                    <p>Type: {roomData.type}</p>
                    <p>Number: {roomData.number}</p>
                    <p>Price: ${roomData.price}</p>
                    <button onClick={() => setEditing(true)}>Edit</button>
                    <button onClick={handleDelete}>Delete</button>
                </div>
            )}
        </div>
    );
};

export default MyRooms;
