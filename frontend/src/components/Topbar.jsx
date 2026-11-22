import { usuariosLogados } from '../dados'
import { iniciais } from '../utils'
import './Topbar.css'

function Topbar({ perfil }) {
  const usuario = usuariosLogados[perfil]

  return (
    <header className="topbar">
      <strong>UNIVILLE — Campus Central</strong>

      <div className="usuario">
        <div className="usuario-texto">
          <span className="usuario-nome">{usuario.nome}</span>
          <small className="usuario-status">Acesso ativo</small>
        </div>
        <div className="avatar">{iniciais(usuario.nome)}</div>
      </div>
    </header>
  )
}

export default Topbar