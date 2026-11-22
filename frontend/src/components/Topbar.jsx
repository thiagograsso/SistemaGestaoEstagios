import './Topbar.css'

function Topbar() {
  return (
    <header className="topbar">
      <strong>UNIVILLE — Campus Central</strong>

      <div className="usuario">
        <div className="usuario-texto">
          <span className="usuario-nome">Ana Luiza</span>
          <small className="usuario-status">Acesso ativo</small>
        </div>
        <div className="avatar">AL</div>
      </div>
    </header>
  )
}

export default Topbar