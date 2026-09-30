package br.edu.unaerp.llmtracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.unaerp.llmtracker.data.MockLlms
import br.edu.unaerp.llmtracker.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding
    private lateinit var adapter: LlmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        aplicarInsets()

        adapter = LlmAdapter { llm ->
            val intent = Intent(this, DetalheActivity::class.java)
            intent.putExtra(DetalheActivity.EXTRA_ID, llm.id)
            intent.putExtra(DetalheActivity.EXTRA_NOME, llm.nome)
            startActivity(intent)
        }

        binding.lista.layoutManager = LinearLayoutManager(this)
        binding.lista.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val lista = MockLlms.listar()
        adapter.atualizar(lista)
        binding.contador.text = getString(
            R.string.contador,
            lista.size,
            lista.count { it.favorito }
        )
    }

    private fun aplicarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }
    }
}
