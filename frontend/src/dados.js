export const usuariosIniciais = [
  { id: 1, nome: 'Ana Luiza', email: 'ana.luiza@univille.br', tipo: 'estudante', cargaPrevista: 120 },
  { id: 2, nome: 'Thiago Grasso', email: 'thiago.grasso@univille.br', tipo: 'estudante', cargaPrevista: null },
  { id: 3, nome: 'Prof. Marcelo Momm', email: 'marcelo.momm@univille.br', tipo: 'supervisor' },
  { id: 4, nome: 'Profa. Fernanda Franco', email: 'fernanda.franco@univille.br', tipo: 'supervisor' },
  { id: 5, nome: 'Carlos Lanzer', email: 'carlos.lanzer@univille.br', tipo: 'coordenador' },
]

export const estudanteLogado = usuariosIniciais[0]
export const supervisorLogado = usuariosIniciais[2]
export const coordenadorLogado = usuariosIniciais[4]

export const usuariosLogados = {
  estudante: estudanteLogado,
  supervisor: supervisorLogado,
  coordenador: coordenadorLogado,
}

export const periodosIniciais = [
  { nome: '2026/2', inicio: '2026-08-01', fim: '2026-12-15', ativo: true },
  { nome: '2026/1', inicio: '2026-02-01', fim: '2026-07-15', ativo: false },
]

export const vinculosIniciais = [
  { id: 1, estudanteId: 1, supervisorId: 3, periodo: '2026/2' },
  { id: 2, estudanteId: 2, supervisorId: 4, periodo: '2026/2' },
]

export const atividadesIniciais = [
  { id: 1, estudanteId: 1, data: '2026-09-02', descricao: 'Levantamento de requisitos com o PO', horas: 4, situacao: 'Aprovado' },
  { id: 2, estudanteId: 1, data: '2026-09-09', descricao: 'Wireframes das telas de login e dashboard', horas: 3, situacao: 'Aprovado' },
  { id: 3, estudanteId: 1, data: '2026-09-16', descricao: 'Protótipo navegável do fluxo de atividades', horas: 5, situacao: 'Submetido' },
  {
    id: 4,
    estudanteId: 1,
    data: '2026-09-23',
    descricao: 'Revisão de acessibilidade',
    horas: 2,
    situacao: 'Devolvido',
    justificativa: 'Detalhe quais critérios WCAG foram verificados.',
  },
  { id: 5, estudanteId: 2, data: '2026-09-10', descricao: 'Configuração do Spring Security', horas: 6, situacao: 'Submetido' },
  { id: 6, estudanteId: 2, data: '2026-09-18', descricao: 'Testes de autenticação', horas: 3, situacao: 'Rascunho' },
]

export const auditoriaInicial = [
  { id: 1, quando: '02/09/2026, 14:00:00', usuario: 'Sistema', acao: 'Dados de demonstração carregados' },
]