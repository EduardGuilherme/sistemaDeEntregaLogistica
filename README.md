# Sistema de Agendamento Logístico

## Descrição

Este projeto é um sistema de agendamento logístico, desenvolvido com **Next.js** no front-end e **Spring Boot** no back-end.  
O sistema permite que administradores criem, listem e excluam agendamentos de caminhões, com controle de ocupação por período, dia e faixa horária.

---

## Funcionalidades

- Listagem de agendamentos.
- Criação de agendamentos (apenas **ROLE_ADMIN**).
- Exclusão de agendamentos (apenas **ROLE_ADMIN**).
- Filtro de agendamentos por **período**.
- Consulta de **ocupação do dia**.
- Consulta de **ocupação por faixa horária**.
- Controle de acesso baseado em **JWT**.
- Dashboard interativo com tabela de agendamentos.

---

## Tecnologias Utilizadas

- **Front-end:** Next.js, React, TypeScript, Tailwind CSS
- **Back-end:** Spring Boot, Java, JWT
- **Banco de Dados:** PostgreSQL (ou H2 para testes)
- **Gerenciamento de cookies:** js-cookie
- **Controle de versão:** Git

---

<img width="1911" height="909" alt="Image" src="https://github.com/user-attachments/assets/2541b4f5-459d-48cd-ac5c-ec4e41f6e5b4" />
<img width="1918" height="914" alt="Image" src="https://github.com/user-attachments/assets/b53611d2-0aa8-46ce-a0b3-559adeb7cd7c" />
<img width="150" height="250" alt="Image" src="https://github.com/user-attachments/assets/002acbb9-006e-4543-92a5-9960b7aeb10a" />

## Instalação e Execução

### Backend (Spring Boot) Frontend (Next)

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/sistema-agendamento.git
cd sistema-agendamento/backend
./mvnw spring-boot:run
Frontend (Next.js)
Acesse a pasta frontend:
cd ../frontend
Instale as dependências:
npm install
Execute o projeto:
npm run dev
O frontend ficará disponível em http://localhost:3000.


