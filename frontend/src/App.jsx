import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import MinhaSituacao from './pages/MinhaSituacao'
import './App.css'

function App() {
  return (
    <div className="layout">
      <Sidebar />
      <div className="principal">
        <Topbar />
        <main className="conteudo">
          <MinhaSituacao />
        </main>
      </div>
    </div>
  )
}

export default App