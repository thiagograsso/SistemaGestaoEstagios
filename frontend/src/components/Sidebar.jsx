import './Sidebar.css'

function Sidebar() {
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
        <a href="#" className="menu-item menu-item-ativo">📊 Minha situação</a>
        <a href="#" className="menu-item">📄 Plano inicial</a>
        <a href="#" className="menu-item">📋 Atividades</a>
      </nav>

      <a href="#" className="sair">Sair do sistema</a>
    </aside>
  )
}

export default Sidebar