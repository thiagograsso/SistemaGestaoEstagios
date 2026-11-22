const API_URL = "http://localhost:8080"

async function request(path, options = {}) {
  const response = await fetch(`${API_URL}${path}`, {
    headers: { "Content-Type": "application/json", ...(options.headers || {}) },
    ...options,
  })
  if (!response.ok) {
    const body = await response.json().catch(() => ({ erro: "Erro na comunicacao com o servidor" }))
    throw new Error(body.erro || "Erro na comunicacao com o servidor")
  }
  if (response.status === 204) return null
  return response.json()
}

export const api = {
  login: (email, senha) => request("/api/auth/login", { method: "POST", body: JSON.stringify({ email, senha }) }),
  periodos: () => request("/api/periodos"),
  criarPeriodo: (periodo) => request("/api/periodos", { method: "POST", body: JSON.stringify(periodo) }),
  atualizarPeriodo: (id, periodo) => request(`/api/periodos/${id}`, { method: "PUT", body: JSON.stringify(periodo) }),
}
