const BASE_URL = "https://firebase-practice-kcf3.onrender.com";

export const checkAuth = async () => {
  const res = await fetch(`${BASE_URL}/check-auth`, {
    method: 'POST',
    credentials: 'include',
    body: JSON.stringify({
      username: data.username
    }),
  });

  if (!res.ok) {
    alert('You are not authenticated. Please log in.');
    return 0;
  }

  const data = await res.json();

  console.log(data);
  if(data.role === 'admin') {
    return 102; // Admin role
  }
  if(data.role === 'user') {
    alert(`Welcome back, ${data.username}!`);
    return 1; // User role
  }
  if(data.role === 'banned') {
    return 2; // Banned role
  }
  alert('You are not authenticated. Please log in.');
  return 0; // Not authenticated
};