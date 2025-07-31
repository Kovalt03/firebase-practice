const BASE_URL = "https://firebase-practice-kcf3.onrender.com";

export async function getExampleData() {
  const res = await fetch(`${BASE_URL}/api/hello`);
  const data = await res.json();
  return data;
}
