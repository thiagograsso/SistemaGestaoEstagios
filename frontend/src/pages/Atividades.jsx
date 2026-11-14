import { useState } from 'react'
import './Atividades.css'

const atividadesIniciais = [
  { id: 1, data: '2026-09-02', descricao: 'Levantamento de requisitos com o PO', horas: 4, situacao: 'Aprovado' },
  { id: 2, data: '2026-09-09', descricao: 'Wireframes das telas de login e dashboard', horas: 3, situacao: 'Aprovado' },
  { id: 3, data: '2026-09-16', descricao: 'Protótipo navegável do fluxo de atividades', horas: 5, situacao: 'Submetido' },
  {
    id: 4,
    data: '2026-09-23',
    descricao: 'Revisão de acessibilidade',
    horas: 2,
    situacao: 'Devolvido',
    justificativa: 'Detalhe quais critérios WCAG foram verificados.',
  },
]

const classeSelo = {
  Rascunho: 'selo-cinza',
  Submetido: 'selo-azul',
  Aprovado: 'selo-verde',
  Devolvido: 'selo-vermelho',
}

function formatarData(iso) {
  const [ano, mes, dia] = iso.split('-')
  return `${dia}/${mes}/${ano}`
}

function Atividades() {
  const [atividades, setAtividades] = useState(atividadesIniciais)

  const [data, setData] = useState('')
  const [descricao, setDescricao] = useState('')
  const [horas, setHoras] = useState('')

  const [editandoId, setEditandoId] = useState(null)

  function limparFormulario() {
    setData('')
    setDescricao('')
    setHoras('')
    setEditandoId(null)
  }

  function registrar(e) {
    e.preventDefault()

    if (editandoId === null) {
      const nova = {
        id: Date.now(),
        data,
        descricao,
        horas: Number(horas),
        situacao: 'Rascunho',
      }
      setAtividades([nova, ...atividades])
    } else {
      setAtividades(
        atividades.map((a) =>
          a.id === editandoId
            ? { ...a, data, descricao, horas: Number(horas) }
            : a
        )
      )
    }

    limparFormulario()
  }

  function editar(atividade) {
    setEditandoId(atividade.id)
    setData(atividade.data)
    setDescricao(atividade.descricao)
    setHoras(String(atividade.horas))
  }

  function excluir(id) {
    setAtividades(atividades.filter((a) => a.id !== id))
    if (id === editandoId) limparFormulario()
  }

  function submeter(id) {
    setAtividades(
      atividades.map((a) =>
        a.id === id
          ? { ...a, situacao: 'Submetido', justificativa: undefined }
          : a
      )
    )
    if (id === editandoId) limparFormulario()
  }

  return (
    <div>
      <h1 className="titulo">Atividades</h1>
      <p className="subtitulo">
        Registre, corrija e submeta suas atividades. Registros submetidos ficam bloqueados.
      </p>

      <form className="card caixa" onSubmit={registrar}>
        <h2 className="caixa-titulo">
          {editandoId === null ? 'Nova atividade' : 'Editar atividade'}
        </h2>

        <div className="linha-form">
          <label className="campo campo-data" htmlFor="data">
            Data
            <input
              id="data"
              type="date"
              value={data}
              onChange={(e) => setData(e.target.value)}
              required
            />
          </label>

          <label className="campo campo-descricao" htmlFor="descricao">
            Descrição
            <input
              id="descricao"
              type="text"
              value={descricao}
              onChange={(e) => setDescricao(e.target.value)}
              required
            />
          </label>

          <label className="campo campo-horas" htmlFor="horas">
            Horas
            <input
              id="horas"
              type="number"
              min="1"
              value={horas}
              onChange={(e) => setHoras(e.target.value)}
              required
            />
          </label>

          <button type="submit" className="botao">Registrar</button>
        </div>
      </form>

      <div className="card tabela-card">
        <div className="tabela-rolagem">
          <table className="tabela">
            <thead>
              <tr>
                <th scope="col">Data</th>
                <th scope="col">Descrição</th>
                <th scope="col" className="coluna-horas">Horas</th>
                <th scope="col">Situação</th>
                <th scope="col"><span className="so-leitor">Ações</span></th>
              </tr>
            </thead>
            <tbody>
              {atividades.map((a) => {
                const podeEditar = a.situacao === 'Rascunho' || a.situacao === 'Devolvido'
                const podeExcluir = a.situacao === 'Rascunho'

                return (
                  <tr key={a.id}>
                    <td>{formatarData(a.data)}</td>
                    <td>
                      <span>{a.descricao}</span>
                      {a.justificativa && (
                        <p className="justificativa">↩ {a.justificativa}</p>
                      )}
                    </td>
                    <td className="coluna-horas">{a.horas}h</td>
                    <td>
                      <span className={`selo ${classeSelo[a.situacao]}`}>
                        {a.situacao}
                      </span>
                    </td>
                    <td>
                      <div className="acoes">
                        {podeEditar ? (
                          <>
                            <button
                              type="button"
                              className="botao botao-pequeno"
                              aria-label={`Editar ${a.descricao}`}
                              onClick={() => editar(a)}
                            >
                              Editar
                            </button>
                            <button
                              type="button"
                              className="botao botao-pequeno"
                              aria-label={`Submeter ${a.descricao}`}
                              onClick={() => submeter(a.id)}
                            >
                              Submeter
                            </button>
                            {podeExcluir && (
                              <button
                                type="button"
                                className="botao botao-pequeno"
                                aria-label={`Excluir ${a.descricao}`}
                                onClick={() => excluir(a.id)}
                              >
                                Excluir
                              </button>
                            )}
                          </>
                        ) : (
                          <span className="bloqueado">🔒 bloqueado</span>
                        )}
                      </div>
                    </td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default Atividades