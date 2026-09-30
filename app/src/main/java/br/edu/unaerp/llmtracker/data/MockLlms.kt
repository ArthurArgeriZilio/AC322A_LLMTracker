package br.edu.unaerp.llmtracker.data

import br.edu.unaerp.llmtracker.model.Llm

/**
 * Lista fixa só pra parcial. Não tem API nem banco: o que mudar de
 * favorito ou anotação fica na memória e some quando o processo morre.
 */
object MockLlms {

    private val itens = mutableListOf(
        Llm(
            id = 1,
            nome = "GPT-4o",
            provedor = "OpenAI",
            resumo = "Uso bastante pra código, resumo de texto e dúvida de aula. Responde rápido e entende bem o que eu peço.",
            notas = "Fica melhor quando peço o passo a passo.",
            avaliacao = 4.8,
            favorito = true,
            lancamento = "2024"
        ),
        Llm(
            id = 2,
            nome = "Claude",
            provedor = "Anthropic",
            resumo = "Bom pra explicar um código que eu não escrevi e pra revisar texto longo sem cortar o começo.",
            notas = null,
            avaliacao = 4.7,
            favorito = true,
            lancamento = "2025"
        ),
        Llm(
            id = 3,
            nome = "Gemini",
            provedor = "Google",
            resumo = "Testei no celular e mandando imagem. A resposta vem rápido, mas às vezes inventa um link.",
            notas = "Conferi a resposta de Android antes de confiar.",
            avaliacao = 4.4,
            favorito = false,
            lancamento = "2025"
        ),
        Llm(
            id = 4,
            nome = "Llama",
            provedor = "Meta",
            resumo = "Modelo aberto. Quero rodar local um dia, por enquanto só li sobre e testei numa demo.",
            notas = "Ainda não coloquei uma nota.",
            avaliacao = null,
            favorito = false,
            lancamento = "2024"
        ),
        Llm(
            id = 5,
            nome = "Mistral",
            provedor = "Mistral AI",
            resumo = "Mais leve e direto. Usei num teste de API na aula e a resposta veio curta, do jeito que eu pedi.",
            notas = null,
            avaliacao = 4.1,
            favorito = false,
            lancamento = "2024"
        ),
        Llm(
            id = 6,
            nome = "DeepSeek",
            provedor = "DeepSeek",
            resumo = "Forte em matemática e em raciocínio. Demora um pouco mais, mas a resposta costuma vir boa.",
            notas = "Usei pra revisar um exercício de lógica.",
            avaliacao = 4.6,
            favorito = true,
            lancamento = "2025"
        ),
        Llm(
            id = 7,
            nome = "Grok",
            provedor = "xAI",
            resumo = "Acompanho mais por curiosidade. Ainda não usei pra nada da faculdade.",
            notas = null,
            avaliacao = null,
            favorito = false,
            lancamento = null
        ),
        Llm(
            id = 8,
            nome = "Qwen",
            provedor = "Alibaba",
            resumo = "Vi gente usando pra código. Entende português mesmo com a interface em inglês.",
            notas = "Testei pouco, só umas perguntas soltas.",
            avaliacao = 4.0,
            favorito = false,
            lancamento = "2025"
        )
    )

    fun listar(): List<Llm> = itens.toList()

    fun buscar(id: Int): Llm? = itens.find { it.id == id }

    fun atualizar(llm: Llm) {
        val indice = itens.indexOfFirst { it.id == llm.id }
        if (indice >= 0) {
            itens[indice] = llm
        }
    }
}
