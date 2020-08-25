package com.example.africatrivia.game

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.africatrivia.Question
import com.example.africatrivia.R
import com.example.africatrivia.databinding.GameFragmentBinding
import com.example.africatrivia.game.GameFragmentDirections.actionGameFragmentToGameWonFragment
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {

    lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Observer for the Game finished event
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })

        //binding.endGameButton.setOnClickListener { onEndGame() }

        return binding.root
    }

    private fun onEndGame() {
        gameFinished()
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        //if statement here - if score >= goto GameWon else goto GameLost
        val action = GameFragmentDirections.actionGameFragmentToGameWonFragment()
        //action.score = viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
    }

//    private fun gameFinished() {
//        val score = viewModel.score
//        val wrongAnswerList = viewModel.wrongAnswerList
//        val highestScore = sharedPreferences.getInt("HIGHESTSCORE", 0)
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
//                wrongAnswerList.toTypedArray()
//            )
//            view?.findNavController()?.navigate(action)
//        } else {
////            Navigation.findNavController(requireView()).navigate(R.id.action_quizGameFragment_to_quizLostFragment)
//            val action = GameFragmentDirections.actionGameFragmentToGameLostFragment(
//                score,
//                wrongAnswerList.toTypedArray()
//            )
//            view?.findNavController()?.navigate(action)
//        }
//    }


}