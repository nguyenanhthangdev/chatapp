export default function MessageList({ messages }) {
  const currentUserId = localStorage.getItem("userId");

  return (
    <div>
      {messages.map((msg, index) => {
        const isMe = msg.senderId === currentUserId;

        return (
          <div
            key={index}
            style={{
              display: "flex",
              justifyContent: isMe ? "flex-end" : "flex-start",
              padding: "5px 10px"
            }}
          >
            <div
              style={{
                background: isMe ? "#DCF8C6" : "#eee",
                padding: "8px 12px",
                borderRadius: "12px",
                maxWidth: "60%"
              }}
            >
              {!isMe && <b>{msg.username}</b>}
              <div>{msg.content}</div>
            </div>
          </div>
        );
      })}
    </div>
  );
}