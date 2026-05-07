import { useEffect, useState, useRef } from "react";
import { connectSocket, sendMessage } from "../services/socket";
import MessageList from "./MessageList";
import MessageInput from "./MessageInput";
// import axios from "axios";

export default function ChatBox({ room }) {
  const [messages, setMessages] = useState([]);
  const connectedRef = useRef(false);

  // 🔥 CONNECT SOCKET (chỉ 1 lần)
  useEffect(() => {
    if (connectedRef.current) return;

    connectedRef.current = true;

    connectSocket((msg) => {
      setMessages((prev) => [...prev, msg]);
    });
  }, []);

  // 🔥 LOAD LỊCH SỬ
  useEffect(() => {
    if (!room) return;

    fetch(`http://localhost:8080/api/messages/${room.id}`)
      .then((res) => res.json())
      .then((data) => setMessages(data));
  }, [room]);

  const handleSend = (text) => {
    const currentUserId = localStorage.getItem("userId"); // 👈 thêm dòng này

    const msg = {
      chatRoomId: room.id,
      senderId: currentUserId, // 👈 sửa dòng này
      content: text,
      type: "TEXT",
      username: localStorage.getItem("username"), // nếu có
    };

    console.log("📤 MESSAGE:", msg);

    sendMessage(msg);
  };

  if (!room) return <div>Chọn 1 cuộc trò chuyện</div>;

  return (
    <div
      style={{
        flex: 1,
        display: "flex",
        flexDirection: "column",
        height: "100vh", // 👈 QUAN TRỌNG
      }}
    >
      {/* MESSAGE LIST */}
      <div style={{ flex: 1, overflowY: "auto" }}>
        <MessageList messages={messages} />
      </div>

      {/* INPUT */}
      <MessageInput onSend={handleSend} />
    </div>
  );
}
