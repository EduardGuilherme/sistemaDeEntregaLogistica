"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { fetcher } from "@/utils/api";

export default function RegisterPage() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("ROLE_USER");

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await fetcher("/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password, role }),
      });
      alert("Cadastro realizado! Faça login.");
      router.push("/");
    } catch (err) {
      console.error(err);
      alert("Erro ao registrar usuário");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen">
      <form
        onSubmit={handleRegister}
        className="bg-white p-8 rounded shadow-md w-96"
      >
        <h2 className="text-2xl font-bold mb-4">Cadastro</h2>
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

        <select
          value={role}
          onChange={(e) => setRole(e.target.value)}
          className="w-full p-2 border rounded mb-4"
        >
          <option value="ROLE_USER">Usuário</option>
          <option value="ROLE_ADMIN">Administrador</option>
        </select>

        <button
          type="submit"
          className="w-full bg-green-600 text-white py-2 rounded"
        >
          Cadastrar
        </button>
        <p className="mt-4 text-center">
            Já tem conta?{" "}
            <a href="/" className="text-blue-600 underline">
                Faça login
            </a>
        </p>
      </form>
    </div>
  );
}
