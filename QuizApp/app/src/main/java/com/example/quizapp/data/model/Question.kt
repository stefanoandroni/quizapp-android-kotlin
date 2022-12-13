package com.example.quizapp.data.model


abstract class Question(
    val question: String
) {
    val isMultipleQuestion get() = this::class == MultipleQuestion::class
    val isBooleanQuestion get() = this::class == BooleanQuestion::class
    abstract var correctAnswerIndex: Int

    abstract fun checkAnswer(answerIndex: Int): Boolean

    open fun isValid(): Boolean {
        return correctAnswerIndex != -1
    }
}

class BooleanQuestion(
    question: String,
    private val correctAnswer: Boolean
) : Question(question) {

    override var correctAnswerIndex: Int = -1
        get() = if (correctAnswer) 0 else 1

    override fun checkAnswer(answerIndex: Int): Boolean {
        return (correctAnswer && answerIndex == 0) || (!correctAnswer && answerIndex == 1)
    }
}

class MultipleQuestion(question: String) : Question(question) {
    val answerList = mutableListOf<Answer>()
    private val numberOfQuestions = 4

    override var correctAnswerIndex: Int = -1

    override fun isValid(): Boolean {
        return super.isValid() && numberOfQuestions == answerList.size
    }

    override fun checkAnswer(answerIndex: Int): Boolean {
        return correctAnswerIndex == answerIndex
    }

    fun addAnswer(newAnswer: Answer): MultipleQuestion {
        if (answerList.size < numberOfQuestions) {
            answerList.add(newAnswer)
        }
        return this
    }

    fun addAllAnswers(newAnswers: List<Answer>): MultipleQuestion {
        if (answerList.size + newAnswers.size <= numberOfQuestions) {
            answerList.addAll(newAnswers)
        }
        return this
    }

    fun setCorrectAnswer(answerIndex: Int): MultipleQuestion {
        correctAnswerIndex = answerIndex
        return this
    }
}