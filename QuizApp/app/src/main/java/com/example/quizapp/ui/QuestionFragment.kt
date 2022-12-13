package com.example.quizapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.data.model.MultipleQuestion
import com.example.quizapp.data.model.Question
import com.example.quizapp.databinding.FragmentQuestionBinding
import com.example.quizapp.model.QuizViewModel


class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: QuizViewModel by activityViewModels()

    private lateinit var currentQuestion: Question

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            viewModel = sharedViewModel
            questionFragment = this@QuestionFragment
            next.setOnClickListener{ onClickNext() }
        }

        sharedViewModel.questionIndex.observe(viewLifecycleOwner) {
            currentQuestion = sharedViewModel.question
            updateNewQuestionUi()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Next - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private fun onClickNext() {
        if (!sharedViewModel.nextQuestion()){
            val action = QuestionFragmentDirections.actionQuestionFragmentToQuizResultFragment()
            findNavController().navigate(action)
        }
    }

    // UpdateUI - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private fun updateNewQuestionUi() {
        restoreDefaultQuestionUi()
        updateQuestionUi()
    }

    private fun restoreDefaultQuestionUi(){
        setAnswersButtonsClickable(true)
        setButtonsDefaultColor()
        setNextButtonVisible(false)
        setAnswerResponseTextView(null)
    }

    private fun updateQuestionUi() {
        updateQuestionDetailUi()
        updateQuestionAnswersUi()
    }

    private fun setButtonsDefaultColor() {
        val color = ContextCompat.getColor(requireContext(), R.color.purple_500) // TODO get from Theme

        binding.apply {
            answer1.setBackgroundColor(color)
            answer2.setBackgroundColor(color)
            answer3.setBackgroundColor(color)
            answer4.setBackgroundColor(color)
            answerTrue.setBackgroundColor(color)
            answerFalse.setBackgroundColor(color)
        }
    }

    private fun setNextButtonVisible(visible: Boolean) {
        if (visible) {
            binding.next.visibility = View.VISIBLE
        } else {
            binding.next.visibility = View.INVISIBLE
        }
    }


    private fun updateQuestionDetailUi() {
        binding.apply {
            questionCount.text = getString(
                R.string.question_count,
                sharedViewModel.questionIndex.value!! + 1,
                sharedViewModel.quiz.getNumberOfQuestions()
            )
            question.text = currentQuestion.question
        }
    }

    private fun updateQuestionAnswersUi() {
        if (currentQuestion.isMultipleQuestion) {
            setMultipleQuestionLayoutVisible()
            updateMultipleQuestionAnswers()
        } else {
            setBooleanQuestionLayoutVisible()
        }
    }

    private fun setMultipleQuestionLayoutVisible (visible: Boolean = true) {
        if (visible) {
            binding.multipleAnswerLayout.visibility = View.VISIBLE
            binding.booleanAnswerLayout.visibility = View.GONE
        } else {
            setBooleanQuestionLayoutVisible()
        }
    }

    private fun setBooleanQuestionLayoutVisible (visible: Boolean = true) {
        if (visible) {
            binding.multipleAnswerLayout.visibility = View.GONE
            binding.booleanAnswerLayout.visibility = View.VISIBLE
        } else {
            setMultipleQuestionLayoutVisible()
        }
    }

    private fun updateMultipleQuestionAnswers(){
        var currentMultipleQuestion = currentQuestion as MultipleQuestion

        binding.apply {
            answer1.text = currentMultipleQuestion.answerList[0].answer
            answer2.text = currentMultipleQuestion.answerList[1].answer
            answer3.text = currentMultipleQuestion.answerList[2].answer
            answer4.text = currentMultipleQuestion.answerList[3].answer
        }
    }

    // Submit Answer - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    fun onSubmitAnswer(selectedAnswerIndex: Int) {
        val isCorrect = sharedViewModel.isUserAnswerCorrect(selectedAnswerIndex)
        setAnswersButtonsClickable(false)
        setAnswerButtonsColor(selectedAnswerIndex, isCorrect)
        setNextButtonVisible(true)
        setAnswerResponseTextView(isCorrect)
    }

    private fun setAnswerResponseTextView(correct: Boolean?) {

        when (correct) {
            true -> {
                binding.answerResponse.visibility = View.VISIBLE
                binding.answerResponse.text = getString(R.string.correct_answer_response)
                binding.answerResponse.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_correct, null), null, null, null)
            }
            false -> {
                binding.answerResponse.visibility = View.VISIBLE
                binding.answerResponse.text = getString(R.string.wrong_answer_response)
                binding.answerResponse.setCompoundDrawablesWithIntrinsicBounds(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_wrong, null), null, null, null)
            }
            else -> {
                binding.answerResponse.visibility = View.INVISIBLE
            } // TODO make invisible
        }
    }

    private fun setAnswerButtonsColor(selectedAnswerIndex:Int, isCorrect: Boolean){
        setButtonAnswerColor(sharedViewModel.question.correctAnswerIndex, true)
        if (!isCorrect){
            setButtonAnswerColor(selectedAnswerIndex, false)
        }
    }

    private fun setButtonAnswerColor(answerIndex: Int, correct: Boolean) {
        val colorRes = if (correct) R.color.green else R.color.red
        val color = ContextCompat.getColor(requireContext(), colorRes) // TODO get from Theme

        val button : Button = if (sharedViewModel.question.isMultipleQuestion) {
            when (answerIndex) {
                0 -> binding.answer1
                1 -> binding.answer2
                2 -> binding.answer3
                else -> binding.answer4
            }
        } else {
            when (answerIndex) {
                0 -> binding.answerTrue
                else -> binding.answerFalse
            }
        }
        button.setBackgroundColor(color)
    }

    private fun setAnswersButtonsClickable(clickable: Boolean) {
        binding.apply {
            answer1.isClickable = clickable
            answer2.isClickable = clickable
            answer3.isClickable = clickable
            answer4.isClickable = clickable
            answerTrue.isClickable = clickable
            answerFalse.isClickable = clickable
        }

    }
}