package crabster.rudakov.sberschoollesson30hwk.presentation.recyclerview.gallery

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for gallery pictures
 */
class ImagesAdapter(private val pickImage: (Uri) -> Unit) :
    RecyclerView.Adapter<ImageViewHolder>() {
    private var imagesList: List<Uri> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setGalleryImages(imagesList: List<Uri>) {
        this.imagesList = imagesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent, pickImage)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imagesList.get(position))
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

}