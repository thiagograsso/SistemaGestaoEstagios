import './Sidebar.css'

const itensMenu = [
  { id: 'situacao', texto: '📊 Minha situação' },
  { id: 'plano', texto: '📄 Plano inicial' },
  { id: 'atividades', texto: '📋 Atividades' },
]

function Sidebar({ paginaAtual, aoNavegar, aoSair }) {
  return (
    <aside className="sidebar">
      <div className="logo-area">
        <div className="logo">U</div>
        <div>
          <strong className="logo-nome">UniStage</strong>
          <small className="logo-sub">UNIVILLE PORTAL</small>
        </div>
      </div>

      <span className="perfil">ESTUDANTE</span>

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