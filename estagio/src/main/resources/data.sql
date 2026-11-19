-- ==========================================
-- DADOS DE EXEMPLO - SISTEMA DE ESTÁGIOS
-- ==========================================

-- 1. INSTITUICAO

INSERT INTO instituicao
(id_instituicao, nome, cnpj, endereco, contato)
VALUES
(1, 'Universidade do Norte', '12345678000101', 'Rua Central, 100', '(47) 3333-1000'),
(2, 'Instituto Tecnológico', '23456789000102', 'Avenida Brasil, 200', '(47) 3333-2000'),
(3, 'Faculdade Regional', '34567890000103', 'Rua das Flores, 300', '(47) 3333-3000')
ON DUPLICATE KEY UPDATE
id_instituicao = id_instituicao;


-- 2. UNIDADE

INSERT INTO unidade
(id_unidade, nome, localizacao, id_instituicao)
VALUES
(1, 'Unidade Central', 'Joinville - SC', 1),
(2, 'Unidade Tecnológica', 'Blumenau - SC', 2),
(3, 'Unidade Regional', 'Jaraguá do Sul - SC', 3)
ON DUPLICATE KEY UPDATE
id_unidade = id_unidade;


-- 3. CURSO

INSERT INTO curso
(id_curso, nome, quantidade_periodos, turno, id_unidade)
VALUES
(1, 'Engenharia de Software', 8, 'Noturno', 1),
(2, 'Engenharia Mecânica', 10, 'Matutino', 2),
(3, 'Análise e Desenvolvimento de Sistemas', 5, 'Noturno', 3)
ON DUPLICATE KEY UPDATE
id_curso = id_curso;


-- 4. DISCIPLINA

INSERT INTO disciplina
(id_disciplina, nome, carga_horaria, id_curso)
VALUES
(1, 'Banco de Dados', 80, 1),
(2, 'Programação Java', 100, 1),
(3, 'Redes de Computadores', 60, 3)
ON DUPLICATE KEY UPDATE
id_disciplina = id_disciplina;


-- 5. USUARIO

INSERT INTO usuario
(id_usuario, email, senha_hash, perfil, ativo)
VALUES
(1, 'joao.academico@email.com', 'hash_senha_joao', 'ACADEMICO', TRUE),
(2, 'maria.academico@email.com', 'hash_senha_maria', 'ACADEMICO', TRUE),
(3, 'pedro.academico@email.com', 'hash_senha_pedro', 'ACADEMICO', TRUE),
(4, 'carlos.docente@email.com', 'hash_senha_carlos', 'DOCENTE', TRUE),
(5, 'ana.docente@email.com', 'hash_senha_ana', 'DOCENTE', TRUE),
(6, 'paulo.docente@email.com', 'hash_senha_paulo', 'DOCENTE', TRUE)
ON DUPLICATE KEY UPDATE
id_usuario = id_usuario;


-- 6. ACADEMICO

INSERT INTO academico
(id_academico, nome, matricula, cpf, contato, endereco, id_usuario)
VALUES
(1, 'João da Silva', '2026001', '12345678901', '(47) 99911-1111', 'Rua A, 100 - Joinville', 1),
(2, 'Maria Oliveira', '2026002', '23456789012', '(47) 99922-2222', 'Rua B, 200 - Blumenau', 2),
(3, 'Pedro Santos', '2026003', '34567890123', '(47) 99933-3333', 'Rua C, 300 - Jaraguá do Sul', 3)
ON DUPLICATE KEY UPDATE
id_academico = id_academico;


-- 7. DOCENTE

INSERT INTO docente
(id_docente, nome, cpf, contato, endereco, id_usuario)
VALUES
(1, 'Carlos Almeida', '45678901234', '(47) 98811-1111', 'Rua D, 400 - Joinville', 4),
(2, 'Ana Souza', '56789012345', '(47) 98822-2222', 'Rua E, 500 - Blumenau', 5),
(3, 'Paulo Martins', '67890123456', '(47) 98833-3333', 'Rua F, 600 - Jaraguá do Sul', 6)
ON DUPLICATE KEY UPDATE
id_docente = id_docente;


-- 8. DOCENTE_DISCIPLINA

INSERT INTO docente_disciplina
(id_docente, id_disciplina)
VALUES
(1, 1),
(2, 2),
(3, 3)
ON DUPLICATE KEY UPDATE
id_docente = id_docente;


-- 9. MATRICULA

INSERT INTO matricula
(id_matricula, status, data_matricula, id_academico, id_curso)
VALUES
(1, 'Ativa', '2026-02-10', 1, 1),
(2, 'Ativa', '2026-02-11', 2, 2),
(3, 'Ativa', '2026-02-12', 3, 3)
ON DUPLICATE KEY UPDATE
id_matricula = id_matricula;


-- 10. PERIODO_ACADEMICO

INSERT INTO periodo_academico
(id_periodo, nome, data_inicio, data_fim, carga_horaria_minima, status)
VALUES
(1, '2026 - Primeiro Semestre', '2026-02-01', '2026-06-30', 200.00, 'Ativo'),
(2, '2026 - Segundo Semestre', '2026-08-01', '2026-12-15', 220.00, 'Planejado'),
(3, '2027 - Primeiro Semestre', '2027-02-01', '2027-06-30', 240.00, 'Planejado')
ON DUPLICATE KEY UPDATE
id_periodo = id_periodo;


-- 11. ESTAGIO

INSERT INTO estagio
(id_estagio, objetivo, local_estagio, carga_horaria_prevista,
 data_inicio, data_fim, status, id_periodo, id_matricula,
 id_instituicao, id_docente)
VALUES
(1, 'Desenvolvimento de sistemas web', 'Empresa Tech', 200.00,
 '2026-03-01', '2026-06-30', 'Em andamento', 1, 1, 1, 1),
(2, 'Atividades de manutenção industrial', 'Indústria Mecânica', 220.00,
 '2026-08-01', '2026-11-30', 'Planejado', 2, 2, 2, 2),
(3, 'Desenvolvimento de aplicações móveis', 'Empresa Digital', 240.00,
 '2027-02-01', '2027-06-30', 'Planejado', 3, 3, 3, 3)
ON DUPLICATE KEY UPDATE
id_estagio = id_estagio;


-- 12. REGISTRO_ATIVIDADE

INSERT INTO registro_atividade
(id_registro, data_atividade, descricao, quantidade_horas,
 status, data_submissao, id_estagio)
VALUES
(1, '2026-03-05', 'Desenvolvimento da tela de login', 5.00,
 'Aprovado', '2026-03-05 17:30:00', 1),
(2, '2026-08-10', 'Inspeção de equipamentos industriais', 6.00,
 'Pendente', '2026-08-10 18:00:00', 2),
(3, '2027-02-15', 'Implementação de funcionalidades do aplicativo', 4.00,
 'Pendente', '2027-02-15 16:45:00', 3)
ON DUPLICATE KEY UPDATE
id_registro = id_registro;


-- 13. HISTORICO_AVALIACAO

INSERT INTO historico_avaliacao
(id_historico, status_anterior, status_novo, justificativa,
 data_avaliacao, id_registro, id_docente, id_usuario_responsavel)
VALUES
(1, 'Pendente', 'Aprovado', 'Atividade realizada corretamente',
 '2026-03-06 10:00:00', 1, 1, 4),
(2, 'Pendente', 'Em análise', 'Necessita de conferência das horas',
 '2026-08-11 14:30:00', 2, 2, 5),
(3, 'Pendente', 'Rejeitado', 'Descrição da atividade incompleta',
 '2027-02-16 09:15:00', 3, 3, 6)
ON DUPLICATE KEY UPDATE
id_historico = id_historico;
