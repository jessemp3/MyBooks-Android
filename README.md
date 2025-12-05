# MyBooks-Android

Aplicativo Android (Java) para gerenciar e visualizar sua coleção de livros de forma simples e prática.

## Visão geral

MyBooks-Android permite organizar uma biblioteca pessoal, visualizar detalhes dos livros e marcar favoritos. O projeto está em desenvolvimento; a inclusão de novos livros já é feita pela interface do aplicativo (UI).

## Funcionalidades

- Cadastro de livros pela UI do aplicativo (formulário no app)
- Visualização de detalhes do livro (título, autor, descrição)
- Tela de favoritos
- Organização da biblioteca pessoal (filtros e categorias planejados)
- Persistência local com Room

## Tecnologias utilizadas

- Android SDK
- Java
- Room
- Material Components (UI)
- Gradle

## Instalação e execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/jessemp3/MyBooks-Android.git
   ```
2. Abra o projeto no Android Studio.
3. Sincronize o Gradle e deixe o Android Studio baixar as dependências.
4. Compile e execute em um dispositivo físico ou emulador Android:
   - Selecione um dispositivo/emulador e rode Run (Play).
5. (Opcional) Se houver um APK disponível, você pode instalá-lo diretamente no dispositivo.

Dicas:
- Use um emulador com API compatível (verifique minSdkVersion no build.gradle).
- Caso precise importar dados externos, adicione uma tela/fluxo no app ou ferramentas de importação conforme necessário.

## Como contribuir

Contribuições são bem-vindas! Sugestões:
- Abra uma issue descrevendo o que deseja adicionar ou corrigir.
- Crie uma branch com um nome descritivo (ex.: feature/add-book-form).
- Faça commits pequenos e descritivos.
- Abra uma Pull Request explicando as mudanças.

Posso abrir a PR com este README atualizado se desejar.

## Estrutura (resumo)

- app/ - código fonte do aplicativo Android
- build.gradle - configurações do projeto
- README.md - este arquivo
- LICENSE - licença do projeto

(Observação: a estrutura pode variar à medida que o projeto evolui; faça uma busca no repositório se precisar de caminhos específicos.)

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Autor

Desenvolvido por [jessemp3](https://github.com/jessemp3).

## Últimas atualizações

- b2f1e1d — Revise README for MyBooks-Android project — 2025-09-27T17:56:27Z  
  (Atualizado nome do projeto e adicionados detalhes, funcionalidades, tecnologias e instruções.)
- 3cf7c26 — feat: add README file and update VCS mappings for project directory — 2025-09-27T17:53:42Z
