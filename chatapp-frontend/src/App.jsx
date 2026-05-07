import { useState, useEffect } from "react";
import ChatPage from "./pages/ChatPage";
import Login from "./components/Login";
import FriendPage from "./pages/FriendPage";
import Notification from "./components/Notification";
import { connectSocket } from "./services/socket";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  useEffect(() => {
    connectSocket((msg) => {
      console.log("📩 SOCKET:", msg);
    });
  }, []);

  if (!isLoggedIn) {
    return <Login onLogin={() => setIsLoggedIn(true)} />;
  }

  return (
    <>
      <Notification />
      <FriendPage />
      <ChatPage />
    </>
  );
}

export default App;
