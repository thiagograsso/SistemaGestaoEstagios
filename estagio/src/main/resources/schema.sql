CREATE DATABASE IF NOT EXISTS sistema_gestao_estagios
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE sistema_gestao_estagios;

CREATE TABLE IF NOT EXISTS instituicao (
    id_instituicao INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    endereco VARCHAR(200),
    contato VARCHAR(100),
    PRIMARY KEY (id_instituicao),
    CONSTRAINT uq_instituicao_cnpj UNIQUE (cnpj)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS unidade (
    id_unidade INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    localizacao VARCHAR(200),
    id_instituicao INT NOT NULL,
    PRIMARY KEY (id_unidade),
    CONSTRAINT fk_unidade_instituicao
      FOREIGN KEY (id_instituicao) REFERENCES instituicao (id_instituicao)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS curso (
    id_curso INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    quantidade_periodos TINYINT UNSIGNED NOT NULL,
    turno VARCHAR(20) NOT NULL,
    id_unidade INT NOT NULL,
    PRIMARY KEY (id_curso),
    CONSTRAINT fk_curso_unidade
      FOREIGN KEY (id_unidade) REFERENCES unidade (id_unidade)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS disciplina (
    id_disciplina INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    carga_horaria INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_disciplina),
    CONSTRAINT fk_disciplina_curso
      FOREIGN KEY (id_curso) REFERENCES curso (id_curso)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(254) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id_usuario),
    CONSTRAINT uq_usuario_email UNIQUE (email)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS academico (
    id_academico INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    matricula VARCHAR(20) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    contato VARCHAR(100),
    endereco VARCHAR(200),
    id_usuario INT,
    PRIMARY KEY (id_academico),
    CONSTRAINT uq_academico_matricula UNIQUE (matricula),
    CONSTRAINT uq_academico_cpf UNIQUE (cpf),
    CONSTRAINT uq_academico_usuario UNIQUE (id_usuario),
    CONSTRAINT fk_academico_usuario
      FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS docente (
    id_docente INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    contato VARCHAR(100),
    endereco VARCHAR(200),
    id_usuario INT,
    PRIMARY KEY (id_docente),
    CONSTRAINT uq_docente_cpf UNIQUE (cpf),
    CONSTRAINT uq_docente_usuario UNIQUE (id_usuario),
    CONSTRAINT fk_docente_usuario
      FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS docente_disciplina (
    id_docente INT NOT NULL,
    id_disciplina INT NOT NULL,
    PRIMARY KEY (id_docente, id_disciplina),
    CONSTRAINT fk_docente_disciplina_docente
      FOREIGN KEY (id_docente) REFERENCES docente (id_docente),
    CONSTRAINT fk_docente_disciplina_disciplina
      FOREIGN KEY (id_disciplina) REFERENCES disciplina (id_disciplina)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS matricula (
    id_matricula INT NOT NULL AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL,
    data_matricula DATE NOT NULL,
    id_academico INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_matricula),
    CONSTRAINT fk_matricula_academico
      FOREIGN KEY (id_academico) REFERENCES academico (id_academico),
    CONSTRAINT fk_matricula_curso
      FOREIGN KEY (id_curso) REFERENCES curso (id_curso)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS periodo_academico (
    id_periodo INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    carga_horaria_minima DECIMAL(6,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_periodo)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS estagio (
    id_estagio INT NOT NULL AUTO_INCREMENT,
    objetivo TEXT,
    local_estagio VARCHAR(150),
    carga_horaria_prevista DECIMAL(6,2),
    data_inicio DATE,
    data_fim DATE,
    status VARCHAR(20),
    id_periodo INT NOT NULL,
    id_matricula INT NOT NULL,
    id_instituicao INT NOT NULL,
    id_docente INT NOT NULL,
    PRIMARY KEY (id_estagio),
    CONSTRAINT fk_estagio_periodo
      FOREIGN KEY (id_periodo) REFERENCES periodo_academico (id_periodo),
    CONSTRAINT fk_estagio_matricula
      FOREIGN KEY (id_matricula) REFERENCES matricula (id_matricula),
    CONSTRAINT fk_estagio_instituicao
      FOREIGN KEY (id_instituicao) REFERENCES instituicao (id_instituicao),
    CONSTRAINT fk_estagio_docente
      FOREIGN KEY (id_docente) REFERENCES docente (id_docente)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS registro_atividade (
    id_registro INT NOT NULL AUTO_INCREMENT,
    data_atividade DATE NOT NULL,
    descricao TEXT NOT NULL,
    quantidade_horas DECIMAL(5,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_submissao DATETIME,
    id_estagio INT NOT NULL,
    PRIMARY KEY (id_registro),
    CONSTRAINT fk_registro_estagio
      FOREIGN KEY (id_estagio) REFERENCES estagio (id_estagio)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS historico_avaliacao (
    id_historico INT NOT NULL AUTO_INCREMENT,
    status_anterior VARCHAR(20),
    status_novo VARCHAR(20) NOT NULL,
    justificativa TEXT,
    data_avaliacao DATETIME NOT NULL,
    id_registro INT NOT NULL,
    id_docente INT,
    id_usuario_responsavel INT NOT NULL,
    PRIMARY KEY (id_historico),
    CONSTRAINT fk_historico_registro
      FOREIGN KEY (id_registro) REFERENCES registro_atividade (id_registro),
    CONSTRAINT fk_historico_docente
      FOREIGN KEY (id_docente) REFERENCES docente (id_docente),
    CONSTRAINT fk_historico_usuario
      FOREIGN KEY (id_usuario_responsavel) REFERENCES usuario (id_usuario)
) ENGINE=InnoDB;
