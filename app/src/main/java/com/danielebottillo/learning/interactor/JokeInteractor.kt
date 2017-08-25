package com.danielebottillo.learning.interactor

import com.danielebottillo.learning.model.Joke
import com.danielebottillo.learning.repository.JokeRepository
import io.reactivex.functions.Consumer

class JokeInteractor {

    fun getJoke() {
        JokeRepository.instance.getJoke()
    }

    fun subscribe(consumer: Consumer<Joke>) {
        JokeRepository.instance.getSubject().subscribe(consumer)
    }
}