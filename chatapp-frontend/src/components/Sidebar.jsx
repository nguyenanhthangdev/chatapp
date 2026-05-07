import { useEffect, useState } from "react";
import api from "../services/api";
import { subscribeNotification } from "../services/socket";

export default function Sidebar({ onSelectRoom }) {
  const [rooms, setRooms] = useState([]);

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const res = await api.get(`/api/chats/my/${userId}`);
        setRooms(res.data);
      } catch (err) {
        console.error("LOAD ROOMS ERROR:", err);
      }
    };

    if (userId) fetchRooms();
  }, [userId]);

  useEffect(() => {
    subscribeNotification(userId, (data) => {
      if (data.type === "NEW_CHAT_ROOM") {
        fetchRooms();
      }
    });
  }, []);

  return (
    <div
      style={{ width: "250px", borderRight: "1px solid #ddd", padding: "10px" }}
    >
      <h3>Chats</h3>

      {rooms.map((room) => (
        <div
          key={room.id}
          onClick={() => onSelectRoom(room)}
          style={{
            padding: "10px",
            margin: "5px 0",
            cursor: "pointer",
            border: "1px solid #eee",
            borderRadius: "5px",
          }}
        >
          {room.otherUsername}
        </div>
      ))}
    </div>
  );
}
