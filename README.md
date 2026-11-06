# Sistema de Gestão de Estágios e Atividades Acadêmicas

## 📋 Informações do Projeto

- **Grupo Nº:** 02
- **Disciplina:** Fábrica de Software I
- **Curso:** Engenharia de Software
- **Universidade:** Univille

---

## 👥 Integrantes

- Ana Luiza Dias da Rocha
- Carlos Eduardo Lanser
- Marcelo Felipe Momm
- Thiago Grasso

---

## 🎯 1. Visão Geral do Projeto

### 1.1 Contexto
Cursos que acompanham estágios, práticas ou atividades acadêmicas precisam controlar rigorosamente estudantes, responsáveis, períodos, carga horária, registros de atividades e aprovações. Atualmente, quando esse acompanhamento ocorre por meio de documentos soltos e trocas de mensagens, a coordenação enfrenta grande dificuldade para identificar pendências e acompanhar a evolução individual de cada estudante.

### 1.2 Problema a ser Resolvido
O objetivo deste projeto é construir um sistema web para registrar e acompanhar atividades acadêmicas supervisionadas, concentrando o plano de atividades, os registros de execução, o controle de horas e as validações em um único fluxo integrado e transparente.

---

## 🎭 2. Atores e Perfis de Usuário

- **Estudante:** Responsável por cadastrar e acompanhar sua atividade, lançar registros de execução e submeter etapas para avaliação.
- **Supervisor / Orientador:** Encarregado de analisar os registros enviados, aprovar as entregas ou solicitar ajustes/correções.
- **Coordenador:** Visão macro de todos os estudantes, responsável por gerenciar os períodos letivos e visualizar pendências gerais do sistema.

---

## 🔄 3. Fluxo Principal da Aplicação

- **Planejamento:** O estudante cadastra os dados da sua atividade acadêmica/estágio.
- **Execução:** O estudante realiza as atividades e registra suas horas e descrições no sistema periodicamente.
- **Análise:** O supervisor/orientador revisa os lançamentos e realiza a aprovação ou solicita revisões.
- **Validação Final:** A coordenação acompanha o status global e homologa a conclusão da carga horária.

---

## 🗄️ 4. Modelagem de Dados (Diagrama DER)

O diagrama entidade-relacionamento estruturado para suportar o banco de dados da aplicação:


[DER.pdf](https://github.com/user-attachments/files/33793076/DER.pdf)
<img width="1536" height="1024" alt="DiagramaDER" src="https://github.com/user-attachments/assets/cb0a2c60-66fb-4e53-a699-15e7fc7abaa8" />


---

## 5. Protótipo e Briefing
### Tela 1 - Minha situação (Estudante)
- **Campo "Horas validadas":** Total de horas correspondentes às atividades do estudante que já foram aprovadas;
- **Campo "Em análise":** Total de horas correspondentes às atividades que estão aguardando aprovação;
- **Campo "Devolvidas":** Total de atividades do estudante que foram devolvidas;
- **Campo "Rascunhos":** Total de atividades do estudante que ainda não foram enviadas para análise;
- **Campo "Progresso em relação à carga prevista no plano":** Total de horas validadas em comparação às horas definidas

### Tela 2 - Plano inicial (Estudante)
- **Campo "Período":** Será disponível para seleção, apenas os períodos que forem cadastrados pelo supervisor/orientador
- **Campo "Instituição / Local":** Campo de texto, referente ao local de onde será cadastrado o plano inicial
- **Campo "Objetivo":** Campo de texto, referente ao objetivo do projeto
- **Campo "Carga prevista":** Carga prevista do projeto, em horas
- Ao salvar plano, será exibido um alerta confirmando que o plano foi salvo

### Tela 3 - Dashboard (Supervisor/Orientador)
- **Campo "Período":** Será disponível para seleção os períodos ativos
- **Campo "Estudantes":** Total de estudantes vinculados ao supervisor/coordenador logado
- **Campo "Horas validadas":** Total de horas correspondentes às atividades do estudante que já foram aprovadas, que correspondem ao período filtrado
- **Campo "Horas em análise":** Total de horas correspondentes às atividades que estão aguardando aprovação, que correspondem ao período filtrado
- **Campo "Devoluções abertas":** Total de atividades devolvidas pelo supervisor/orientador, que correspondem ao período filtrado
- Listagem de estudantes que são vinculados ao supervisor/coordenador logado. Contém os campos Estudante, Supervisor, Plano, Validadas, Em análise, Devolvidas

### Tela 4 - Análise de registros (Supervisor/Orientador)
- **Botão "Aguardando análise":** Filtra pelas atividades que estão aguardando análise
- **Botão "Todos":** Retorna todas as atividades vinculadas ao supervisor/orientador logado
- **Card das atividades:" Nome do estudante, data e horas correspondentes acima. Em destaque, o título da atividade com duas opções: Aprovar e Devolver. Ao aprovar, atividade muda de situação para Aprovado, e atividade é validada. Ao devolver, a atividade muda de situação para devolvido e atividade é retornada para o estudante realizar as devidas alterações.

### Tela 5 - Dashboard (Coordenador)
- **Campo "Período":** Será disponível para seleção os períodos ativos
- **Campo "Estudantes":** Total de estudantes, que correspondem ao período filtrado
- **Campo "Horas validadas":** Total de horas correspondentes às atividades do estudante que já foram aprovadas, que correspondem ao período filtrado
- **Campo "Horas em análise":** Total de horas correspondentes às atividades que estão aguardando aprovação, que correspondem ao período filtrado
- **Campo "Devoluções abertas":** Total de atividades devolvidas pelo supervisor/orientador, que correspondem ao período filtrado
- Listagem de estudantes. Contém os campos Estudante, Supervisor, Plano, Validadas, Em análise, Devolvidas

### Tela 6 - Pessoas e vínculos (Coordenador)
- **Campo "Nome" (Novo usuário):** Nome do usuário
- **E-mail (Novo usuário):** E-mail do usuário em formato válido
- **Seleção de tipo de usuário (Novo usuário):** Opções Estudante, Supervisor/Orientador, Coordenador
- **Campo "Estudante" (Novo vínculo):** Usuários cadastrados do tipo Estudante
- **Campo "Supervisor" (Novo vínculo):** Usuários cadastrados do tipo Supervisor/Orientador
- **Campo "Período" (Novo vínculo):** Períodos cadastrados no sistema pelo Supervisor/Orientador
- Listagem de pessoas e vínculos, contendo os campos Estudante, Supervisor e Período. Um registro só é listado ao realizar o vínculo.

### Tela 7 - Todos os registros (Coordenador)
- Listagem de todos os registros de atividades, contendo os campos Estudante, Data, Descrição, Horas e Situação. Acima, há um botão sendo possível filtrar por cada situação (Rascunho, Submetido, Aprovado e Devolvido)

### Tela 8 - Rastreabilidade (Coordenador)
- Listagem de ações relevantes no sistema, contendo os campos Quando (Data e Horário), Usuário e Ação.

### Todas as telas
- **Botão de "Sair do sistema":** Responsável por deslogar do sistema, e voltar à página de login

[protótipo](https://www.figma.com/proto/Wi3HheDZJ1sH4WglWv5en7/Untitled?node-id=90-193&t=QR4alUHoweBqqunQ-0&scaling=min-zoom&content-scaling=fixed&page-id=0%3A1&starting-point-node-id=97%3A242&show-proto-sidebar=1)

---

## 🛠️ 6. Tecnologias e Estrutura Atual

O projeto utiliza tecnologias modernas voltadas para aplicações web robustas, estruturadas com padrões corporativos.

- **Linguagem:** Java (Versão padrão atual LTS)
- **Framework Back-end:** Spring Boot
- **Gerenciamento de Build e Dependências:** Gradle (`build.gradle`, `settings.gradle`) *(compatível com o fluxo Maven/Gradle exigido)*
- **Front-end:** React
- **Banco de Dados:** MySQL

---

## 🚀 7. Próximos Passos (Roadmap)

- [x] Definição da modelagem de dados inicial (Diagrama DER).
- [x] Configuração inicial da estrutura do repositório (Java/Spring Boot, Gradle e arquivos de controle).
- [ ] Desenvolvimento dos wireframes / protótipo de tela.
- [ ] Implementação do MVP (Foco no fluxo de cadastro e lançamentos do estudante).
- [ ] Implementação dos módulos de validação do orientador e coordenação.
