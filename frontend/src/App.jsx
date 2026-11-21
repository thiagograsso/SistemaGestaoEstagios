import { useState } from 'react'
import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import MinhaSituacao from './pages/MinhaSituacao'
import PlanoInicial from './pages/PlanoInicial'
import Atividades from './pages/Atividades'
import Login from './pages/Login'

import './App.css'

function App() {
  const [logado, setLogado] = useState(false)
  const [paginaAtual, setPaginaAtual] = useState('situacao')

  function entrar() {
    setPaginaAtual('situacao')
    setLogado(true)
  }

  function sair() {
    setLogado(false)
  }

  if (!logado) {
    return <Login aoEntrar={entrar} />
  }

  return (
    <div className="layout">
      <Sidebar
        paginaAtual={paginaAtual}
        aoNavegar={setPaginaAtual}
        aoSair={sair}
      />
      <div className="principal">
        <Topbar />
        <main className="conteudo">
          {paginaAtual === 'situacao' && <MinhaSituacao />}
          {paginaAtual === 'plano' && <PlanoInicial />}
          {paginaAtual === 'atividades' && <Atividades />}
        </main>
      </div>
    </div>
  )
}

export default App