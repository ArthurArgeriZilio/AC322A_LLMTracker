package br.edu.unaerp.llmtracker.model

/**
 * LLM que eu acompanho. A data class é imutável: pra mudar favorito ou
 * anotação eu uso copy() e guardo a cópia nova na lista mock.
 *
 * notas, avaliacao e lancamento são opcionais de propósito — nem todo
 * modelo já tem anotação, nota ou data que eu lembre.
 */
data class Llm(
    val id: Int,
    val nome: String,
    val provedor: String,
    val resumo: String,
    val notas: String?,
    val avaliacao: Double?,
    val favorito: Boolean,
    val lancamento: String?
)
