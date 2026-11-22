import { useState } from 'react'
import { supervisorLogado } from '../dados'
import { iniciais, nomeDoUsuario } from '../utils'
import './Dashboard.css'

function somarHoras(lista, situacao) {
  return lista
    .filter((a) => a.situacao === situacao)
    .reduce((total, a) => total + a.horas, 0)
}

function contar(lista, situacao) {
  return lista.filter((a) => a.situacao === situacao).length
}

function Dashboard({ perfil, atividades, periodos, usuarios, vinculos }) {
  const periodosAtivos = periodos.filter((p) => p.ativo)
  const [periodoNome, setPeriodoNome] = useState('')

  const periodo =
    periodosAtivos.find((p) => p.nome === periodoNome) ?? periodosAtivos[0]

  const subtitulo =
    perfil === 'coordenador'
      ? 'Situação geral dos estudantes.'
      : 'Seus estudantes vinculados.'

  if (!periodo) {
    return (
      <div>
        <h1 className="titulo">Dashboard</h1>
        <p className="subtitulo">{subtitulo}</p>
        <p className="vazio">Nenhum período ativo no momento.</p>
      </div>
    )
  }

  const vinculosDoPeriodo = vinculos.filter(
    (v) =>
      v.periodo === periodo.nome &&
      (perfil === 'coordenador' || v.supervisorId === supervisorLogado.id)
  )

  const atividadesDoPeriodo = atividades.filter(
    (a) =>
      vinculosDoPeriodo.some((v) => v.estudanteId === a.estudanteId) &&
      a.data >= periodo.inicio &&
      a.data <= periodo.fim
  )

  return (
    <div>
      <h1 className="titulo">Dashboard</h1>
      <p className="subtitulo">{subtitulo}</p>

      <label className="campo filtro-periodo" htmlFor="periodo-dashboard">
        Período
        <select
          id="periodo-dashboard"
          value={periodo.nome}
          onChange={(e) => setPeriodoNome(e.target.value)}
        >
          {periodosAtivos.map((p) => (
            <option key={p.nome} value={p.nome}>
              {p.nome} (ativo)
            </option>
          ))}
        </select>
      </label>

      <div className="cards">
        <div className="card">
          <span className="card-rotulo">Estudantes</span>
          <strong className="card-valor">{vinculosDoPeriodo.length}</strong>
        </div>

        <div className="card">
          <span className="card-rotulo">Horas validadas</span>
          <strong className="card-valor verde">
            {somarHoras(atividadesDoPeriodo, 'Aprovado')}h
          </strong>
        </div>

        <div className="card">
          <span className="card-rotulo">Horas em análise</span>
          <strong className="card-valor azul">
            {somarHoras(atividadesDoPeriodo, 'Submetido')}h
          </strong>
        </div>

        <div className="card">
          <span className="card-rotulo">Devoluções abertas</span>
          <strong className="card-valor vermelho">
            {contar(atividadesDoPeriodo, 'Devolvido')}
          </strong>
        </div>
      </div>

      <div className="card tabela-card">
        <div className="tabela-rolagem">
          <table className="tabela">
            <thead>
              <tr>
                <th scope="col">Estudante</th>
                <th scope="col">Supervisor</th>
                <th scope="col">Plano</th>
                <th scope="col">Validadas</th>
                <th scope="col">Em análise</th>
                <th scope="col">Devolvidas</th>
              </tr>
            </thead>
            <tbody>
              {vinculosDoPeriodo.map((v) => {
                const estudante = usuarios.find((u) => u.id === v.estudanteId)
                const dele = atividadesDoPeriodo.filter(
                  (a) => a.estudanteId === v.estudanteId
                )

                return (
                  <tr key={v.id}>
                    <td>
                      <div className="estudante-celula">
                        <span
                          className={`avatar-iniciais avatar-cor-${estudante.id % 3}`}
                          aria-hidden="true"
                        >
                          {iniciais(estudante.nome)}
                        </span>
                        {estudante.nome}
                      </div>
                    </td>
                    <td>{nomeDoUsuario(usuarios, v.supervisorId)}</td>
                    <td>
                      {estudante.cargaPrevista ? (
                        `${estudante.cargaPrevista}h previstas`
                      ) : (
                        <span className="selo selo-amarelo">Sem plano</span>
                      )}
                    </td>
                    <td>
                      <strong className="verde">{somarHoras(dele, 'Aprovado')}h</strong>
                    </td>
                    <td>{somarHoras(dele, 'Submetido')}h</td>
                    <td>{contar(dele, 'Devolvido')}</td>
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

export default Dashboard