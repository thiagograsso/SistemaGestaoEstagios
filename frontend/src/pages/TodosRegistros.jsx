import { useState } from 'react'
import { formatarData, classeSelo, nomeDoUsuario } from '../utils'

const filtros = ['Todos', 'Rascunho', 'Submetido', 'Aprovado', 'Devolvido']

function TodosRegistros({ atividades, usuarios }) {
  const [filtro, setFiltro] = useState('Todos')

  const registrosVisiveis =
    filtro === 'Todos'
      ? atividades
      : atividades.filter((a) => a.situacao === filtro)

  return (
    <div>
      <h1 className="titulo">Todos os registros</h1>
      <p className="subtitulo">
        Visualização de todos os registros (somente leitura).
      </p>

      <div className="filtros">
        {filtros.map((f) => (
          <button
            key={f}
            type="button"
            className={
              f === filtro
                ? 'botao botao-filtro filtro-ativo'
                : 'botao botao-filtro'
            }
            aria-pressed={f === filtro}
            onClick={() => setFiltro(f)}
          >
            {f}
          </button>
        ))}
      </div>

      {registrosVisiveis.length === 0 ? (
        <p className="vazio">Nenhum registro encontrado.</p>
      ) : (
        <div className="card tabela-card">
          <div className="tabela-rolagem">
            <table className="tabela">
              <thead>
                <tr>
                  <th scope="col">Estudante</th>
                  <th scope="col">Data</th>
                  <th scope="col">Descrição</th>
                  <th scope="col" className="coluna-horas">Horas</th>
                  <th scope="col">Situação</th>
                </tr>
              </thead>
              <tbody>
                {registrosVisiveis.map((a) => (
                  <tr key={a.id}>
                    <td>{nomeDoUsuario(usuarios, a.estudanteId)}</td>
                    <td>{formatarData(a.data)}</td>
                    <td>{a.descricao}</td>
                    <td className="coluna-horas">{a.horas}h</td>
                    <td>
                      <span className={`selo ${classeSelo[a.situacao]}`}>
                        {a.situacao}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  )
}

export default TodosRegistros