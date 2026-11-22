import { useState } from 'react'
import { estudanteLogado } from '../dados'
import { formatarData, classeSelo } from '../utils'
import './Atividades.css'

function Atividades({ atividades, setAtividades, aoRegistrar }) {
  const minhasAtividades = atividades.filter(
    (a) => a.estudanteId === estudanteLogado.id
  )
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
        estudanteId: estudanteLogado.id,
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
    const atividade = atividades.find((a) => a.id === id)
    aoRegistrar(`Atividade "${atividade.descricao}" submetida`)

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
              {minhasAtividades.map((a) => {
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