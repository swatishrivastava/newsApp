package com.android.newsapp.headlines.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.newsapp.utils.HEADLINE_URL
import com.android.newsapp.R
import kotlinx.android.synthetic.main.activity_headlines_details.show_headlines_details


class HeadlinesDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headlines_details)
        val url = intent.extras?.getString(HEADLINE_URL)
        url?.let {
            show_headlines_details.loadUrl(url)
        }
    }
}