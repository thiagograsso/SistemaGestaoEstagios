import './Login.css'

function Login({ aoEntrar }) {
  function entrarComoEstudante(e) {
    e.preventDefault()
    aoEntrar()
  }

  return (
    <div className="login">
      <section className="login-lado-azul">
        <div className="logo-area">
          <div className="logo">U</div>
          <div>
            <strong className="logo-nome">UniStage</strong>
            <small className="logo-sub">UNIVILLE PORTAL</small>
          </div>
        </div>

        <div>
          <span className="login-etiqueta">PORTAL ACADÊMICO</span>
          <h1 className="login-titulo">Sua jornada acadêmica em um só lugar.</h1>
          <p className="login-descricao">
            Acompanhe planos, atividades, horas e validações com clareza.
          </p>
        </div>

        <small className="login-rodape">UNIVILLE · Fábrica de Software · P02</small>
      </section>

      <section className="login-lado-form">
        <form className="login-form" onSubmit={entrarComoEstudante}>
          <div className="login-icone" aria-hidden="true">🎓</div>

          <h2 className="login-form-titulo">Acessar o portal</h2>
          <p className="login-form-sub">
            Informe suas credenciais e entre com o seu perfil.
          </p>

          <label className="login-campo" htmlFor="email">
            E-mail institucional
            <input id="email" type="email" placeholder="seu.nome@email.com" />
          </label>

          <label className="login-campo" htmlFor="senha">
            Senha
            <input id="senha" type="password" placeholder="Digite sua senha" />
          </label>

          <button type="submit" className="login-botao">Entrar como estudante</button>
          <button type="button" className="login-botao">Entrar como supervisor</button>
          <button type="button" className="login-botao">Entrar como coordenador</button>
        </form>
      </section>
    </div>
  )
}

export default Login