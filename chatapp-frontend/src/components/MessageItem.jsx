export default function MessageItem({ message }) {
  return (
    <div
      style={{
        padding: "6px 10px",
        margin: "5px 0",
        background: "#f1f1f1",
        borderRadius: "10px",
        maxWidth: "60%",
      }}
    >
      <div style={{ fontSize: "12px", color: "#666" }}>
        {message.username}
      </div>
      <div>{message.content}</div>
    </div>
  );
}