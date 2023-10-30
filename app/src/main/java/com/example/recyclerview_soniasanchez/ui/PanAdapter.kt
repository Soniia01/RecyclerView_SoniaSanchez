package com.example.recyclerview_soniasanchez.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_soniasanchez.R
import com.example.recyclerview_soniasanchez.databinding.ItemPanBinding
import com.example.recyclerview_soniasanchez.domain.modelo.Pan

class PanAdapter(
    private var pan: List<Pan>,
    private val onClickButton: (Pan) -> Unit
) : RecyclerView.Adapter<PanViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanViewHolder {
        val layoutInflater = LayoutInflater. from(parent.context)
        return PanViewHolder(layoutInflater.inflate(R.layout.item_pan, parent, false))
    }

    override fun onBindViewHolder(holder: PanViewHolder, position: Int) {
        holder.render(pan[position], onClickButton)
    }
    fun cambiarLista(lista: List<Pan>) {
        pan = lista
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = pan.size
}
class PanViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemPanBinding.bind(view)
    fun render(pan: Pan, onClickButton: (Pan) -> Unit){
        with(binding) {
            tvNombre.text = pan.nombre
            detalle.setOnClickListener {
                onClickButton(pan)
            }
        }
    }


}