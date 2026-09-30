# LLM Tracker

Aplicativo da parcial de Programação Mobile I (UNAERP, AC322A).

A ideia é acompanhar os modelos de linguagem (LLMs) que eu uso ou quero testar. Cada modelo tem nome, provedor, um resumo, anotação (opcional), avaliação (opcional) e se está nos favoritos.

A primeira tela lista os modelos. Toque em um deles para abrir o detalhe. Dá para marcar ou tirar dos favoritos e salvar uma anotação. A lista atualiza a estrela e o contador de favoritos quando você volta.

Os dados são simulados, direto no código (`MockLlms`). Não tem API nem banco. Favorito e anotação ficam só enquanto o app está aberto.

## Como rodar no Android Studio

1. Instale o Android Studio usado na disciplina (com Android Gradle Plugin 9).
2. Clone este repositório e abra a pasta do projeto em **File → Open**. Não abra só a pasta `app`.
3. Espere o Gradle sincronizar. Se o Android Studio pedir para baixar o SDK 37 ou o build-tools, aceite.
4. Crie um emulador com API 24 ou mais nova, ou ligue um celular com a depuração USB ativada.
5. Selecione a configuração **app** e aperte **Run**.

Não precisa de chave, senha ou arquivo `.env`. O projeto já abre na lista de modelos.

## Bibliotecas

Não usei biblioteca de fora do AndroidX / Material. Tudo abaixo já vem no projeto Android padrão:

- **AndroidX AppCompat** — `AppCompatActivity` e compatibilidade da tela.
- **Material Components** — toolbar, card, campo de texto e botões no visual do Material 3.
- **AndroidX RecyclerView** — lista de modelos da tela principal.
- **AndroidX Activity** — `enableEdgeToEdge()`, para o conteúdo não ficar atrás da barra do sistema.
- **ViewBinding** — recurso do Android Gradle Plugin (não é uma dependência à parte). Liga o XML ao Kotlin sem `findViewById`.
