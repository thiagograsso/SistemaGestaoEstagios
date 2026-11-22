import { useEffect, useState } from "react"
import { api } from "../api"
import { formatarData } from "../utils"

function mapear(periodo) {
  return { id: periodo.idPeriodo, nome: periodo.nome, inicio: periodo.dataInicio, fim: periodo.dataFim, ativo: periodo.status === "Ativo" }
}

function Periodos({ periodos, setPeriodos, aoRegistrar }) {
  const [nome, setNome] = useState("")
  const [inicio, setInicio] = useState("")
  const [fim, setFim] = useState("")
  const [erro, setErro] = useState("")

  useEffect(() => {
    api.periodos().then((lista) => setPeriodos(lista.map(mapear))).catch((error) => setErro(error.message))
  }, [setPeriodos])

  async function criarPeriodo(e) {
    e.preventDefault()
    const nomeLimpo = nome.trim()
    if (!nomeLimpo) return setErro("Informe o nome do periodo.")
    if (periodos.some((p) => p.nome.toLowerCase() === nomeLimpo.toLowerCase())) return setErro("Ja existe um periodo com este nome.")
    if (fim < inicio) return setErro("A data de fim nao pode ser anterior a data de inicio.")
    try {
      const criado = await api.criarPeriodo({ nome: nomeLimpo, dataInicio: inicio, dataFim: fim, cargaHorariaMinima: 120, status: "Planejado" })
      setPeriodos([mapear(criado), ...periodos])
      aoRegistrar(`Periodo ${nomeLimpo} criado`)
      setNome(""); setInicio(""); setFim(""); setErro("")
    } catch (error) { setErro(error.message) }
  }

  async function alternar(periodo) {
    try {
      const atualizado = await api.atualizarPeriodo(periodo.id, { nome: periodo.nome, dataInicio: periodo.inicio, dataFim: periodo.fim, cargaHorariaMinima: 120, status: periodo.ativo ? "Encerrado" : "Ativo" })
      setPeriodos(periodos.map((p) => p.id === periodo.id ? mapear(atualizado) : p))
      aoRegistrar(`Periodo ${periodo.nome} ${periodo.ativo ? "encerrado" : "ativado"}`)
      setErro("")
    } catch (error) { setErro(error.message) }
  }

  return (
    <div>
      <h1 className="titulo">Periodos</h1>
      <p className="subtitulo">Cadastre e ative periodos academicos.</p>
      <form className="card caixa" onSubmit={criarPeriodo}>
        <div className="linha-form">
          <label className="campo campo-descricao" htmlFor="periodo-nome">Nome<input id="periodo-nome" type="text" value={nome} onChange={(e) => setNome(e.target.value)} required /></label>
          <label className="campo campo-data" htmlFor="periodo-inicio">Inicio<input id="periodo-inicio" type="date" value={inicio} onChange={(e) => setInicio(e.target.value)} required /></label>
          <label className="campo campo-data" htmlFor="periodo-fim">Fim<input id="periodo-fim" type="date" value={fim} onChange={(e) => setFim(e.target.value)} required /></label>
          <button type="submit" className="botao">Criar</button>
        </div>
        <div role="status">{erro && <p className="alerta-erro">{erro}</p>}</div>
      </form>
      <div className="card tabela-card"><div className="tabela-rolagem"><table className="tabela">
        <thead><tr><th>Periodo</th><th>Inicio</th><th>Fim</th><th>Situacao</th><th>Acoes</th></tr></thead>
        <tbody>{periodos.map((p) => (
          <tr key={p.id || p.nome}><td><strong>{p.nome}</strong></td><td>{formatarData(p.inicio)}</td><td>{formatarData(p.fim)}</td><td><span className={p.ativo ? "selo selo-verde" : "selo selo-cinza"}>{p.ativo ? "Ativo" : "Encerrado"}</span></td><td><button type="button" className="botao botao-pequeno" onClick={() => alternar(p)}>{p.ativo ? "Encerrar" : "Ativar"}</button></td></tr>
        ))}</tbody>
      </table></div></div>
    </div>
  )
}

export default Periodos
