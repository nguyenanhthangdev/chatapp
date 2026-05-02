import MessageItem from "./MessageItem";

export default function MessageList({ messages }) {
  return (
    <div style={{ flex: 1, padding: "10px", overflowY: "auto" }}>
      {messages.map((msg, index) => (
        <MessageItem key={index} message={msg} />
      ))}
    </div>
  );
}