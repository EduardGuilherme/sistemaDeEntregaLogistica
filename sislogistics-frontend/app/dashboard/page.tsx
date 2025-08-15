'use client';

import { useEffect, useState } from "react";
import Cookies from "js-cookie";
import { API_URL } from "@/utils/api";
import { useRouter } from "next/navigation";

interface Agendamento {
  id: number;
  data: string;
  faixaHorariaId: number;
  idPedido: number;
  fornecedor: string;
  emailFornecedor: string;
  tipoCaminhaoId: number;
  tipoPaletizacaoId: number;
  quantidadePallets: number;
  observacao: string;
}

function decodeJWT(token: string) {
  try {
    const payload = token.split(".")[1];
    return JSON.parse(atob(payload));
  } catch (e) {
    console.error("Erro ao decodificar JWT:", e);
    return null;
  }
}

export default function DashboardPage() {
  const router = useRouter();
  const [agendamentos, setAgendamentos] = useState<Agendamento[]>([]);
  const [isAdmin, setIsAdmin] = useState(false);

  
  const [data, setData] = useState("");
  const [faixaHorariaId, setFaixaHorariaId] = useState("1");
  const [idPedido, setIdPedido] = useState("");
  const [fornecedor, setFornecedor] = useState("");
  const [emailFornecedor, setEmailFornecedor] = useState("");
  const [tipoCaminhaoId, setTipoCaminhaoId] = useState("1");
  const [tipoPaletizacaoId, setTipoPaletizacaoId] = useState("1");
  const [quantidadePallets, setQuantidadePallets] = useState("");
  const [observacao, setObservacao] = useState("");

  const token = Cookies.get("token");

  const carregarAgendamentos = () => {
    if (token) {
      fetch(`${API_URL}/agendamentos`, {
        headers: { "Authorization": `Bearer ${token}` }
      })
        .then(res => {
          if (!res.ok) throw new Error("Erro ao carregar agendamentos");
          return res.json();
        })
        .then(data => setAgendamentos(data))
        .catch(err => console.error(err));
    }
  };

  useEffect(() => {
    if (token) {
      const payload = decodeJWT(token);
      console.log("Payload JWT:", payload);

      
      if (payload?.role && payload.role === "ROLE_ADMIN") {
        setIsAdmin(true);
      }
      if (Array.isArray(payload?.roles) && payload.roles.includes("ROLE_ADMIN")) {
        setIsAdmin(true);
      }
      if (Array.isArray(payload?.authorities) && payload.authorities.includes("ROLE_ADMIN")) {
        setIsAdmin(true);
      }
    }
    carregarAgendamentos();
  }, [token]);

  const handleCriar = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await fetch(`${API_URL}/agendamentos`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
          data,
          faixaHorariaId: Number(faixaHorariaId),
          idPedido: Number(idPedido),
          fornecedor,
          emailFornecedor,
          tipoCaminhaoId: Number(tipoCaminhaoId),
          tipoPaletizacaoId: Number(tipoPaletizacaoId),
          quantidadePallets: Number(quantidadePallets),
          observacao
        })
      });

      if (!res.ok) throw new Error("Erro ao criar agendamento");
      alert("Agendamento criado com sucesso!");
      setData(""); setFaixaHorariaId("1"); setIdPedido("");
      setFornecedor(""); setEmailFornecedor(""); setTipoCaminhaoId("1");
      setTipoPaletizacaoId("1"); setQuantidadePallets(""); setObservacao("");
      carregarAgendamentos();
    } catch (error) {
      console.error(error);
      alert("Erro ao criar agendamento");
    }
  };

  const handleDelete = async (id: number) => {
    if (!confirm("Tem certeza que deseja excluir?")) return;
    try {
      const res = await fetch(`${API_URL}/agendamentos/${id}`, {
        method: "DELETE",
        headers: { "Authorization": `Bearer ${token}` }
      });
      if (!res.ok) throw new Error("Erro ao excluir agendamento");
      carregarAgendamentos();
    } catch (err) {
      console.error(err);
      alert("Erro ao excluir");
    }
  };

  const handleLogout = () => {
    Cookies.remove("token");
    router.push("/");
  };

  return (
    <div className="p-8">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Agendamentos</h1>
        <button onClick={handleLogout} className="bg-red-600 text-white px-4 py-2 rounded">Logout</button>
      </div>

      {isAdmin && (
        <form onSubmit={handleCriar} className="bg-white p-6 rounded shadow-md mb-6 max-w-lg space-y-2">
          <h2 className="text-xl font-semibold mb-4">Novo Agendamento</h2>

          <input type="date" value={data} onChange={e => setData(e.target.value)} className="w-full p-2 border rounded" required />
          
          <select value={faixaHorariaId} onChange={e => setFaixaHorariaId(e.target.value)} className="w-full p-2 border rounded" required>
            <option value="1">08:00 - 10:00</option>
            <option value="2">10:00 - 12:00</option>
            <option value="3">13:00 - 15:00</option>
            <option value="4">15:00 - 17:00</option>
          </select>

          <input type="number" placeholder="Pedido ID" value={idPedido} onChange={e => setIdPedido(e.target.value)} className="w-full p-2 border rounded" required />
          <input type="text" placeholder="Fornecedor" value={fornecedor} onChange={e => setFornecedor(e.target.value)} className="w-full p-2 border rounded" required />
          <input type="email" placeholder="Email Fornecedor" value={emailFornecedor} onChange={e => setEmailFornecedor(e.target.value)} className="w-full p-2 border rounded" required />

          <select value={tipoCaminhaoId} onChange={e => setTipoCaminhaoId(e.target.value)} className="w-full p-2 border rounded" required>
            <option value="1">Caminhão Pequeno</option>
            <option value="2">Caminhão Médio</option>
            <option value="3">Caminhão Grande</option>
          </select>

          <select value={tipoPaletizacaoId} onChange={e => setTipoPaletizacaoId(e.target.value)} className="w-full p-2 border rounded" required>
            <option value="1">Paletizado</option>
            <option value="2">Não Paletizado</option>
          </select>

          <input type="number" placeholder="Quantidade Pallets" value={quantidadePallets} onChange={e => setQuantidadePallets(e.target.value)} className="w-full p-2 border rounded" required />
          <textarea placeholder="Observação" value={observacao} onChange={e => setObservacao(e.target.value)} className="w-full p-2 border rounded" required />

          <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded w-full">Criar Agendamento</button>
        </form>
      )}

      {agendamentos.length === 0 ? (
        <p>Nenhum agendamento encontrado</p>
      ) : (
        <table className="w-full bg-white shadow-md rounded">
          <thead>
            <tr>
              <th className="border p-2">ID</th>
              <th className="border p-2">Data</th>
              <th className="border p-2">Faixa Horária</th>
              <th className="border p-2">Pedido</th>
              <th className="border p-2">Fornecedor</th>
              <th className="border p-2">Email</th>
              <th className="border p-2">Caminhão</th>
              <th className="border p-2">Paletização</th>
              <th className="border p-2">Qtd Pallets</th>
              <th className="border p-2">Observação</th>
              {isAdmin && <th className="border p-2">Ações</th>}
            </tr>
          </thead>
          <tbody>
            {agendamentos.map(a => (
              <tr key={a.id}>
                <td className="border p-2">{a.id}</td>
                <td className="border p-2">{a.data}</td>
                <td className="border p-2">{a.faixaHorariaId}</td>
                <td className="border p-2">{a.idPedido}</td>
                <td className="border p-2">{a.fornecedor}</td>
                <td className="border p-2">{a.emailFornecedor}</td>
                <td className="border p-2">{a.tipoCaminhaoId}</td>
                <td className="border p-2">{a.tipoPaletizacaoId}</td>
                <td className="border p-2">{a.quantidadePallets}</td>
                <td className="border p-2">{a.observacao}</td>
                {isAdmin && (
                  <td className="border p-2">
                    <button 
                      onClick={() => handleDelete(a.id)}
                      className="bg-red-500 text-white px-2 py-1 rounded"
                    >
                      Excluir
                    </button>
                  </td>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
