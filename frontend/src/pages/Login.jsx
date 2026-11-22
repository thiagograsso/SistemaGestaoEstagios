import { useState } from "react"
import { api } from "../api"
import "./Login.css"

function Login({ aoEntrar }) {
  const [email, setEmail] = useState("")
  const [senha, setSenha] = useState("")
  const [erro, setErro] = useState("")
  const [carregando, setCarregando] = useState(false)

  async function entrar(e) {
    e.preventDefault()
    setErro("")
    setCarregando(true)
    try {
      const usuario = await api.login(email, senha)
      aoEntrar(usuario.perfil)
    } catch (error) {
      setErro(error.message)
    } finally {
      setCarregando(false)
    }
  }

  return (
    <div className="login">
      <section className="login-lado-azul">
        <div className="logo-area"><div className="logo">U</div><div><strong className="logo-nome">UniStage</strong><small className="logo-sub">UNIVILLE PORTAL</small></div></div>
        <div><span className="login-etiqueta">PORTAL ACADEMICO</span><h1 className="login-titulo">Sua jornada academica em um so lugar.</h1><p className="login-descricao">Acompanhe planos, atividades, horas e validacoes com clareza.</p></div>
        <small className="login-rodape">UNIVILLE - Fabrica de Software - P02</small>
      </section>
      <section className="login-lado-form">
        <form className="login-form" onSubmit={entrar}>
          <div className="login-icone" aria-hidden="true">Graduacao</div>
          <h2 className="login-form-titulo">Acessar o portal</h2>
          <p className="login-form-sub">Informe suas credenciais e entre com o seu perfil.</p>
          <label className="login-campo" htmlFor="email">E-mail institucional<input id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required /></label>
          <label className="login-campo" htmlFor="senha">Senha<input id="senha" type="password" value={senha} onChange={(e) => setSenha(e.target.value)} required /></label>
          {erro && <p className="alerta-erro">{erro}</p>}
          <button type="submit" className="login-botao" disabled={carregando}>{carregando ? "Entrando..." : "Entrar"}</button>
          <p className="login-form-sub">Demonstração: ana.luiza@univille.br / estudante123</p>
        </form>
      </section>
    </div>
  )
}

export default Login
