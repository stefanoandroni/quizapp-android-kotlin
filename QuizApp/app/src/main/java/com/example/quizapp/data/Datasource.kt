package com.example.quizapp.data

import com.example.quizapp.R
import com.example.quizapp.data.model.*

class Datasource {
    fun loadQuizzes(): List<Quiz> {
        var list = mutableListOf<Quiz>()

        list.addAll(
            listOf(
                Quiz(
                    1,
                    "Random general questions",
                    Category.GENERAL,
                    Difficulty.MEDIUM,
                    "Just a random general knowledge quiz.",
                    R.drawable.quiz_1
                ).addAllQuestions(
                    listOf(
                        MultipleQuestion("In Roman Numerals, what does XL equate to")
                            .addAllAnswers(
                                listOf(
                                    Answer("40"),
                                    Answer("60"),
                                    Answer("15"),
                                    Answer("90")
                                )
                            ).setCorrectAnswer(0),
                        MultipleQuestion("Which computer hardware device provides an interface for all other connected devices to communicate?")
                            .addAllAnswers(
                                listOf(
                                    Answer("Central Processing Unit"),
                                    Answer("Motherboard"),
                                    Answer("Hard Disk Drive"),
                                    Answer("Random Access Memory")
                                )
                            ).setCorrectAnswer(1),
                        MultipleQuestion("What is the area of Vatican City?")
                            .addAllAnswers(
                                listOf(
                                    Answer("0.10km^2"),
                                    Answer("0.86km^2"),
                                    Answer("0.44km^2"),
                                    Answer("12.00km^2")
                                )
                            ).setCorrectAnswer(2),
                        MultipleQuestion("Which restaurant's mascot is a clown?")
                            .addAnswer(Answer("McDonald's"))
                            .addAnswer(Answer("Whataburger"))
                            .addAnswer(Answer("Burger King"))
                            .addAnswer(Answer("Sonic"))
                            .setCorrectAnswer(0)
                    )
                ),
                Quiz(
                    2,
                    "Football Rules",
                    Category.SPORT,
                    Difficulty.EASY,
                    "How much do you know about the beautiful game? Test your soccer knowledge with this awesome quiz!",
                    R.drawable.quiz_2
                ).addAllQuestions(
                    listOf(
                        BooleanQuestion(
                            "Is it against the rules to use your hands to control the ball in football",
                            false
                        ),
                        MultipleQuestion("What is the maximum number of substitutions a team is allowed to make in a single football match?")
                            .addAllAnswers(
                                listOf(
                                    Answer("3"),
                                    Answer("4"),
                                    Answer("5"),
                                    Answer("6"),
                                )
                            ).setCorrectAnswer(0),
                        MultipleQuestion("Which of the following is not allowed in football?")
                            .addAllAnswers(
                                listOf(
                                    Answer("Kicking the ball to your teammates"),
                                    Answer("Tackling an opponent"),
                                    Answer("Biting an opponent"),
                                    Answer("Heading the ball"),
                                )
                            ).setCorrectAnswer(2),
                        BooleanQuestion(
                            "Is it against the rules to score a goal directly from a corner kick",
                            false
                        ),
                        MultipleQuestion("Which of the following offenses is not a direct free kick?")
                            .addAllAnswers(
                                listOf(
                                    Answer("Tripping an opponent"),
                                    Answer("Holding an opponent"),
                                    Answer("Handling the ball"),
                                    Answer("Dissent"),
                                )
                            ).setCorrectAnswer(3),
                        MultipleQuestion("How many players from each team are allowed on the field at the same time?")
                            .addAllAnswers(
                                listOf(
                                    Answer("8"),
                                    Answer("9"),
                                    Answer("10"),
                                    Answer("11"),
                                )
                            ).setCorrectAnswer(3),
                    )
                ),
                Quiz(
                    4,
                    "Geography",
                    Category.GEOGRAPHY,
                    Difficulty.EASY,
                    "Test your knowledge about Geography.",
                    R.drawable.quiz_4
                ).addAllQuestions(
                    listOf(
                        BooleanQuestion(
                            "Is the Amazon River the longest river in the world?",
                            false
                        ),
                        MultipleQuestion("What is the capital city of Canada?")
                            .addAllAnswers(
                                listOf(
                                    Answer("Toronto"),
                                    Answer("Montreal"),
                                    Answer("Vancouver"),
                                    Answer("Ottawa")
                                )
                            ).setCorrectAnswer(3),
                        MultipleQuestion("Which of the following countries is not a member of the European Union?")
                            .addAllAnswers(
                                listOf(
                                    Answer("France"),
                                    Answer("Germany"),
                                    Answer("Sweden"),
                                    Answer("Norway")
                                )
                            ).setCorrectAnswer(3),
                        MultipleQuestion("What is the highest mountain peak in the world?")
                            .addAllAnswers(
                                listOf(
                                    Answer("Mount Everest"),
                                    Answer("K2"),
                                    Answer("Kangchenjunga"),
                                    Answer("Lhotse")
                                )
                            ).setCorrectAnswer(0),
                        BooleanQuestion("Is the Pacific Ocean the largest ocean on Earth?", true),
                        MultipleQuestion("Which of the following countries is not a member of the G8?")
                            .addAllAnswers(
                                listOf(
                                    Answer("United States"),
                                    Answer("United Kingdom"),
                                    Answer("France"),
                                    Answer("India")
                                )
                            ).setCorrectAnswer(3),
                    )
                ),
                Quiz(
                    5,
                    "History quiz",
                    Category.HISTORY,
                    Difficulty.MEDIUM,
                    "Learn history while having fun with quizzes",
                    R.drawable.quiz_5
                )
                    .addAllQuestions(
                        listOf(
                            MultipleQuestion("What year did World War II begin?")
                                .addAllAnswers(
                                    listOf(
                                        Answer("1914"),
                                        Answer("1939"),
                                        Answer("1917"),
                                        Answer("1945")
                                    )
                                ).setCorrectAnswer(1),
                            BooleanQuestion("Did the Roman Empire rule over Egypt?", true),
                            MultipleQuestion("What year did the American Civil War end?")
                                .addAllAnswers(
                                    listOf(
                                        Answer("1860"),
                                        Answer("1861"),
                                        Answer("1865"),
                                        Answer("1870")
                                    )
                                ).setCorrectAnswer(2),
                            MultipleQuestion("Which of the following is not one of the Five Good Emperors of ancient Rome?")
                                .addAllAnswers(
                                    listOf(
                                        Answer("Nerva"),
                                        Answer("Trajan"),
                                        Answer("Hadrian"),
                                        Answer("Julian")
                                    )
                                ).setCorrectAnswer(3),
                            BooleanQuestion(
                                "Did the United States become an independent country in 1776?",
                                true
                            )
                        )
                    ),
            )
        )

        return list
    }
}