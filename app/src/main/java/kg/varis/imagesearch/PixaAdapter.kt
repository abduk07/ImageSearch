package kg.varis.imagesearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import kg.varis.imagesearch.databinding.ItemImageBinding

class PixaAdapter : Adapter<PixaAdapter.PixaViewHolder>() {

    private var arrayList = ArrayList<ImageModel>()

    fun addImages(list:List<ImageModel>){
        arrayList.clear()
        arrayList.addAll(0,list)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(arrayList[position])
    }

    class PixaViewHolder( val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.ivItemImage.load(model.largeImageURL)
        }

    }
}