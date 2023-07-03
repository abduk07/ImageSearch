package kg.varis.imagesearch

data class PixaModel(
    val hits: ArrayList<ImageModel>
)

data class ImageModel(
    val largeImageURL: String,
    var id: Int
) : java.io.Serializable
