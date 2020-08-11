package com.jack.sample.pixabay.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jack.sample.pixabay.R
import com.jack.sample.pixabay.home.data.repository.MediumRepository
import com.jack.sample.pixabay.home.ui.adapter.MediumPagedAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    private val adapter = MediumPagedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
    }

    private fun initView() {
        MediumRepository().getImageList().observe(this, Observer {
            adapter.submitList(it)
        })

        list_medium.apply {
            adapter = this@HomeActivity.adapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}