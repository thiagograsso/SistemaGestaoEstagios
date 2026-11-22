import { useState } from 'react'
import { supervisorLogado } from '../dados'
import { formatarData, classeSelo, nomeDoUsuario } from '../utils'
import './AnaliseRegistros.css'

function AnaliseRegistros({ atividades, setAtividades, usuarios, vinculos, aoRegistrar }) {
  const [filtro, setFiltro] = useState('aguardando')

  const idsDosMeusEstudantes = vinculos
    .filter((v) => v.supervisorId === supervisorLogado.id)
    .map((v) => v.estudanteId)

  const registros = atividades.filter(
    (a) =>
      idsDosMeusEstudantes.includes(a.estudanteId) &&
      a.situacao !== 'Rascunho'
  )

  const registrosVisiveis =
    filtro === 'aguardando'
      ? registros.filter((a) => a.situacao === 'Submetido')
      : registros

  function mudarSituacao(atividade, novaSituacao) {
    setAtividades(
      atividades.map((a) =>
        a.id === atividade.id ? { ...a, situacao: novaSituacao } : a
      )
    )

    const verbo = novaSituacao === 'Aprovado' ? 'aprovada' : 'devolvida'
    const estudante = nomeDoUsuario(usuarios, atividade.estudanteId)
    aoRegistrar(`Atividade "${atividade.descricao}" de ${estudante} ${verbo}`)
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
                  {nomeDoUsuario(usuarios, a.estudanteId)} · {formatarData(a.data)} · {a.horas}h
                </span>
                <strong className="registro-titulo">{a.descricao}</strong>

                {a.situacao === 'Submetido' && (
                  <div className="registro-acoes">
                    <button
                      type="button"
                      className="botao botao-pequeno"
                      aria-label={`Aprovar ${a.descricao}`}
                      onClick={() => mudarSituacao(a, 'Aprovado')}
                    >
                      Aprovar
                    </button>
                    <button
                      type="button"
                      className="botao botao-pequeno"
                      aria-label={`Devolver ${a.descricao}`}
                      onClick={() => mudarSituacao(a, 'Devolvido')}
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