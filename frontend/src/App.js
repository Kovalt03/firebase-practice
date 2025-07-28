import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    axios.get(`${process.env.REACT_APP_API_URL}/message`)
      .then((res) => {
        setMessage(res.data);
      })
      .catch((err) => {
        console.error("API 호출 오류:", err);
      });
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h1>React ↔ Spring 통신 예제</h1>
      <p>{message}</p>
    </div>
  );
}

export default App;
