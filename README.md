# Sistema de Gestão de Estágios e Atividades Acadêmicas

Aplicação web para planejar estágios, registrar atividades acadêmicas, controlar carga horária e aprovar ou devolver registros pelos perfis de estudante, supervisor/orientador e coordenador.

## Índice

- [Visão geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Como executar no Windows](#como-executar-no-windows)
- [Como executar no Linux ou macOS](#como-executar-no-linux-ou-macos)
- [Usuários de demonstração](#usuários-de-demonstração)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Banco de dados](#banco-de-dados)
- [Endpoints principais](#endpoints-principais)
- [Build e validação](#build-e-validação)
- [Limitações atuais](#limitações-atuais)
- [Solução de problemas](#solução-de-problemas)

## Visão geral

O sistema contempla os seguintes fluxos:

- Estudante: consultar situação, preencher plano inicial e criar, editar, excluir e submeter atividades.
- Supervisor/orientador: visualizar o dashboard e analisar registros submetidos.
- Coordenador: gerenciar períodos, pessoas, vínculos, registros e rastreabilidade.
- Login por e-mail e senha com identificação do perfil.
- Persistência das informações em banco H2 durante o funcionamento da aplicação.

## Arquitetura

```text
Navegador
   |
   v
React + Vite  (porta 5173)
   |
   | HTTP/JSON
   v
Spring Boot REST (porta 8080)
   |
   v
Spring Data JPA / Hibernate
   |
   v
Banco H2 em arquivo
```

O front-end está em `frontend/` e o back-end em `estagio/`. As APIs REST são consumidas pelo navegador, enquanto o H2 é usado pela camada JPA.

## Tecnologias

### Back-end

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 Database
- Maven Wrapper

### Front-end

- React 19
- Vite 8
- JavaScript ES Modules
- CSS

## Pré-requisitos

Instale antes de iniciar:

1. **Java JDK 21**.
2. **Node.js 20.19+ ou 22.12+**.
3. **npm** compatível com a versão do Node.
4. **Git**.

Verifique as ferramentas:

```bash
java -version
node --version
npm --version
git --version
```

> O projeto inclui o Maven Wrapper. Não é necessário instalar o Maven separadamente.

## Como executar no Windows

### 1. Clonar o projeto

```powershell
 git clone https://github.com/thiagograsso/SistemaGestaoEstagios.git
 cd SistemaGestaoEstagios
```

### 2. Iniciar o back-end

Em um terminal PowerShell:

```powershell
cd estagio
.\mvnw.cmd spring-boot:run
```

Aguarde até aparecer:

```text
Started EstagioApplication
Tomcat started on port 8080
```

O back-end estará em:

- API: <http://localhost:8080>
- H2 console: <http://localhost:8080/h2-console>

### 3. Iniciar o front-end

Abra um segundo terminal na raiz do projeto:

```powershell
cd frontend
npm install
npm run dev
```

Acesse:

- Aplicação: <http://localhost:5173>

### 4. Alternativa para o front-end já instalado

Se as dependências já estiverem instaladas:

```powershell
cd frontend
npm run dev
```

## Como executar no Linux ou macOS

### 1. Clonar e entrar no projeto

```bash
git clone https://github.com/thiagograsso/SistemaGestaoEstagios.git
cd SistemaGestaoEstagios
```

### 2. Executar o back-end

```bash
cd estagio
chmod +x mvnw
./mvnw spring-boot:run
```

### 3. Executar o front-end

Em outro terminal:

```bash
cd frontend
npm install
npm run dev
```

Acesse <http://localhost:5173>.

## Usuários de demonstração

Estes usuários são criados automaticamente pelo `DataSeeder` na primeira inicialização:

| Perfil | E-mail | Senha |
|---|---|---|
| Estudante | `ana.luiza@univille.br` | `estudante123` |
| Supervisor/Orientador | `marcelo.momm@univille.br` | `supervisor123` |
| Coordenador | `carlos.lanzer@univille.br` | `coordenador123` |

Os dados são armazenados no banco H2 local. Alterações permanecem enquanto o arquivo `estagio/data/gestao-estagios.mv.db` existir.

## Estrutura do projeto

```text
SistemaGestaoEstagios/
├── estagio/
│   ├── src/main/java/br/com/sistema/estagio/
│   │   ├── config/       # Seed e configuração CORS
│   │   ├── controller/   # Endpoints REST
│   │   ├── model/        # Entidades JPA
│   │   ├── repository/   # Repositórios Spring Data
│   │   └── service/      # Regras de negócio
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   ├── schema.sql
│   │   └── data.sql
│   └── pom.xml
└── frontend/
    ├── src/
    │   ├── components/   # Barra lateral e barra superior
    │   ├── pages/        # Telas por perfil
    │   ├── api.js        # Comunicação com a API
    │   └── App.jsx       # Navegação e estado principal
    └── package.json
```

## Banco de dados

O projeto usa H2 em arquivo pela configuração padrão:

```properties
spring.datasource.url=jdbc:h2:file:./data/gestao-estagios;MODE=MySQL;DATABASE_TO_LOWER=TRUE
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=never
```

Isso permite executar o projeto sem instalar MySQL. Os arquivos do banco ficam dentro de `estagio/data/`.

Os arquivos `schema.sql` e `data.sql` existem como referência do modelo MySQL, mas não são executados pela configuração H2 atual.

Para reiniciar os dados do banco H2, pare o back-end e apague a pasta:

```powershell
Remove-Item -Recurse -Force estagio\data
```

No Linux ou macOS:

```bash
rm -rf estagio/data
```

Na próxima inicialização, o `DataSeeder` recria os usuários e períodos iniciais.

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/api/auth/login` | Valida e-mail e senha |
| GET | `/api/periodos` | Lista períodos acadêmicos |
| POST | `/api/periodos` | Cria período acadêmico |
| PUT | `/api/periodos/{id}` | Atualiza período acadêmico |
| GET | `/api/docentes` | Lista docentes |
| GET | `/api/academicos` | Lista acadêmicos |
| GET | `/api/estagios` | Lista estágios |
| GET | `/api/registro-atividades` | Lista registros de atividades |
| POST | `/api/registro-atividades` | Cria registro de atividade |
| PUT | `/api/registro-atividades/{id}` | Atualiza registro de atividade |

Exemplo de login:

```json
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "ana.luiza@univille.br",
  "senha": "estudante123"
}
```

## Build e validação

### Back-end

```bash
cd estagio
./mvnw compile
```

No Windows, use `mvnw.cmd`:

```powershell
cd estagio
.\mvnw.cmd compile
```

### Front-end

```bash
cd frontend
npm run build
```

O build de produção será criado em `frontend/dist/`.

### Executar os testes

```bash
cd estagio
./mvnw test
```

## Limitações atuais

- A tela ainda contém parte dos dados demonstrativos no front-end.
- Algumas telas funcionam no estado local da navegação e ainda não persistem todas as entidades pelo REST.
- O login atual usa consulta direta ao usuário e não emite token JWT/sessão segura.
- Senhas demonstrativas são armazenadas como texto no banco atual e devem ser substituídas por hash para uso real.
- H2 é adequado para desenvolvimento e demonstração; MySQL pode ser configurado para ambiente de produção.

## Solução de problemas

### Porta 8080 já está em uso

Identifique e encerre o processo antigo que está usando a porta:

```powershell
Get-NetTCPConnection -LocalPort 8080 -State Listen
Stop-Process -Id <PID> -Force
```

### Porta 5173 já está em uso

O Vite mostrará automaticamente outra porta disponível. Use o endereço informado no terminal.

### Erro de Node.js no Vite

Atualize para Node.js 20.19+ ou 22.12+. Depois execute:

```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
npm run dev
```

No Windows:

```powershell
cd frontend
Remove-Item -Recurse -Force node_modules
Remove-Item package-lock.json
npm install
npm run dev
```

### Back-end não encontra o banco

Certifique-se de executar o Maven a partir da pasta `estagio`. A URL H2 usa um caminho relativo a essa pasta.

### Alterações não aparecem no navegador

- Atualize a página.
- Verifique se o back-end está ativo na porta 8080.
- Verifique o console do navegador para erros CORS ou de rede.

## Integrantes

- Ana Luiza Dias da Rocha
- Carlos Eduardo Lanser
- Marcelo Felipe Momm
- Thiago Grasso
