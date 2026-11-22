export function formatarData(iso) {
  const [ano, mes, dia] = iso.split('-')
  return `${dia}/${mes}/${ano}`
}

export function formatarDataHora(data) {
  const dois = (n) => String(n).padStart(2, '0')
  const dia = dois(data.getDate())
  const mes = dois(data.getMonth() + 1)
  const ano = data.getFullYear()
  const hora = dois(data.getHours())
  const minuto = dois(data.getMinutes())
  const segundo = dois(data.getSeconds())
  return `${dia}/${mes}/${ano}, ${hora}:${minuto}:${segundo}`
}

export function iniciais(nome) {
  const partes = nome.trim().split(/\s+/)
  const primeira = partes[0][0]
  const ultima = partes.length > 1 ? partes[partes.length - 1][0] : ''
  return (primeira + ultima).toUpperCase()
}

export function nomeDoUsuario(usuarios, id) {
  const usuario = usuarios.find((u) => u.id === id)
  return usuario ? usuario.nome : '—'
}

export const classeSelo = {
  Rascunho: 'selo-cinza',
  Submetido: 'selo-azul',
  Aprovado: 'selo-verde',
  Devolvido: 'selo-vermelho',
}