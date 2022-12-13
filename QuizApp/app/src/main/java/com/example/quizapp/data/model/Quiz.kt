package com.example.quizapp.data.model

class Quiz(
    val id: Int,
    val title: String,
    val category: Category,
    val difficulty: Difficulty,
    val description: String,
    val imageResourceId: Int
) {
    private val _questionList = mutableListOf<Question>()
    val questionList get() = _questionList

    fun addQuestion(newQuestion: Question): Quiz {
        _questionList.add(newQuestion)
        return this
    }

    fun addAllQuestions(newQuestions: List<Question>): Quiz {
        _questionList.addAll(newQuestions)
        return this
    }

    fun getNumberOfQuestions(): Int {
        return _questionList.size
    }

    fun isValid(): Boolean {
        return _questionList.all { it.isValid() } && getNumberOfQuestions() > 0
    }

}

enum class Difficulty {
    EASY, MEDIUM, HARD
}

enum class Category {
    SPORT, HISTORY, GENERAL, GEOGRAPHY
}