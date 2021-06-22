package com.kuldeep.makwana.home.adapter

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 22-06-2021.
 * PS: Not easy to write code, please indicate.
 */
import android.R.attr.data
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.kuldeep.makwana.R
import com.kuldeep.makwana.home.model.ImageModel
import kotlinx.android.synthetic.main.item_list_layout.view.*


class ImageAdapter(
    arrayList: ArrayList<ImageModel>,
    context: Context,
    private val listener: ImageAdapterClickListener
) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var ctx: Context? = context
    private var listSettingsList: List<ImageModel> = arrayList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageModel: ImageModel = listSettingsList[position]
        holder.bindItems(position, imageModel, listener)
    }

    override fun getItemCount(): Int {
        return listSettingsList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(
            position: Int,
            imageModel: ImageModel,
            listenerImage: ImageAdapterClickListener
        ) {


            val clView = itemView.mainCard
            val uri = Uri.parse(Uri.parse(imageModel.thumbnailUrl).toString())
            with(clView) {
                Glide.with(itemView.context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(imgIcon)
                txtTitle.text = imageModel.title
            }
            clView.setOnClickListener {
                listenerImage.onSelectImage(position, imageModel)
            }

        }
    }

    interface ImageAdapterClickListener {
        fun onSelectImage(
            position: Int,
            imageModel: ImageModel
        )
    }
}