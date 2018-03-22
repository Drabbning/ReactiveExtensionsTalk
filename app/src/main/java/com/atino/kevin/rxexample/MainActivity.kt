package com.atino.kevin.rxexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        bindViews()
    }

    private fun bindViews() {
        main_async_button.setOnClickListener({ startActivity(Intent(this, AsyncTaskActivity::class.java)) })

        main_runnable_button.setOnClickListener({ startActivity(Intent(this, RunnableActivity::class.java)) })

        main_rx_button.setOnClickListener({ startActivity(Intent(this, RxJavaActivity::class.java)) })
        
        main_functional_button.setOnClickListener({ startActivity(Intent(this, FunctionalActivity::class.java)) })
    }
}
