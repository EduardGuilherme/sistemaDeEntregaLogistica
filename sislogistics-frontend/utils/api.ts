export const API_URL = "http://localhost:8080";

export const fetcher = async (url: string, options?: RequestInit) => {
  const res = await fetch(`${API_URL}${url}`, options);
  if (!res.ok) throw new Error(await res.text());
  return res.json();
};
