import { useState } from 'react'
import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import MinhaSituacao from './pages/MinhaSituacao'
import PlanoInicial from './pages/PlanoInicial'
import './App.css'

function App() {
  const [paginaAtual, setPaginaAtual] = useState('situacao')

  return (
    <div className="layout">
      <Sidebar paginaAtual={paginaAtual} aoNavegar={setPaginaAtual} />
      <div className="principal">
        <Topbar />
        <main className="conteudo">
          {paginaAtual === 'situacao' && <MinhaSituacao />}
          {paginaAtual === 'plano' && <PlanoInicial />}
        </main>
      </div>
    </div>
  )
}

export default App