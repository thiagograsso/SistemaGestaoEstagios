import { useState } from 'react'
import { supervisorLogado, estudantes } from '../dados'
import { formatarData, classeSelo } from '../utils'
import './AnaliseRegistros.css'

function AnaliseRegistros({ atividades, setAtividades }) {
  const [filtro, setFiltro] = useState('aguardando')

  const meusEstudantes = estudantes.filter(
    (e) => e.supervisorId === supervisorLogado.id
  )

  const registros = atividades.filter(
    (a) =>
      meusEstudantes.some((e) => e.id === a.estudanteId) &&
      a.situacao !== 'Rascunho'
  )

  const registrosVisiveis =
    filtro === 'aguardando'
      ? registros.filter((a) => a.situacao === 'Submetido')
      : registros

  function nomeDoEstudante(id) {
    return meusEstudantes.find((e) => e.id === id).nome
  }

  function mudarSituacao(id, novaSituacao) {
    setAtividades(
      atividades.map((a) =>
        a.id === id ? { ...a, situacao: novaSituacao } : a
      )
    )
  }

  return (
    <div>
      <h1 className="titulo">Análise de registros</h1>
      <p className="subtitulo">Registros dos estudantes vinculados a você.</p>

      <div className="filtros">
        <button
          type="button"
          className={
            filtro === 'aguardando'
              ? 'botao botao-filtro filtro-ativo'
              : 'botao botao-filtro'
          }
          aria-pressed={filtro === 'aguardando'}
          onClick={() => setFiltro('aguardando')}
        >
          Aguardando análise
        </button>

        <button
          type="button"
          className={
            filtro === 'todos'
              ? 'botao botao-filtro filtro-ativo'
              : 'botao botao-filtro'
          }
          aria-pressed={filtro === 'todos'}
          onClick={() => setFiltro('todos')}
        >
          Todos
        </button>
      </div>

      {registrosVisiveis.length === 0 ? (
        <p className="vazio">Nenhum registro encontrado.</p>
      ) : (
        <ul className="lista-registros">
          {registrosVisiveis.map((a) => (
            <li key={a.id} className="card registro">
              <div>
                <span className="registro-info">
                  {nomeDoEstudante(a.estudanteId)} · {formatarData(a.data)} · {a.horas}h
                </span>
                <strong className="registro-titulo">{a.descricao}</strong>

                {a.situacao === 'Submetido' && (
                  <div className="registro-acoes">
                    <button
                      type="button"
                      className="botao botao-pequeno"
                      aria-label={`Aprovar ${a.descricao}`}
                      onClick={() => mudarSituacao(a.id, 'Aprovado')}
                    >
                      Aprovar
                    </button>
                    <button
                      type="button"
                      className="botao botao-pequeno"
                      aria-label={`Devolver ${a.descricao}`}
                      onClick={() => mudarSituacao(a.id, 'Devolvido')}
                    >
                      Devolver
                    </button>
                  </div>
                )}
              </div>

              <span className={`selo ${classeSelo[a.situacao]}`}>
                {a.situacao}
              </span>
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}

export default AnaliseRegistros