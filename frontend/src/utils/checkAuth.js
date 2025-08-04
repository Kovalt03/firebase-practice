const BASE_URL = "https://firebase-practice-kcf3.onrender.com";

export const checkAuth = async () => {
  const res = await fetch(`${BASE_URL}/check-auth`, {
    method: 'GET',
    credentials: 'include'
  });
  const data = await res.json();
  if(data.role === 'admin') {
    return 102; // Admin role
  }
  if(data.role === 'user') {
    return 1; // User role
  }
  if(data.role === 'banned') {
    return 2; // Banned role
  }
  return 0; // Not authenticated
};