import { useEffect, useState } from "react";
import { connectSocket, sendMessage } from "../services/socket";
import MessageList from "./MessageList";
import MessageInput from "./MessageInput";
import {  useRef } from "react";

export default function ChatBox({ room }) {
  const [messages, setMessages] = useState([]);
  const connectedRef = useRef(false);

  useEffect(() => {
    if (connectedRef.current) return;

    connectedRef.current = true;

    connectSocket((msg) => {
      setMessages((prev) => [...prev, msg]);
    });
  }, []);

  const handleSend = (text) => {
    const msg = {
      chatRoomId: room.id,
      senderId: "9f31bc95-cbb3-4815-99b7-3ef78f547cda",
      content: text,
      type: "TEXT",
      username: name,
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