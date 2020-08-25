package com.example.africatrivia.score.won

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.africatrivia.R
import com.example.africatrivia.databinding.GameFragmentBinding
import com.example.africatrivia.databinding.GameWonFragmentBinding
import com.example.africatrivia.title.TitleFragmentDirections.actionTitleFragmentToGameFragment
import kotlinx.android.synthetic.main.game_lost_fragment.*

class GameWonFragment : Fragment() {

    private lateinit var viewModel: GameWonViewModel
    //private lateinit var viewModelFactory: GameWonViewModelFactory

    lateinit var binding: GameWonFragmentBinding
    var earnedScore: String = ""
    var wrongAnswerText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: GameWonFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.game_won_fragment, container, false)

        //viewModelFactory = GameWonViewModelFactory(GameWonFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this)
            .get(GameWonViewModel::class.java)
        binding.gameWonViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnPlayNext.setOnClickListener {
            val action = GameWonFragmentDirections.actionGameWonFragmentToTitleFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        arguments?.let{
//            val args = GameWonFragmentArgs.fromBundle(it)
//            earnedScore = args.score.toString()
//            if (args.wrongAnswers.isNotEmpty()){
//                yourwronganswer.visibility = View.VISIBLE
//                txt_wrong_answer.visibility = View.VISIBLE
//
//                for (ans in args.wrongAnswers){
//                    wrongAnswerText = wrongAnswerText + ans + "\n"
//                }
//            }
//        }
//    }
}