import { useState, useEffect } from 'react'
import './PlanoInicial.css'

const periodosCadastrados = ['2026/1', '2026/2', '2027/1']

function PlanoInicial() {
  const [periodo, setPeriodo] = useState('')
  const [local, setLocal] = useState('')
  const [objetivo, setObjetivo] = useState('')
  const [carga, setCarga] = useState('')
  const [salvo, setSalvo] = useState(false)

  useEffect(() => {
    if (!salvo) return

    const cronometro = setTimeout(() => {
      setSalvo(false)
    }, 2000)

    return () => clearTimeout(cronometro)
  }, [salvo])

  function salvarPlano(e) {
    e.preventDefault()
    setSalvo(true)
  }

  return (
    <div>
      <h1 className="titulo">Plano inicial</h1>
      <p className="subtitulo">
        Instituição/local, período, objetivo e carga prevista.
      </p>

      <form className="card formulario" onSubmit={salvarPlano}>
        <label className="campo" htmlFor="periodo">
          Período
          <select
            id="periodo"
            value={periodo}
            onChange={(e) => setPeriodo(e.target.value)}
            required
          >
            <option value="">Selecione um período</option>
            {periodosCadastrados.map((p) => (
              <option key={p} value={p}>{p}</option>
            ))}
          </select>
        </label>

        <label className="campo" htmlFor="local">
          Instituição / local
          <input
            id="local"
            type="text"
            value={local}
            onChange={(e) => setLocal(e.target.value)}
            required
          />
        </label>

        <label className="campo" htmlFor="objetivo">
          Objetivo
          <textarea
            id="objetivo"
            rows="4"
            value={objetivo}
            onChange={(e) => setObjetivo(e.target.value)}
            required
          />
        </label>

        <label className="campo" htmlFor="carga">
          Carga prevista (h)
          <input
            id="carga"
            type="number"
            min="1"
            value={carga}
            onChange={(e) => setCarga(e.target.value)}
            required
          />
        </label>

        <button type="submit" className="botao">Salvar plano</button>
        <div role="status">
          {salvo && <p className="alerta-sucesso">Plano salvo</p>}
        </div>
      </form>
    </div>
  )
}

export default PlanoInicial