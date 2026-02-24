package com.example.flixsterplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import okhttp3.internal.http2.ErrorCode
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private  lateinit var moviesRecyclerView: RecyclerView
    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        moviesRecyclerView = findViewById(R.id.moviesRecyclerView)

        val adapter = MovieAdapter(movies)
        moviesRecyclerView.adapter = adapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                val results: JSONArray = json.jsonObject.getJSONArray("results")

                for (i in 0 until results.length()) {
                    movies.add(Movie(results.getJSONObject(i)))
                }

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                throwable?.printStackTrace()
            }
        } )

    }
}