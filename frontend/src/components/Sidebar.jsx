import './Sidebar.css'

const menus = {
  estudante: [
    { id: 'situacao', texto: '📊 Minha situação' },
    { id: 'plano', texto: '📄 Plano inicial' },
    { id: 'atividades', texto: '📋 Atividades' },
  ],
  supervisor: [
    { id: 'dashboard', texto: '📊 Visão geral' },
    { id: 'analise', texto: '📋 Análise de registros' },
  ],
  coordenador: [
    { id: 'dashboard', texto: '📊 Dashboard' },
    { id: 'periodos', texto: '📅 Períodos' },
    { id: 'pessoas', texto: '👥 Pessoas e vínculos' },
    { id: 'registros', texto: '📋 Todos os registros' },
    { id: 'rastreabilidade', texto: '🕘 Rastreabilidade' },
  ],
}

const nomePerfil = {
  estudante: 'ESTUDANTE',
  supervisor: 'SUPERVISOR/ORIENTADOR',
  coordenador: 'COORDENADOR',
}

function Sidebar({ perfil, paginaAtual, aoNavegar, aoSair }) {
  const itensMenu = menus[perfil]

  return (
    <aside className="sidebar">
      <div className="logo-area">
        <div className="logo">U</div>
        <div>
          <strong className="logo-nome">UniStage</strong>
          <small className="logo-sub">UNIVILLE PORTAL</small>
        </div>
      </div>

      <span className="perfil">{nomePerfil[perfil]}</span>

      <nav>
        {itensMenu.map((item) => (
          <a
            key={item.id}
            href="#"
            className={item.id === paginaAtual ? 'menu-item menu-item-ativo' : 'menu-item'}
            aria-current={item.id === paginaAtual ? 'page' : undefined}
            onClick={(e) => {
              e.preventDefault()
              aoNavegar(item.id)
            }}
          >
            {item.texto}
          </a>
        ))}
      </nav>

      <a
        href="#"
        className="sair"
        onClick={(e) => {
          e.preventDefault()
          aoSair()
        }}
      >
        Sair do sistema
      </a>
    </aside>
  )
}

export default Sidebar