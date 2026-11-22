import { useState } from 'react'
import { formatarData } from '../utils'

function Periodos({ periodos, setPeriodos, aoRegistrar }) {
  const [nome, setNome] = useState('')
  const [inicio, setInicio] = useState('')
  const [fim, setFim] = useState('')
  const [erro, setErro] = useState('')

  function criar(e) {
    e.preventDefault()

    const nomeLimpo = nome.trim()

    if (nomeLimpo === '') {
      setErro('Informe o nome do período.')
      return
    }
    if (periodos.some((p) => p.nome.toLowerCase() === nomeLimpo.toLowerCase())) {
      setErro('Já existe um período com este nome.')
      return
    }
    if (fim < inicio) {
      setErro('A data de fim não pode ser anterior à data de início.')
      return
    }

    setPeriodos([{ nome: nomeLimpo, inicio, fim, ativo: false }, ...periodos])
    aoRegistrar(`Período ${nomeLimpo} criado`)

    setNome('')
    setInicio('')
    setFim('')
    setErro('')
  }

  function alternar(periodo) {
    setPeriodos(
      periodos.map((p) =>
        p.nome === periodo.nome ? { ...p, ativo: !p.ativo } : p
      )
    )
    aoRegistrar(`Período ${periodo.nome} ${periodo.ativo ? 'encerrado' : 'ativado'}`)
  }

  return (
    <div>
      <h1 className="titulo">Períodos</h1>
      <p className="subtitulo">Cadastre e ative períodos/edições acadêmicas.</p>

      <form className="card caixa" onSubmit={criar}>
        <div className="linha-form">
          <label className="campo campo-descricao" htmlFor="periodo-nome">
            Nome
            <input
              id="periodo-nome"
              type="text"
              placeholder="2027/1"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />
          </label>

          <label className="campo campo-data" htmlFor="periodo-inicio">
            Início
            <input
              id="periodo-inicio"
              type="date"
              value={inicio}
              onChange={(e) => setInicio(e.target.value)}
              required
            />
          </label>

          <label className="campo campo-data" htmlFor="periodo-fim">
            Fim
            <input
              id="periodo-fim"
              type="date"
              value={fim}
              onChange={(e) => setFim(e.target.value)}
              required
            />
          </label>

          <button type="submit" className="botao">Criar</button>
        </div>

        <div role="status">
          {erro && <p className="alerta-erro">{erro}</p>}
        </div>
      </form>

      <div className="card tabela-card">
        <div className="tabela-rolagem">
          <table className="tabela">
            <thead>
              <tr>
                <th scope="col">Período</th>
                <th scope="col">Início</th>
                <th scope="col">Fim</th>
                <th scope="col">Situação</th>
                <th scope="col"><span className="so-leitor">Ações</span></th>
              </tr>
            </thead>
            <tbody>
              {periodos.map((p) => (
                <tr key={p.nome}>
                  <td><strong>{p.nome}</strong></td>
                  <td>{formatarData(p.inicio)}</td>
                  <td>{formatarData(p.fim)}</td>
                  <td>
                    <span className={p.ativo ? 'selo selo-verde' : 'selo selo-cinza'}>
                      {p.ativo ? 'Ativo' : 'Encerrado'}
                    </span>
                  </td>
                  <td>
                    <div className="acoes">
                      <button
                        type="button"
                        className="botao botao-pequeno"
                        aria-label={`${p.ativo ? 'Encerrar' : 'Ativar'} período ${p.nome}`}
                        onClick={() => alternar(p)}
                      >
                        {p.ativo ? 'Encerrar' : 'Ativar'}
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default Periodos