package com.danielebottillo.learning.interactor

import com.danielebottillo.learning.model.Joke
import com.danielebottillo.learning.repository.JokeRepository
import io.reactivex.subjects.Subject

class JokeInteractor {

    fun getJoke() {
        JokeRepository.instance.getJoke()
    }

    fun subscribe(): Subject<Joke> {
        return JokeRepository.instance.getSubject()
    }

    fun generateError() {
        JokeRepository.instance.generateError()
    }
}