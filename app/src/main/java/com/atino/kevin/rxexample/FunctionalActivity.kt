package com.atino.kevin.rxexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_functional.*


class FunctionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functional)

        FunctionalExample
                .declarativeList()
                .forEach({ functional_declarative.append(" " + it) })

        FunctionalExample
                .imperativeList()
                .forEach({ functional_imperative.append(" " + it) })

        FunctionalExample
                .observableBlockingList()
                .forEach({ functional_reactive_blocking.append(" " + it) })

        FunctionalExample
                .observableList()
                .subscribe({ functional_reactive.append(" " + it) })
    }
}
