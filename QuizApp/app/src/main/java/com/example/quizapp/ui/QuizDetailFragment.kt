package com.example.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.FragmentQuizDetailBinding
import com.example.quizapp.model.QuizViewModel
import com.example.quizapp.util.QUIZ_ID_KEY

class QuizDetailFragment : Fragment() {
    private var _binding: FragmentQuizDetailBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: QuizViewModel by activityViewModels()

    private var quizId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quizId = it.getInt(QUIZ_ID_KEY)
        }
        sharedViewModel.setQuizById(quizId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            quizDetailFragment = this@QuizDetailFragment
            viewModel = sharedViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startQuiz(quizId: Int) {
        sharedViewModel.startQuiz()
        val action = QuizDetailFragmentDirections.actionQuizDetailFragmentToQuestionFragment()
        findNavController().navigate(action)
    }

}