import { useState } from "react";
import api from "../services/api";

export default function Login({ onLogin }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const res = await api.post("/api/auth/login", {
        email,
        password,
      });

      const token = res.data.token;
      const userId = res.data.userId;
      const username = res.data.username;

      localStorage.setItem("token", token);
      localStorage.setItem("userId", userId); // 👈 THÊM
      localStorage.setItem("username", username); // 👈 THÊM

      console.log("✅ LOGIN SUCCESS");

      onLogin(); // báo cho App biết là đã login
    } catch (err) {
      console.error("❌ LOGIN FAIL", err);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Login</h2>

      <input
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <br />

      <input
        placeholder="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br />

      <button onClick={handleLogin}>Login</button>
    </div>
  );
}
