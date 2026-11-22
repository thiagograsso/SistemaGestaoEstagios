function Rastreabilidade({ auditoria }) {
  return (
    <div>
      <h1 className="titulo">Rastreabilidade</h1>
      <p className="subtitulo">Alterações relevantes com data/hora e usuário.</p>

      <div className="card tabela-card">
        <div className="tabela-rolagem">
          <table className="tabela">
            <thead>
              <tr>
                <th scope="col">Quando</th>
                <th scope="col">Usuário</th>
                <th scope="col">Ação</th>
              </tr>
            </thead>
            <tbody>
              {auditoria.map((item) => (
                <tr key={item.id}>
                  <td>{item.quando}</td>
                  <td>{item.usuario}</td>
                  <td>{item.acao}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default Rastreabilidade