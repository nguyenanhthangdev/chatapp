import { useEffect, useState } from "react";
import api from "../services/api";
import { subscribeNotification } from "../services/socket";

export default function Notification() {
  const [requests, setRequests] = useState([]);

  const userId = localStorage.getItem("userId");

  useEffect(() => {
    loadRequests();
  }, []);

  useEffect(() => {
    subscribeNotification(userId, () => {
      loadRequests();
    });
  }, []);

  const loadRequests = async () => {
    const res = await api.get(`/api/friends/pending/${userId}`);
    setRequests(res.data);
  };

  const handleAccept = async (id) => {
    await api.post(`/api/friends/accept/${id}`);
    loadRequests();
  };

  const handleReject = async (id) => {
    await api.post(`/api/friends/reject/${id}`);
    loadRequests();
  };

  return (
    <div>
      <h4>🔔 ({requests.length})</h4>

      {requests.map((r) => (
        <div key={r.id} style={{ border: "1px solid #ccc", margin: "5px" }}>
          <p>Friend request</p>

          <button onClick={() => handleAccept(r.id)}>Accept</button>
          <button onClick={() => handleReject(r.id)}>Reject</button>
        </div>
      ))}
    </div>
  );
}
