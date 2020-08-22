package com.example.africatrivia.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.africatrivia.R
import com.example.africatrivia.databinding.TitleFragmentBinding
import com.example.africatrivia.game.GameFragmentDirections
import com.example.africatrivia.title.TitleFragmentDirections.actionTitleFragmentToGameFragment
import kotlinx.android.synthetic.main.title_fragment.view.*

class TitleFragment : Fragment() {

    lateinit var binding: TitleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false)
        binding.btnPlay.setOnClickListener {
            findNavController().navigate(actionTitleFragmentToGameFragment())
        }

        return binding.root
    }

//    private fun playGame() {
//        val action = TitleFragmentDirections.actionTitleFragmentToGameFragment()
//        NavHostFragment.findNavController(this).navigate(action)
//    }

}