import { useState } from "react";
import api from "../services/api";

export default function FriendPage() {
  const [email, setEmail] = useState("");
  const [foundUser, setFoundUser] = useState(null);

  const myId = localStorage.getItem("userId");

  // 🔍 SEARCH
  const handleSearch = async () => {
    try {
      const res = await api.get(`/api/users/search?email=${email}`);
      setFoundUser(res.data);
    } catch (err) {
      alert("Không tìm thấy user");
    }
  };

  // 📤 GỬI LỜI MỜI
  const handleAddFriend = async () => {
    await api.post(`/api/friends/send?senderId=${myId}&receiverId=${foundUser.id}`);
    alert("Đã gửi lời mời");
  };

  return (
    <div style={{ padding: "20px" }}>
      <h3>Tìm bạn</h3>

      <input
        placeholder="Nhập email..."
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <button onClick={handleSearch}>Tìm</button>

      {foundUser && (
        <div>
          <p>{foundUser.username}</p>
          <button onClick={handleAddFriend}>Kết bạn</button>
        </div>
      )}
    </div>
  );
}