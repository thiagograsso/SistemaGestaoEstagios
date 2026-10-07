Sistema de Gestão de Estágios e Atividades Acadêmicas

📋 Informações do Projeto

Grupo Nº: 02

Disciplina: Fábrica de Software I

Curso: Engenharia de Software

Universidade: Univille

👥 Integrantes

Ana Luiza Dias da Rocha

Carlos Eduardo Lanser

Marcelo Felipe Momm

Thiago Grasso

🎯 1. Visão Geral do Projeto

1.1 Contexto

Cursos que acompanham estágios, práticas ou atividades acadêmicas precisam controlar rigorosamente estudantes, responsáveis, períodos, carga horária, registros de atividades e aprovações. Atualmente, quando esse acompanhamento ocorre por meio de documentos soltos e trocas de mensagens, a coordenação enfrenta grande dificuldade para identificar pendências e acompanhar a evolução individual de cada estudante.

1.2 Problema a ser Resolvido

O objetivo deste projeto é construir um sistema web para registrar e acompanhar atividades acadêmicas supervisionadas, concentrando o plano de atividades, os registros de execução, o controle de horas e as validações em um único fluxo integrado e transparente.

🎭 2. Atores e Perfis de Usuário

Estudante: Responsável por cadastrar e acompanhar sua atividade, lançar registros de execução e submeter etapas para avaliação.

Supervisor / Orientador: Encarregado de analisar os registros enviados, aprovar as entregas ou solicitar ajustes/correções.

Coordenador: Visão macro de todos os estudantes, responsável por gerenciar os períodos letivos e visualizar pendências gerais do sistema.

🔄 3. Fluxo Principal da Aplicação

Planejamento: O estudante cadastra os dados da sua atividade acadêmica/estágio.

Execução: O estudante realiza as atividades e registra suas horas e descrições no sistema periodicamente.

Análise: O supervisor/orientador revisa os lançamentos e realiza a aprovação ou solicita revisões.

Validação Final: A coordenação acompanha o status global e homologa a conclusão da carga horária.

🗄️ 4. Modelagem de Dados (Diagrama DER)

O diagrama entidade-relacionamento estruturado para suportar o banco de dados da aplicação:

Dica: Insira a imagem do seu DER na pasta do projeto (ex: docs/der.png) e atualize o caminho abaixo.

🛠️ 5. Tecnologias e Estrutura Atual

O projeto está configurado utilizando Gradle para gerenciamento de dependências e automação de build, estruturado em linguagem Java.

Gerenciamento de Build: Gradle (build.gradle, settings.gradle)

Front-end: React

Back-end: Java / Gradle

Banco de Dados: [A definir / Em mapeamento via DER]

🚀 6. Próximos Passos (Roadmap)

[x] Definição da modelagem de dados inicial (Diagrama DER).

[x] Configuração inicial da estrutura do repositório (Gradle e arquivos de controle).

[ ] Desenvolvimento dos wireframes / protótipo de tela.

[ ] Implementação do MVP (Foco no fluxo de cadastro e lançamentos do estudante).

[ ] Implementação dos módulos de validação do orientador e coordenação.
