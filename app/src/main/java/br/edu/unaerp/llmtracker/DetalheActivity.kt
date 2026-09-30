package br.edu.unaerp.llmtracker

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.unaerp.llmtracker.data.MockLlms
import br.edu.unaerp.llmtracker.databinding.ActivityDetalheBinding
import br.edu.unaerp.llmtracker.databinding.ChipRotuloBinding
import br.edu.unaerp.llmtracker.model.Llm

class DetalheActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NOME = "extra_nome"
    }

    private lateinit var binding: ActivityDetalheBinding
    private var llmId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)
        aplicarInsets()

        binding.toolbar.setNavigationOnClickListener { finish() }

        llmId = intent.getIntExtra(EXTRA_ID, -1)
        val nomeRecebido = intent.getStringExtra(EXTRA_NOME)
        binding.toolbar.title = nomeRecebido ?: getString(R.string.detalhe_titulo)

        val llm = MockLlms.buscar(llmId)
        if (llm == null) {
            mostrarNaoEncontrado(nomeRecebido)
            return
        }

        mostrar(llm, preencherCampo = true)

        binding.botaoSalvarNota.setOnClickListener {
            val atual = MockLlms.buscar(llmId) ?: return@setOnClickListener
            val texto = binding.campoNotas.text?.toString()?.trim().orEmpty()
            val notas: String? = texto.ifEmpty { null }
            val atualizado = atual.copy(notas = notas)
            MockLlms.atualizar(atualizado)
            mostrar(atualizado, preencherCampo = false)
            binding.mensagem.text = getString(R.string.nota_salva)
            binding.mensagem.visibility = View.VISIBLE
        }

        binding.botaoFavorito.setOnClickListener {
            val atual = MockLlms.buscar(llmId) ?: return@setOnClickListener
            val atualizado = atual.copy(favorito = !atual.favorito)
            MockLlms.atualizar(atualizado)
            mostrar(atualizado, preencherCampo = false)
            binding.mensagem.text = getString(
                if (atualizado.favorito) R.string.virou_favorito else R.string.deixou_favorito
            )
            binding.mensagem.visibility = View.VISIBLE
        }
    }

    private fun mostrar(llm: Llm, preencherCampo: Boolean) {
        binding.toolbar.title = llm.nome
        binding.nome.text = llm.nome
        binding.provedor.text = llm.provedor
        binding.resumo.text = llm.resumo

        binding.avaliacao.text = if (llm.avaliacao == null) {
            getString(R.string.sem_avaliacao)
        } else {
            getString(R.string.nota_formatada, llm.avaliacao)
        }

        binding.lancamento.text = llm.lancamento ?: getString(R.string.sem_lancamento)
        binding.notas.text = llm.notas ?: getString(R.string.sem_notas)
        if (preencherCampo) {
            binding.campoNotas.setText(llm.notas.orEmpty())
        }

        if (llm.favorito) {
            binding.botaoFavorito.setText(R.string.remover_favorito)
            binding.botaoFavorito.setIconResource(R.drawable.ic_estrela)
        } else {
            binding.botaoFavorito.setText(R.string.marcar_favorito)
            binding.botaoFavorito.setIconResource(R.drawable.ic_estrela_vazia)
        }

        preencherChips(llm)
    }

    private fun preencherChips(llm: Llm) {
        binding.containerChips.removeAllViews()
        adicionarChip(llm.provedor)
        adicionarChip(
            getString(if (llm.favorito) R.string.chip_favorito else R.string.chip_acompanhando)
        )
        if (llm.avaliacao != null) {
            adicionarChip(getString(R.string.nota_lista, llm.avaliacao))
        }
    }

    private fun adicionarChip(texto: String) {
        val chip = ChipRotuloBinding.inflate(layoutInflater, binding.containerChips, true)
        chip.rotulo.text = texto
    }

    private fun mostrarNaoEncontrado(nome: String?) {
        binding.conteudo.visibility = View.GONE
        binding.erro.visibility = View.VISIBLE
        binding.erro.text = if (nome.isNullOrBlank()) {
            getString(R.string.nao_encontrado)
        } else {
            getString(R.string.nao_encontrado_nome, nome)
        }
    }

    private fun aplicarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }
    }
}
