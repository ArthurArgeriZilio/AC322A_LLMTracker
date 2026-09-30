package br.edu.unaerp.llmtracker

import br.edu.unaerp.llmtracker.model.Llm
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LlmTest {

    @Test
    fun camposOpcionaisPodemFicarNulos() {
        val grok = Llm(
            id = 7,
            nome = "Grok",
            provedor = "xAI",
            resumo = "Acompanho por curiosidade.",
            notas = null,
            avaliacao = null,
            favorito = false,
            lancamento = null
        )

        assertNull(grok.notas)
        assertNull(grok.avaliacao)
        assertNull(grok.lancamento)
    }

    @Test
    fun favoritarGeraOutraCopia() {
        val original = Llm(
            id = 1,
            nome = "GPT-4o",
            provedor = "OpenAI",
            resumo = "Uso no dia a dia.",
            notas = "Bom em código",
            avaliacao = 4.8,
            favorito = false,
            lancamento = "2024"
        )

        val favorito = original.copy(favorito = true)

        assertFalse(original.favorito)
        assertTrue(favorito.favorito)
        assertEquals(original.nome, favorito.nome)
        assertEquals(original.notas, favorito.notas)
    }
}
