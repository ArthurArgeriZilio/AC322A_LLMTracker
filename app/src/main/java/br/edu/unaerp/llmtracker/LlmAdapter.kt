package br.edu.unaerp.llmtracker

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.edu.unaerp.llmtracker.databinding.ItemLlmBinding
import br.edu.unaerp.llmtracker.model.Llm

class LlmAdapter(
    private val aoClicar: (Llm) -> Unit
) : RecyclerView.Adapter<LlmAdapter.ViewHolder>() {

    private val itens = mutableListOf<Llm>()

    private val coresAvatar = intArrayOf(
        R.color.avatar_azul,
        R.color.avatar_verde,
        R.color.avatar_roxo,
        R.color.avatar_laranja,
        R.color.avatar_rosa
    )

    fun atualizar(novos: List<Llm>) {
        itens.clear()
        itens.addAll(novos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLlmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itens[position])
    }

    override fun getItemCount(): Int = itens.size

    inner class ViewHolder(
        private val binding: ItemLlmBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(llm: Llm) {
            val contexto = binding.root.context

            binding.nome.text = llm.nome
            binding.provedor.text = llm.provedor
            binding.avaliacao.text = if (llm.avaliacao == null) {
                contexto.getString(R.string.sem_avaliacao)
            } else {
                contexto.getString(R.string.nota_lista, llm.avaliacao)
            }

            val cor = coresAvatar[kotlin.math.abs(llm.id) % coresAvatar.size]
            binding.icone.background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(ContextCompat.getColor(contexto, cor))
            }

            if (llm.favorito) {
                binding.estrela.setImageResource(R.drawable.ic_estrela)
                binding.estrela.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(contexto, R.color.estrela)
                )
                binding.estrela.contentDescription = contexto.getString(R.string.cd_favorito)
            } else {
                binding.estrela.setImageResource(R.drawable.ic_estrela_vazia)
                binding.estrela.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(contexto, R.color.texto_secundario)
                )
                binding.estrela.contentDescription = contexto.getString(R.string.cd_nao_favorito)
            }

            binding.root.setOnClickListener { aoClicar(llm) }
        }
    }
}
