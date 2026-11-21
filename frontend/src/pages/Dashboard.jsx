import { useState } from 'react'
import { supervisorLogado, estudantes, periodos } from '../dados'
import './Dashboard.css'

const periodosAtivos = periodos.filter((p) => p.ativo)

function somarHoras(lista, situacao) {
  return lista
    .filter((a) => a.situacao === situacao)
    .reduce((total, a) => total + a.horas, 0)
}

function contar(lista, situacao) {
  return lista.filter((a) => a.situacao === situacao).length
}

function Dashboard({ atividades }) {
  const [periodoNome, setPeriodoNome] = useState(periodosAtivos[0].nome)
  const periodo = periodosAtivos.find((p) => p.nome === periodoNome)

  const meusEstudantes = estudantes.filter(
    (e) => e.supervisorId === supervisorLogado.id
  )

  const atividadesDoPeriodo = atividades.filter(
    (a) =>
      meusEstudantes.some((e) => e.id === a.estudanteId) &&
      a.data >= periodo.inicio &&
      a.data <= periodo.fim
  )

  return (
    <div>
      <h1 className="titulo">Dashboard</h1>
      <p className="subtitulo">Seus estudantes vinculados.</p>

      <label className="campo filtro-periodo" htmlFor="periodo-dashboard">
        Período
        <select
          id="periodo-dashboard"
          value={periodoNome}
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
          <strong className="card-valor">{meusEstudantes.length}</strong>
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
              {meusEstudantes.map((e) => {
                const dele = atividadesDoPeriodo.filter(
                  (a) => a.estudanteId === e.id
                )

                return (
                  <tr key={e.id}>
                    <td>
                      <div className="estudante-celula">
                        <span className="avatar-iniciais" aria-hidden="true">
                          {e.iniciais}
                        </span>
                        {e.nome}
                      </div>
                    </td>
                    <td>{supervisorLogado.nome}</td>
                    <td>{e.cargaPrevista}h previstas</td>
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