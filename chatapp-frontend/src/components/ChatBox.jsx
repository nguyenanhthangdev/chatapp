import { useEffect, useState, useRef } from "react";
import { connectSocket, sendMessage } from "../services/socket";
import MessageList from "./MessageList";
import MessageInput from "./MessageInput";
import axios from "axios";

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

    axios
      .get(`http://localhost:8080/api/messages/${room.id}`)
      .then((res) => setMessages(res.data))
      .catch((err) => console.error("LOAD HISTORY ERROR", err));
  }, [room]);

  const handleSend = (text) => {
    const msg = {
      chatRoomId: room.id,
      senderId: "9f31bc95-cbb3-4815-99b7-3ef78f547cda",
      content: text,
      type: "TEXT",
      username: "test1", // 🔥 fix tạm
    };

    console.log("📤 MESSAGE:", msg);

    sendMessage(msg);
  };

  if (!room) return <div>Chọn 1 cuộc trò chuyện</div>;

  return (
    <div style={{ flex: 1, display: "flex", flexDirection: "column" }}>
      <MessageList messages={messages} />
      <MessageInput onSend={handleSend} />
    </div>
  );
}