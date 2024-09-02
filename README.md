# GitHub User Activity

Este projeto consiste no desenvolvimento de uma interface de linha de comando (CLI) simples e intuitiva, projetada para
buscar e exibir a atividade recente de um usuário no GitHub diretamente no terminal. A CLI permite que os usuários
consultem rapidamente as ações mais recentes, como commits, pull requests, e issues, fornecendo uma visão clara e
organizada das atividades diretamente em sua linha de comando, sem a necessidade de acessar o site do GitHub.

## Funcionalidades

- **Commits Recentes:** Obtém todos os commits feitos no repositório mais recente.
- **Issues:** Retorna a issue mais recente aberta ou fechada do GitHub do usuário.
- **Estrela:** Retorna o repositório mais recente que o usuário adicionou aos favoritos.
- **Pull Request:** Retorna o status e o repositório da pull request mais recente aberta.

# Requisitos

- É necessario ter o  [java](https://www.java.com/download/ie_manual.jsp) instalado em sua maquina para rodar o
  programa e tambem o [maven](https://maven.apache.org/install.html) para gerenciar as dependências e compilar o projeto.

# Instalação

- Clone o repositório

```
  git clone https://github.com/pedro-henrique1/GitHub-User-Activity.git
  cd GitHub-User-Activity
```

- Gere o token [aqui](https://github.com/settings/tokens)

- Clone o arquivo `.env.example` e adicione sua chave do GitHub:

```
  cd src/main/resources
  cp .env.example .env

```
- Edite o arquivo `.env` para incluir seu token de acesso pessoal.


- Instalar Dependência:

```
  mvn compile
```

- Executar o programa:

``` 
  mvn exec:java -Dexec.mainClass="com.task.Main"
```
# Uso
Execute o programa e forneça o nome de usuário do GitHub que você deseja consultar:
```
 github-activity `username`
```

# Exemplo de Uso

```
  github-activity kamranahmedse
```

# Exemplo
- Saída esperada:

```plaintext
# Commit mais recente
Pushed 30 commits to kamranahmedse/developer-roadmap

# Issue mais recente
Opened a new issue in kamranahmedse/developer-roadmap

# Repositorio com estrela
Starred arikchakma/nanohost

# Último pull request
Last Pull request em kamranahmedse/developer-roadmap status of closed

```
