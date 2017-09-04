package com.danielebottillo.learning.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.danielebottillo.learning.R
import com.danielebottillo.learning.interactor.JokeInteractor
import com.danielebottillo.learning.model.Joke
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

class BottomFragment : Fragment() {

    val interactor: JokeInteractor by lazy {
        JokeInteractor()
    }

    val text: TextView by lazy { view!!.findViewById<TextView>(R.id.joke) }

    var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.joke_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view?.findViewById<Button>(R.id.another)?.setOnClickListener {
            interactor.getJoke()
        }

        view?.findViewById<Button>(R.id.stop)?.setOnClickListener {
            disposable?.dispose()
        }

        view?.findViewById<Button>(R.id.start)?.setOnClickListener {
            subscribe()
        }

        view?.findViewById<Button>(R.id.error)?.setOnClickListener {
            interactor.generateError()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribe()
    }

    fun subscribe() {
        disposable = interactor.subscribe().subscribe({
            text.setTextColor(resources.getColor(android.R.color.black))
            text.text = it.text
        }, {
            text.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            text.text = it.localizedMessage
        })
    }

}