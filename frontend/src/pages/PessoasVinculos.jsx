import { useState } from 'react'
import { nomeDoUsuario } from '../utils'
import './PessoasVinculos.css'

const rotuloTipo = {
  estudante: 'Estudante',
  supervisor: 'Supervisor/Orientador',
  coordenador: 'Coordenador',
}

function PessoasVinculos({
  usuarios,
  setUsuarios,
  vinculos,
  setVinculos,
  periodos,
  aoRegistrar,
}) {
  const [nome, setNome] = useState('')
  const [email, setEmail] = useState('')
  const [tipo, setTipo] = useState('estudante')
  const [mensagemUsuario, setMensagemUsuario] = useState(null)

  const [estudanteId, setEstudanteId] = useState('')
  const [supervisorId, setSupervisorId] = useState('')
  const [periodoNome, setPeriodoNome] = useState('')
  const [mensagemVinculo, setMensagemVinculo] = useState(null)

  const estudantes = usuarios.filter((u) => u.tipo === 'estudante')
  const supervisores = usuarios.filter((u) => u.tipo === 'supervisor')

  function cadastrarUsuario(e) {
    e.preventDefault()

    const nomeLimpo = nome.trim()
    const emailLimpo = email.trim().toLowerCase()

    if (nomeLimpo === '') {
      setMensagemUsuario({ tipo: 'erro', texto: 'Informe o nome do usuário.' })
      return
    }
    if (usuarios.some((u) => u.email.toLowerCase() === emailLimpo)) {
      setMensagemUsuario({ tipo: 'erro', texto: 'Já existe um usuário com este e-mail.' })
      return
    }

    const novo = {
      id: Date.now(),
      nome: nomeLimpo,
      email: emailLimpo,
      tipo,
      cargaPrevista: null,
    }

    setUsuarios([...usuarios, novo])
    aoRegistrar(`Usuário ${novo.nome} cadastrado como ${rotuloTipo[tipo]}`)

    setMensagemUsuario({ tipo: 'sucesso', texto: 'Usuário cadastrado' })
    setNome('')
    setEmail('')
    setTipo('estudante')
  }

  function criarVinculo(e) {
    e.preventDefault()

    const jaTemVinculo = vinculos.some(
      (v) => v.estudanteId === Number(estudanteId) && v.periodo === periodoNome
    )
    if (jaTemVinculo) {
      setMensagemVinculo({
        tipo: 'erro',
        texto: 'Este estudante já possui vínculo neste período.',
      })
      return
    }

    const novo = {
      id: Date.now(),
      estudanteId: Number(estudanteId),
      supervisorId: Number(supervisorId),
      periodo: periodoNome,
    }

    setVinculos([...vinculos, novo])

    const nomeEstudante = nomeDoUsuario(usuarios, novo.estudanteId)
    const nomeSupervisor = nomeDoUsuario(usuarios, novo.supervisorId)
    aoRegistrar(`Vínculo criado: ${nomeEstudante} com ${nomeSupervisor} em ${periodoNome}`)

    setMensagemVinculo({ tipo: 'sucesso', texto: 'Vínculo criado' })
    setEstudanteId('')
    setSupervisorId('')
    setPeriodoNome('')
  }

  return (
    <div>
      <h1 className="titulo">Pessoas e vínculos</h1>
      <p className="subtitulo">
        Cadastre estudantes e supervisores e crie vínculos por período.
      </p>

      <div className="duas-colunas">
        <form className="card formulario-coluna" onSubmit={cadastrarUsuario}>
          <h2 className="caixa-titulo">Novo usuário</h2>

          <label className="campo" htmlFor="usuario-nome">
            <span className="so-leitor">Nome</span>
            <input
              id="usuario-nome"
              type="text"
              placeholder="Nome"
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              required
            />
          </label>

          <label className="campo" htmlFor="usuario-email">
            <span className="so-leitor">E-mail</span>
            <input
              id="usuario-email"
              type="email"
              placeholder="E-mail"
              pattern="[^@\s]+@[^@\s]+\.[^@\s]+"
              title="Informe um e-mail válido, como nome@dominio.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </label>

          <label className="campo" htmlFor="usuario-tipo">
            <span className="so-leitor">Tipo de usuário</span>
            <select
              id="usuario-tipo"
              value={tipo}
              onChange={(e) => setTipo(e.target.value)}
            >
              <option value="estudante">Estudante</option>
              <option value="supervisor">Supervisor/Orientador</option>
              <option value="coordenador">Coordenador</option>
            </select>
          </label>

          <button type="submit" className="botao">Cadastrar</button>

          <div role="status">
            {mensagemUsuario && (
              <p className={mensagemUsuario.tipo === 'erro' ? 'alerta-erro' : 'alerta-sucesso'}>
                {mensagemUsuario.texto}
              </p>
            )}
          </div>
        </form>

        <form className="card formulario-coluna" onSubmit={criarVinculo}>
          <h2 className="caixa-titulo">Novo vínculo</h2>

          <label className="campo" htmlFor="vinculo-estudante">
            <span className="so-leitor">Estudante</span>
            <select
              id="vinculo-estudante"
              value={estudanteId}
              onChange={(e) => setEstudanteId(e.target.value)}
              required
            >
              <option value="">Estudante...</option>
              {estudantes.map((u) => (
                <option key={u.id} value={u.id}>{u.nome}</option>
              ))}
            </select>
          </label>

          <label className="campo" htmlFor="vinculo-supervisor">
            <span className="so-leitor">Supervisor</span>
            <select
              id="vinculo-supervisor"
              value={supervisorId}
              onChange={(e) => setSupervisorId(e.target.value)}
              required
            >
              <option value="">Supervisor...</option>
              {supervisores.map((u) => (
                <option key={u.id} value={u.id}>{u.nome}</option>
              ))}
            </select>
          </label>

          <label className="campo" htmlFor="vinculo-periodo">
            <span className="so-leitor">Período</span>
            <select
              id="vinculo-periodo"
              value={periodoNome}
              onChange={(e) => setPeriodoNome(e.target.value)}
              required
            >
              <option value="">Período...</option>
              {periodos.map((p) => (
                <option key={p.nome} value={p.nome}>{p.nome}</option>
              ))}
            </select>
          </label>

          <button type="submit" className="botao">Vincular</button>

          <div role="status">
            {mensagemVinculo && (
              <p className={mensagemVinculo.tipo === 'erro' ? 'alerta-erro' : 'alerta-sucesso'}>
                {mensagemVinculo.texto}
              </p>
            )}
          </div>
        </form>
      </div>

      <div className="card tabela-card">
        <div className="tabela-rolagem">
          <table className="tabela">
            <thead>
              <tr>
                <th scope="col">Estudante</th>
                <th scope="col">Supervisor</th>
                <th scope="col">Período</th>
              </tr>
            </thead>
            <tbody>
              {vinculos.map((v) => (
                <tr key={v.id}>
                  <td>{nomeDoUsuario(usuarios, v.estudanteId)}</td>
                  <td>{nomeDoUsuario(usuarios, v.supervisorId)}</td>
                  <td>{v.periodo}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default PessoasVinculos