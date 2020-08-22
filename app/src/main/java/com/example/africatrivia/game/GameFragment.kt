package com.example.africatrivia.game

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.africatrivia.Question
import com.example.africatrivia.R
import com.example.africatrivia.databinding.GameFragmentBinding
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {

    lateinit var binding: GameFragmentBinding
    lateinit var currentQuestion: Question
    lateinit var sharedPreferences: SharedPreferences
    private var questionIndex = 0
    val maxNumberOfQuestions = 4

    lateinit var answers: ArrayList<String>
    lateinit var selectedAnswer: String
    var wrongAnswerList: ArrayList<String> = ArrayList()
    var score: Int = 0

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

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = ArrayList(currentQuestion.theAnswer)
        answers.shuffle()
        Log.d("ANSWERGROUP", answers[0] + " " + answers[1] + " " + answers[2] + " " + answers[3])
        Log.d("ANSWERREAL", currentQuestion.theAnswer[0])
    }

    private fun checkAnswer(answer: String) {
        if (answer.equals(currentQuestion.theAnswer[0])) {
            score += 1
        } else {
            wrongAnswerList.add(currentQuestion.theQuestion)
        }
        questionIndex++
        if (questionIndex < maxNumberOfQuestions) {
            setQuestion()
            binding.invalidateAll()
        } else {
            getScore()
        }
    }

    private fun getScore() {
        var highestScore = sharedPreferences.getInt("HIGHESTSCORE", 0)
        if (score > highestScore) {
            val editor = sharedPreferences.edit()
            editor.putInt("HIGHESTSCORE", score)
            editor.apply()
        }
        if (score >= 3) {
//            view?.let {
//                Navigation.findNavController(it).navigate(R.id.action_quizGameFragment_to_quizWonFragment)
//            }
            val action = GameFragmentDirections.actionGameFragmentToGameWonFragment(
                score,
                wrongAnswerList.toTypedArray()
            )
            view?.findNavController()?.navigate(action)
        } else {
//            Navigation.findNavController(requireView()).navigate(R.id.action_quizGameFragment_to_quizLostFragment)
            val action = GameFragmentDirections.actionGameFragmentToGameLostFragment(
                score,
                wrongAnswerList.toTypedArray()
            )
            view?.findNavController()?.navigate(action)
        }
    }

    private fun randomQuestion() {
        questions.shuffle()
        setQuestion()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        randomQuestion()
        binding.quiz = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        option1.setOnClickListener {
            checkAnswer(option1.text.toString())
        }
        option2.setOnClickListener {
            checkAnswer(option2.text.toString())
        }
        option3.setOnClickListener {
            checkAnswer(option3.text.toString())
        }
        option4.setOnClickListener {
            checkAnswer(option4.text.toString())
        }

        sharedPreferences = requireActivity().getSharedPreferences("SP_HIGHEST_SCORE",
            Context.MODE_PRIVATE
        )
    }

}