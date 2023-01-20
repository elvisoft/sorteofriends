package com.example.sorteofriends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sorteofriends.ItemsPlayers
import java.nio.file.Files.size

class CustomAdapter(private val listaplayers: MutableList<ItemsPlayers>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var removedPosition : Int=0
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var itemImage: ImageView
    var itemTitle: TextView
    var butondelete: ImageButton
        init {
            itemImage=itemView.findViewById(R.id.item_image)
            itemTitle=itemView.findViewById(R.id.item_title)
            butondelete=itemView.findViewById(R.id.item_imgdelete)
        }
    }
    override fun onCreateViewHolder(viewgroup: ViewGroup, i: Int): ViewHolder {
        val v=LayoutInflater.from(viewgroup.context).inflate(R.layout.card_layout,viewgroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, i: Int) {
        val currentItem = listaplayers[i]
        viewholder.itemImage.setImageResource(currentItem.imageResource)
        viewholder.itemTitle.text = currentItem.text1
        viewholder.butondelete.setOnClickListener(View.OnClickListener {
            listaplayers.removeAt(i)
            //adapter.notifyItemRemoved(index)
            removedPosition = i
            notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int {
        return listaplayers.size
    }
    fun getPositionremoved():Int{
        return removedPosition
    }

}