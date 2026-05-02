import { useState } from "react";

export default function MessageInput({ onSend }) {
  const [text, setText] = useState("");
  console.log("🧠 MessageInput render");

  return (
    <div style={{ display: "flex", padding: "10px" }}>
      <input
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Nhập tin nhắn..."
        style={{ flex: 1, padding: "8px" }}
      />

      <button
        onClick={() => {
          if (!text.trim()) return;
          onSend(text);
          setText("");
        }}
      >
        Send
      </button>
    </div>
  );
}