import { useState } from "react";
import Sidebar from "../components/Sidebar";
import ChatBox from "../components/ChatBox";

export default function ChatPage() {
  const [selectedRoom, setSelectedRoom] = useState(null);

  return (
    <div style={{ display: "flex", height: "100vh" }}>
      <Sidebar onSelectRoom={setSelectedRoom} />
      <ChatBox room={selectedRoom} />
    </div>
  );
}