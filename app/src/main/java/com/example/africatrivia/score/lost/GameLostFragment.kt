package com.example.africatrivia.score.lost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.africatrivia.R
import com.example.africatrivia.databinding.GameLostFragmentBinding
import com.example.africatrivia.title.TitleFragmentDirections
import kotlinx.android.synthetic.main.game_won_fragment.*

class GameLostFragment : Fragment() {
    lateinit var binding: GameLostFragmentBinding
    var earnedScore: String = ""
    var wrongAnswerText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.game_lost_fragment, container, false)

        binding.btnTryAgain.setOnClickListener {
            playAgain()
        }

        binding.gamelost = this
        return binding.root
    }

    private fun playAgain() {
        val action = GameLostFragmentDirections.actionGameLostFragmentToTitleFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = GameLostFragmentArgs.fromBundle(it)
            earnedScore = args.score.toString()
            if (args.wrongAnswers.isNotEmpty()) {
                yourwronganswer.visibility = View.VISIBLE
                //txt_wrong_answer.visibility = View.VISIBLE

                for (ans in args.wrongAnswers) {
                    wrongAnswerText = wrongAnswerText + ans + "\n"
                }
            }
        }
    }
}