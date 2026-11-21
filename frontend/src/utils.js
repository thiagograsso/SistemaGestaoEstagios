export function formatarData(iso) {
  const [ano, mes, dia] = iso.split('-')
  return `${dia}/${mes}/${ano}`
}

export const classeSelo = {
  Rascunho: 'selo-cinza',
  Submetido: 'selo-azul',
  Aprovado: 'selo-verde',
  Devolvido: 'selo-vermelho',
}