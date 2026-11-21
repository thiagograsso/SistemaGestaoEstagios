import { estudanteLogado, supervisorLogado } from '../dados'
import './Topbar.css'

function Topbar({ perfil }) {
  const usuario = perfil === 'supervisor' ? supervisorLogado : estudanteLogado

  return (
    <header className="topbar">
      <strong>UNIVILLE — Campus Central</strong>

      <div className="usuario">
        <div className="usuario-texto">
          <span className="usuario-nome">{usuario.nome}</span>
          <small className="usuario-status">Acesso ativo</small>
        </div>
        <div className="avatar">{usuario.iniciais}</div>
      </div>
    </header>
  )
}

export default Topbar