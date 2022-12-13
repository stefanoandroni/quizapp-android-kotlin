package com.example.quizapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.data.Datasource
import com.example.quizapp.data.model.Quiz

class QuizViewModel : ViewModel() {

    // Dataset
    var dataset: List<Quiz> = Datasource().loadQuizzes()

    // Quiz
    lateinit var quiz: Quiz

    // Question
    private val _questionIndex = MutableLiveData(-1)
    val questionIndex: LiveData<Int>
        get() = _questionIndex

    val question get() = quiz.questionList[_questionIndex.value!!]

    // Score
    private var _score = 0
    val score get() = _score

    fun isUserAnswerCorrect(answerIndex: Int): Boolean {
        val isCorrect = question.checkAnswer(answerIndex)
        if (isCorrect) { increaseScore() }
        return isCorrect
    }

    fun nextQuestion(): Boolean {
        if (_questionIndex.value!! < quiz.getNumberOfQuestions() - 1) {
            getNextQuestion()
            return true
        }
        return false
    }

    fun startQuiz() {
        _questionIndex.value = 0
        _score = 0
    }

    fun setQuizById(quizId: Int) {
        quiz = dataset.first { it.id == quizId }
    }

    private fun increaseScore() {
        _score += 1
    }

    private fun getNextQuestion() {
        _questionIndex.value = _questionIndex.value?.plus(1)
    }

}