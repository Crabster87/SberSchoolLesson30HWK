package crabster.rudakov.sberschoollesson30hwk.presentation.recyclerview.gallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import crabster.rudakov.sberschoollesson30hwk.databinding.ItemImageBinding

class ImageViewHolder(private val binding: ItemImageBinding, private val pickImage: (Uri) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(uri: Uri) {
        Glide.with(itemView)
            .load(uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.galleryImage)
        itemView.setOnClickListener {
            pickImage.invoke(uri)
        }
    }

    companion object {
        fun create(parent: ViewGroup, pickImage: (Uri) -> Unit): ImageViewHolder {
            val binding =
                ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ImageViewHolder(binding, pickImage)
        }
    }

}
