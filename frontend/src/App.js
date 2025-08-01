import React, { useState } from 'react';

function App() {
  const [name, setName] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async () => {
    const res = await fetch(`https://firebase-practice-kcf3.onrender.com/api/hello?name=${name}`);
    const text = await res.text();
    setMessage(text);
  };

  return (
    <div>
      <Login />
      <h1>Spring 연결 테스트</h1>
      <input
        type="text"
        placeholder="이름 입력"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <button onClick={handleSubmit}>서버에 요청</button>
      <p>{message}</p>
    </div>
  );
}

export default App;
