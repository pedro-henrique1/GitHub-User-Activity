# GitHub User Activity

Este projeto consiste no desenvolvimento de uma interface de linha de comando (CLI) simples e intuitiva, projetada para
buscar e exibir a atividade recente de um usuário no GitHub diretamente no terminal. A CLI permite que os usuários
consultem rapidamente as ações mais recentes, como commits, pull requests, e issues, fornecendo uma visão clara e
organizada das atividades diretamente em sua linha de comando, sem a necessidade de acessar o site do GitHub.

## Funcionalidades

- **Commits Recentes:** Pegar todos os commits feitos no repositorio mais recente.
- **Issues:** Retorna a issues mais recente aberta ou fechada do seu github.
- **Estrela:** Retorna o repositorio mais recente que o usuario adicionou aos favoritos.
- **Pull Request:** Retorna o status e o repositorio da pull request mais recente aberta.

# Requisitos

- É necessario ter o  [java](https://www.java.com/download/ie_manual.jsp) instalado em sua maquina para rodar o
  programa e tambem o [maven](https://maven.apache.org/install.html).

# Instalação

- Clone o repositório

```
  git clone https://github.com/pedro-henrique1/GitHub-User-Activity.git
  cd GitHub-User-Activity
```

- Instalar Dependência:

```
  mvn compile
```

- Executar o programa:

``` 
  mvn exec:java -Dexec.mainClass="com.task.Main"
```
# Uso
Execute o programa e coloque o username do github

```
 github-activity kamranahmedse
```


# Exemplo

```shell
# Commit mais recente
Pushed 30 commits to kamranahmedse/developer-roadmap

# Issue mais recente
Opened a new issue in kamranahmedse/developer-roadmap

# Repositorio com estrela
Starred arikchakma/nanohost

# Último pull request
Last Pull request em kamranahmedse/developer-roadmap status of closed

```
