import { useState } from 'react'
import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import MinhaSituacao from './pages/MinhaSituacao'
import PlanoInicial from './pages/PlanoInicial'
import Atividades from './pages/Atividades'
import Dashboard from './pages/Dashboard'
import AnaliseRegistros from './pages/AnaliseRegistros'
import Periodos from './pages/Periodos'
import PessoasVinculos from './pages/PessoasVinculos'
import TodosRegistros from './pages/TodosRegistros'
import Rastreabilidade from './pages/Rastreabilidade'
import Login from './pages/Login'
import {
  atividadesIniciais,
  periodosIniciais,
  usuariosIniciais,
  vinculosIniciais,
  auditoriaInicial,
  usuariosLogados,
} from './dados'
import { formatarDataHora } from './utils'

import './App.css'

function App() {
  const [logado, setLogado] = useState(false)
  const [perfil, setPerfil] = useState('estudante')
  const [paginaAtual, setPaginaAtual] = useState('situacao')

  const [atividades, setAtividades] = useState(atividadesIniciais)
  const [periodos, setPeriodos] = useState(periodosIniciais)
  const [usuarios, setUsuarios] = useState(usuariosIniciais)
  const [vinculos, setVinculos] = useState(vinculosIniciais)
  const [auditoria, setAuditoria] = useState(auditoriaInicial)

  function registrarAcao(acao) {
    const novoItem = {
      id: Date.now(),
      quando: formatarDataHora(new Date()),
      usuario: usuariosLogados[perfil].nome,
      acao,
    }
    setAuditoria((lista) => [novoItem, ...lista])
  }

  function entrar(perfilEscolhido) {
    setPerfil(perfilEscolhido)
    setPaginaAtual(perfilEscolhido === 'estudante' ? 'situacao' : 'dashboard')
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
            <Atividades
              atividades={atividades}
              setAtividades={setAtividades}
              aoRegistrar={registrarAcao}
            />
          )}
          {paginaAtual === 'dashboard' && (
            <Dashboard
              perfil={perfil}
              atividades={atividades}
              periodos={periodos}
              usuarios={usuarios}
              vinculos={vinculos}
            />
          )}
          {paginaAtual === 'analise' && (
            <AnaliseRegistros
              atividades={atividades}
              setAtividades={setAtividades}
              usuarios={usuarios}
              vinculos={vinculos}
              aoRegistrar={registrarAcao}
            />
          )}
          {paginaAtual === 'periodos' && (
            <Periodos
              periodos={periodos}
              setPeriodos={setPeriodos}
              aoRegistrar={registrarAcao}
            />
          )}
          {paginaAtual === 'pessoas' && (
            <PessoasVinculos
              usuarios={usuarios}
              setUsuarios={setUsuarios}
              vinculos={vinculos}
              setVinculos={setVinculos}
              periodos={periodos}
              aoRegistrar={registrarAcao}
            />
          )}
          {paginaAtual === 'registros' && (
            <TodosRegistros atividades={atividades} usuarios={usuarios} />
          )}
          {paginaAtual === 'rastreabilidade' && (
            <Rastreabilidade auditoria={auditoria} />
          )}
        </main>
      </div>
    </div>
  )
}

export default App