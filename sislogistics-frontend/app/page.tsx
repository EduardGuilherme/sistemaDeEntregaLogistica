'use client';

import { useState } from "react";
import { useRouter } from "next/navigation";
import Cookies from "js-cookie";
import { fetcher } from "@/utils/api";

export default function LoginPage() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await fetcher("/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      Cookies.set("token", res.token, { expires: 1 });
      router.push("/dashboard");
    } catch (err) {
      console.error(err);
      alert("Falha no login");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <form
        onSubmit={handleLogin}
        className="bg-white p-8 rounded shadow-md w-96"
      >
        <h2 className="text-2xl font-bold mb-4">Login</h2>
        <input
          type="text"
          placeholder="Usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="w-full p-2 border rounded mb-4"
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full p-2 border rounded mb-4"
        />
        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-2 rounded"
        >
          Entrar
        </button>

        <p className="mt-4 text-center">
          Não tem conta?{" "}
          <button
            type="button"
            onClick={() => router.push("/register")}
            className="text-blue-600 underline"
          >
            Cadastre-se
          </button>
        </p>
      </form>
    </div>
  );
}
