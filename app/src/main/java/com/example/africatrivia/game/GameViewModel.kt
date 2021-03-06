package com.example.africatrivia.game

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.africatrivia.Question

class GameViewModel : ViewModel() {


    lateinit var sharedPreferences: SharedPreferences

    val maxNumberOfQuestions = 4


    lateinit var selectedAnswer: String

    //ja
//    private val _wrongAnswerList = ArrayList<String>
//    val wrongAnswerList: ArrayList<String>
//    get() = _wrongAnswerList

    //ja
    //The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //ja
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    var questions = arrayListOf<Question>(
        Question(
            "Which is the Independence day of Bangladesh?",
            arrayListOf("26 March", "21 Feb", "14th April", "16 December")
        ),
        Question(
            "Who is the first man landed on moon?",
            arrayListOf("Neil Armstrong", "Edwin Aldrin", "Michael Collins", "Yuri Gregory")
        ),
        Question(
            "Socrates is best known for - ",
            arrayListOf("Philosophy", "Mathmetics", "Physiology", "Astrology")
        ),
        Question(
            "How many states does USA have? ",
            arrayListOf("50", "45", "55", "49")
        ),
        Question(
            "Which is not an Europian Country? ",
            arrayListOf("Combodia", "Estonia", "Lithunia", "Moldova")
        ),
        Question(
            "Who is the first President of USA? ",
            arrayListOf(
                "George Washington",
                "William Henry Harrison",
                "Abraham Lincoln",
                "Franklin D. Roosevelt"
            )
        ),
        Question(
            "Which one is the largest ocean? ",
            arrayListOf("Pacific", "Atlantic", "Mediterian", "Arctic")
        ),
        Question(
            "What country has a town named Marathon? ",
            arrayListOf("USA", "GREECE", "ITALY", "FRANCE")
        ),
        Question(
            "What well-known mountain pass connects Pakistan and Afghanistan? ",
            arrayListOf("Khyber Pass", "Malakand Pass", "Ahmad Pass", "Shandar Pass")
        ),
        Question(
            "What country was formerly known as Ceylon?",
            arrayListOf("Sri Lanka", "Sweden", "Vietnam", "Switzerland")
        )
    )

    lateinit var currentQuestion: Question
    //lateinit var answers: ArrayList<String>
    //ja
    lateinit var _answers: MutableList<String>


    private var questionIndex = 0
    //ja
    val numQuestions = Math.min((questions.size + 1) / 2, 3)

    init {
        randomQuestion()
        _score.value = 0
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        //answers = ArrayList(currentQuestion.theAnswer)
        //ja
        _answers = currentQuestion.theAnswer.toMutableList()
        _answers.shuffle()
        //(activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_africa_trivia_question, questionIndex + 1, numQuestions)
        Log.d("ANSWERGROUP", _answers[0] + " " + _answers[1] + " " + _answers[2] + " " + _answers[3])
        Log.d("ANSWERREAL", currentQuestion.theAnswer[0])
    }

//    private fun checkAnswer(answer: String) {
//        if (answer.equals(currentQuestion.theAnswer[0])) {
//            score += 1
//        } else {
//            this._wrongAnswerList.add(currentQuestion.theQuestion)
//        }
//        questionIndex++
//        if (questionIndex < maxNumberOfQuestions) {
//            setQuestion()
//            binding.invalidateAll()
//        } else {
//            getScore()
//        }
//    }

//    private fun getScore() {
//        var highestScore = sharedPreferences.getInt("HIGHESTSCORE", 0)
//        if (score > highestScore) {
//            val editor = sharedPreferences.edit()
//            editor.putInt("HIGHESTSCORE", score)
//            editor.apply()
//        }
//        if (score >= 3) {
////            view?.let {
////                Navigation.findNavController(it).navigate(R.id.action_quizGameFragment_to_quizWonFragment)
////            }
//            val action = GameFragmentDirections.actionGameFragmentToGameWonFragment(
//                score,
//                this._wrongAnswerList.toTypedArray()
//            )
//            view?.findNavController()?.navigate(action)
//        } else {
////            Navigation.findNavController(requireView()).navigate(R.id.action_quizGameFragment_to_quizLostFragment)
//            val action = GameFragmentDirections.actionGameFragmentToGameLostFragment(
//                score,
//                this._wrongAnswerList.toTypedArray()
//            )
//            view?.findNavController()?.navigate(action)
//        }
//    }

    private fun randomQuestion() {
        questions.shuffle()
        setQuestion()
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        option1.setOnClickListener {
//            checkAnswer(option1.text.toString())
//        }
//        option2.setOnClickListener {
//            checkAnswer(option2.text.toString())
//        }
//        option3.setOnClickListener {
//            checkAnswer(option3.text.toString())
//        }
//        option4.setOnClickListener {
//            checkAnswer(option4.text.toString())
//        }
//
//        sharedPreferences = requireActivity().getSharedPreferences(
//            "SP_HIGHEST_SCORE",
//            Context.MODE_PRIVATE
//        )
//    }

    //ja
    //note getScore == onGameFinish
//    fun getScore() {
//        _eventGameFinish.value = true
//    }

    //ja
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

}
