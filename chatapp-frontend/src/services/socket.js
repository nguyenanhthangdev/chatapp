import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

let stompClient = null;
let isConnected = false;

export const connectSocket = (onMessageReceived) => {
  // ❗ CHẶN gọi lại
  if (stompClient && isConnected) {
    console.log("⚠️ Đã connect rồi, bỏ qua");
    return;
  }

  stompClient = new Client({
    webSocketFactory: () => new SockJS("http://localhost:8080/ws"),
    reconnectDelay: 5000,

    onConnect: () => {
      console.log("✅ Connected");

      stompClient.subscribe("/topic/messages", (message) => {
        onMessageReceived(JSON.parse(message.body));
      });
    },

    onDisconnect: () => {
      isConnected = false;
    },
  });

  stompClient.activate();
};

// 👇 PHẢI CÓ export
export const sendMessage = (msg) => {
  if (stompClient && stompClient.connected) {
    stompClient.publish({
      destination: "/app/chat",
      body: JSON.stringify(msg),
    });
  } else {
    console.log("❌ Socket chưa connect");
  }
};

export const subscribeNotification = (userId, callback) => {
  if (!stompClient || !stompClient.connected) {
    console.log("❌ Socket chưa connected");
    return;
  }

  stompClient.subscribe(`/topic/notifications/${userId}`, (message) => {
    callback(JSON.parse(message.body));
  });
};
