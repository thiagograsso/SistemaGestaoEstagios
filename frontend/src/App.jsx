import { useState } from 'react'
import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import MinhaSituacao from './pages/MinhaSituacao'
import PlanoInicial from './pages/PlanoInicial'
import Atividades from './pages/Atividades'
import Dashboard from './pages/Dashboard'
import AnaliseRegistros from './pages/AnaliseRegistros'
import Login from './pages/Login'
import { atividadesIniciais } from './dados'

import './App.css'

function App() {
  const [logado, setLogado] = useState(false)
  const [perfil, setPerfil] = useState('estudante')
  const [paginaAtual, setPaginaAtual] = useState('situacao')

  const [atividades, setAtividades] = useState(atividadesIniciais)

  function entrar(perfilEscolhido) {
    setPerfil(perfilEscolhido)
    setPaginaAtual(perfilEscolhido === 'supervisor' ? 'dashboard' : 'situacao')
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
        perfil={perfil}
        paginaAtual={paginaAtual}
        aoNavegar={setPaginaAtual}
        aoSair={sair}
      />
      <div className="principal">
        <Topbar perfil={perfil} />
        <main className="conteudo">
          {paginaAtual === 'situacao' && <MinhaSituacao />}
          {paginaAtual === 'plano' && <PlanoInicial />}
          {paginaAtual === 'atividades' && (
            <Atividades atividades={atividades} setAtividades={setAtividades} />
          )}
          {paginaAtual === 'dashboard' && <Dashboard atividades={atividades} />}
          {paginaAtual === 'analise' && (
            <AnaliseRegistros atividades={atividades} setAtividades={setAtividades} />
          )}
        </main>
      </div>
    </div>
  )
}

export default App