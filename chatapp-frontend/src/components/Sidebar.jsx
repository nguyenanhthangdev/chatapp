import { useEffect, useState } from "react";
import axios from "axios";

export default function Sidebar({ onSelectRoom }) {
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/chats");

        console.log("ROOMS API RESPONSE:", res.data);

        setRooms(res.data);
      } catch (err) {
        console.error("LOAD ROOMS ERROR:", err);
      }
    };

    fetchRooms();
  }, []);


  console.log("SIDEBAR RENDERED");

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
          {room.name || "Unnamed Room"}
        </div>
      ))}
    </div>
  );
}
