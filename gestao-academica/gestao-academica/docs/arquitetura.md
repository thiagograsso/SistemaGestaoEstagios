# Arquitetura do Sistema – Sistema de Gestão de Estágios e Atividades Acadêmicas

Este documento descreve a arquitetura de software adotada para o desenvolvimento do sistema, detalhando as escolhas tecnológicas, os padrões de projeto e a comunicação entre os componentes da aplicação.

---

## 1. Visão Geral da Arquitetura
O sistema foi concebido estruturalmente em camadas, separando claramente a interface do usuário (Frontend), a lógica de negócios e processamento (Backend) e a persistência de dados (Banco de Dados).
```text
[ Frontend (React) ] ──(HTTP / JSON)──> [ Backend (Java / Spring Boot) ] ──(JPA / SQL)──> [ Banco de Dados (MySQL) ]
``` 

## 2. Escolhas Tecnológicas e Justificativas
| Componente | Tecnologia Escolhida | Justificativa |
|:----------:|:--------------------:|:-------------:|
|Front-end |	React	| Permite o desenvolvimento de interfaces web dinâmicas e componentizadas, facilitando a criação de painéis separados para Estudantes, Orientadores e Coordenadores. |
|Back-end |	Java / Spring Boot | Oferece alta robustez, segurança, padronização corporativa e facilidade na criação de APIs REST robustas para regras de negócio acadêmicas. |
|Gerenciamento de Build	| Gradle | Automação eficiente de compilação e gestão de dependências do ecossistema Java. |
|Banco de Dados Relacional | MySQL | Garantia de integridade e consistência transacional (ACID) essenciais para vínculos acadêmicos, controle de cargas horárias e aprovações; suporte nativo a relacionamentos complexos (1:N, N:N) e excelente integração com o Spring Data JPA. |

## 3. Comunicação entre as Camadas (Frontend, Backend e Banco)
A arquitetura de comunicação opera através de uma API RESTful, onde o fluxo de dados ocorre da seguinte forma:
**1. Interface de Usuário (React):** O usuário interage com as telas e aciona ações (ex: submeter registros de estágio). O front-end empacota os dados e realiza requisições HTTP (GET, POST, etc.) enviando payloads em formato JSON.
**2. Camada de Serviço e Regras (Spring Boot):** O back-end recebe as requisições na API, valida as regras de negócio e permissões acadêmicas pertinentes ao ator logado.
**3. Persistência (MySQL):** O back-end interage com o banco de dados relacional MySQL por meio de mapeamento objeto-relacional (JPA/Hibernate), garantindo a persistência segura e estruturada das informações.

## 4. Histórico de Revisões
| Data | Descrição | Autor(es) |
|:----:|:---------:|:---------:|
| Hoje | Documentação inicial de arquitetura, tecnologias e fluxo de comunicação. | Equipe 02 |
