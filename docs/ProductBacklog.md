# Product Backlog – Black BattleShip Test Suite

**Projeto:** BlackBattleShip – Testes de Aceitação Selenium  
**Website alvo:** https://www.blackbattleship.com/  
**Data:** 2026-05-13  
**Equipa:** Engenharia de Software – Testes Caixa-Preta  

---

## Épicos

| ID    | Épico                          | Descrição                                               |
|-------|--------------------------------|---------------------------------------------------------|
| EP-01 | Acesso e Navegação             | Verificar homepage e navegação principal                |
| EP-02 | Gestão de Jogador              | Nickname, autenticação e perfil                         |
| EP-03 | Modo Single Player             | Jogo contra robot/computador                            |
| EP-04 | Modo Multiplayer               | Criação e gestão de salas online                        |
| EP-05 | Instruções e Regras            | Acesso a conteúdo de ajuda                              |
| EP-06 | Interface e UX                 | Validade da interface e elementos visuais               |

---

## Product Backlog Items (PBIs)

### EP-01 – Acesso e Navegação

| ID     | Título                                  | Prioridade | Pontos | Estado       |
|--------|-----------------------------------------|------------|--------|--------------|
| PBI-01 | Abrir homepage com sucesso              | Alta       | 1      | Concluído    |
| PBI-02 | Verificar título da página              | Alta       | 1      | Concluído    |
| PBI-03 | Verificar URL da homepage               | Alta       | 1      | Concluído    |
| PBI-04 | Verificar carregamento completo (ready) | Média      | 2      | Concluído    |
| PBI-05 | Verificar presença de botões de acção   | Alta       | 2      | Concluído    |
| PBI-06 | Verificar links de navegação            | Baixa      | 1      | Concluído    |

### EP-02 – Gestão de Jogador

| ID     | Título                                  | Prioridade | Pontos | Estado       |
|--------|-----------------------------------------|------------|--------|--------------|
| PBI-07 | Campo de nickname visível               | Alta       | 1      | Concluído    |
| PBI-08 | Introduzir nickname no campo            | Alta       | 2      | Concluído    |
| PBI-09 | Limpar campo de nickname                | Média      | 1      | Concluído    |
| PBI-10 | Nickname com caracteres especiais       | Baixa      | 1      | Concluído    |
| PBI-11 | Validação de nickname vazio             | Média      | 3      | Backlog      |
| PBI-12 | Nickname máximo de caracteres           | Baixa      | 2      | Backlog      |

### EP-03 – Modo Single Player

| ID     | Título                                       | Prioridade | Pontos | Estado       |
|--------|----------------------------------------------|------------|--------|--------------|
| PBI-13 | Botão "Jogar vs Robot" visível               | Alta       | 1      | Concluído    |
| PBI-14 | Iniciar jogo vs robot com nickname           | Alta       | 3      | Concluído    |
| PBI-15 | Tabuleiro aparece após iniciar jogo          | Alta       | 3      | Concluído    |
| PBI-16 | Verificar dimensões do tabuleiro (10x10)     | Média      | 3      | Backlog      |
| PBI-17 | Posicionar navios no tabuleiro               | Alta       | 5      | Backlog      |
| PBI-18 | Efectuar jogada (clicar numa célula)         | Alta       | 5      | Backlog      |
| PBI-19 | Verificar acerto ("hit")                     | Alta       | 3      | Backlog      |
| PBI-20 | Verificar falha ("miss")                     | Alta       | 3      | Backlog      |
| PBI-21 | Robot faz jogada automática                  | Média      | 5      | Backlog      |
| PBI-22 | Mensagem de vitória exibida                  | Alta       | 3      | Backlog      |
| PBI-23 | Mensagem de derrota exibida                  | Alta       | 3      | Backlog      |
| PBI-24 | Jogar nova partida após fim do jogo          | Média      | 2      | Backlog      |

### EP-04 – Modo Multiplayer

| ID     | Título                                       | Prioridade | Pontos | Estado       |
|--------|----------------------------------------------|------------|--------|--------------|
| PBI-25 | Botão Multiplayer visível                    | Alta       | 1      | Concluído    |
| PBI-26 | Acesso ao lobby multiplayer                  | Alta       | 3      | Concluído    |
| PBI-27 | Botão "Criar Sala" no lobby                  | Alta       | 2      | Concluído    |
| PBI-28 | Criar sala gera código único                 | Alta       | 5      | Concluído    |
| PBI-29 | Campo de código de sala disponível           | Alta       | 2      | Concluído    |
| PBI-30 | Entrar com código de sala válido             | Alta       | 5      | Backlog      |
| PBI-31 | Tratamento de código de sala inválido        | Média      | 3      | Concluído    |
| PBI-32 | Indicador de espera por jogador              | Média      | 2      | Backlog      |
| PBI-33 | Lista de jogadores na sala                   | Baixa      | 2      | Backlog      |
| PBI-34 | Início de jogo quando ambos prontos          | Alta       | 5      | Backlog      |
| PBI-35 | Copiar código da sala                        | Baixa      | 2      | Backlog      |

### EP-05 – Instruções e Regras

| ID     | Título                                       | Prioridade | Pontos | Estado       |
|--------|----------------------------------------------|------------|--------|--------------|
| PBI-36 | Link de instruções visível                   | Alta       | 1      | Concluído    |
| PBI-37 | Página de instruções carrega conteúdo        | Alta       | 3      | Concluído    |
| PBI-38 | Conteúdo das instruções não está vazio       | Alta       | 2      | Concluído    |
| PBI-39 | Título consistente na página de instruções   | Média      | 1      | Concluído    |
| PBI-40 | Botão "Voltar" regressa ao menu              | Alta       | 2      | Concluído    |
| PBI-41 | Ciclo homepage → instruções → homepage       | Média      | 3      | Concluído    |
| PBI-42 | Instruções contêm referência a navios        | Baixa      | 2      | Backlog      |
| PBI-43 | Instruções contêm referência a grelha 10x10  | Baixa      | 2      | Backlog      |

### EP-06 – Interface e UX

| ID     | Título                                       | Prioridade | Pontos | Estado       |
|--------|----------------------------------------------|------------|--------|--------------|
| PBI-44 | Botões têm texto visível                     | Média      | 2      | Concluído    |
| PBI-45 | Elementos de lobby são interactivos          | Média      | 2      | Concluído    |
| PBI-46 | Página responsiva em 1280x900                | Baixa      | 3      | Backlog      |
| PBI-47 | Sem erros de console JavaScript              | Média      | 3      | Backlog      |
| PBI-48 | Imagens carregam correctamente               | Baixa      | 2      | Backlog      |
| PBI-49 | Favicon presente                             | Baixa      | 1      | Backlog      |

---

## Critérios de Definição de Concluído (DoD)

- [ ] Código Java compila sem erros
- [ ] Teste executa com sucesso no IntelliJ IDEA
- [ ] Teste executa via `mvn test`
- [ ] Assert JUnit presente e com mensagem descritiva
- [ ] Uso de explicit waits (sem depender de implicit wait)
- [ ] Código segue padrão POM (Page Object ≠ Test)
- [ ] Javadoc nos métodos públicos
- [ ] Sem dependências de Allure, Selenide ou TestNG

---

## Velocidade Estimada por Sprint

| Sprint | PBIs                    | Story Points | Foco                        |
|--------|-------------------------|--------------|-----------------------------|
| S1     | PBI-01 a PBI-10         | 12           | Navegação e Nickname        |
| S2     | PBI-11 a PBI-24         | 28           | Single Player               |
| S3     | PBI-25 a PBI-35         | 27           | Multiplayer / Lobby         |
| S4     | PBI-36 a PBI-49         | 24           | Instruções e UX             |
