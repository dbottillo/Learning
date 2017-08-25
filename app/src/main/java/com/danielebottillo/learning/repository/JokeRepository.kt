package com.danielebottillo.learning.repository

import com.danielebottillo.learning.client.RestClient
import com.danielebottillo.learning.model.Joke
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


class JokeRepository private constructor(val restClient: RestClient = RestClient()) {

    private object Holder {
        val INSTANCE = JokeRepository()
    }

    companion object {
        val instance: JokeRepository by lazy { Holder.INSTANCE }
    }

    var mObservable: Subject<Joke> = PublishSubject.create<Joke>()

    var lastJoke: Joke? = null
    var lastRequest: Long = -1

    fun getJoke() {
        lastJoke?.let {
            if (System.currentTimeMillis() <= lastRequest + 5 * 1000) {
                mObservable.onNext(it.copy(text = "FROM CACHE: " + it.text))
                return
            }
        }
        lastRequest = -1

        restClient.random().map { Joke(it.value.text) }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mObservable)
    }

    fun getSubject(): Subject<Joke> {
        return mObservable
    }

    fun doSomething(){

    }
}
