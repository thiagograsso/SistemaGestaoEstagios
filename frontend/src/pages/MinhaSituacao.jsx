import './MinhaSituacao.css'

function MinhaSituacao() {
  return (
    <div>
      <h1 className="titulo">Olá, Ana</h1>
      <p className="subtitulo">Supervisor: Prof. Marcelo Momm - Período: 2026/2</p>

      <div className="cards">
        <div className="card">
          <span className="card-rotulo">Horas validadas</span>
          <strong className="card-valor verde">7h</strong>
        </div>

        <div className="card">
          <span className="card-rotulo">Em análise</span>
          <strong className="card-valor azul">5h</strong>
        </div>

        <div className="card">
          <span className="card-rotulo">Devolvidas</span>
          <strong className="card-valor vermelho">1</strong>
        </div>

        <div className="card">
          <span className="card-rotulo">Rascunhos</span>
          <strong className="card-valor">0</strong>
        </div>
      </div>

      <div className="card caixa">
        <div className="progresso-topo">
          <strong>Progresso em relação à carga prevista no plano</strong>
          <strong>7h / 120h</strong>
        </div>
        <div className="barra">
          <div className="barra-preenchida"></div>
        </div>
      </div>

      <div className="card caixa">
        <h2 className="caixa-titulo">Pendências</h2>

        <div className="pendencia">
          <span>Protótipo navegável do fluxo de atividades</span>
          <span className="selo selo-azul">Submetido</span>
        </div>

        <div className="pendencia pendencia-borda">
          <div>
            <span>Revisão de acessibilidade</span>
            <p className="justificativa">
              Justificativa: Detalhe quais critérios WCAG foram verificados.
            </p>
          </div>
          <span className="selo selo-vermelho">Devolvido</span>
        </div>
      </div>
    </div>
  )
}

export default MinhaSituacao