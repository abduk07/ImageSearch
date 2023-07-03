package kg.varis.imagesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kg.varis.imagesearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val adapter = PixaAdapter()
    private lateinit var binding: ActivityMainBinding
    private var page: Int = 1
    private var loadPage = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }

    private fun initData() {
        with(binding) {
            imageRecyclerView.adapter = adapter
            imageRecyclerView.addOnScrollListener(object :
                PaginationScrollListener(imageRecyclerView.layoutManager as LinearLayoutManager) {
                override fun loadItems() {
                    page++
                    searchImage()
                }

                override fun lastPage() = false
                override fun isLoading() = loadPage
            })

            btnChangePage.setOnClickListener {
                page++
                searchImage()
            }

            btnSearch.setOnClickListener {
                page = 1
                searchImage()
            }

        }
    }

    private fun searchImage() {
        with(binding) {
            loadPage = true
            RetrofitService.api.searchImage(etSearch.text.toString(), page = page)
                .enqueue(object : Callback<PixaModel> {
                    override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                        loadPage = false
                        if (response.isSuccessful) {
                            adapter.addImages(response.body()?.hits!!)
                        }
                    }

                    override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                        loadPage = false
                    }
                })
        }
    }
}



