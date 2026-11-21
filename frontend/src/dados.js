export const supervisorLogado = {
  id: 1,
  nome: 'Prof. Marcelo Momm',
  iniciais: 'PM',
}

export const estudantes = [
  { id: 1, nome: 'Ana Luiza', iniciais: 'AL', supervisorId: 1, cargaPrevista: 120 },
]

export const estudanteLogado = estudantes[0]

export const periodos = [
  { nome: '2026/1', inicio: '2026-02-01', fim: '2026-07-31', ativo: false },
  { nome: '2026/2', inicio: '2026-08-01', fim: '2026-12-31', ativo: true },
  { nome: '2027/1', inicio: '2027-02-01', fim: '2027-07-31', ativo: true },
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
]