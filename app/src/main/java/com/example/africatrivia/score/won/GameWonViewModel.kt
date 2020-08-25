package com.example.africatrivia.score.won

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameWonViewModel : ViewModel() {
    //The final score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score


//    init {
//        _score.value = finalScore
//    }
}