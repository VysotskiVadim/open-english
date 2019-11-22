package io.github.freeenglish.questions

data class Question(
    val id: Long,
    val question: String,
    val answers: List<Answer>,
    val correctAnswerId: Long
)

data class Answer(
    val id: Long,
    val value: String
)

interface AskUserUseCase {
    suspend fun askQuestion(): Question
    suspend fun checkAnswer(isAnswerRight: Boolean): Boolean
}

class AskUserUseCaseImplementation(
    private val questionsDao: QuestionsDao
) : AskUserUseCase {
    override suspend fun askQuestion(): Question {
        val word = questionsDao.getWordWithDefinitions()
        return Question(
            id = 1,
            question = word.word.value,
            answers = listOf(
                Answer(1, word.definitions[0].meaning),
                Answer(2, "wrong")
            ),
            correctAnswerId = word.definitions[0].id
        )
    }

    override suspend fun checkAnswer(isAnswerRight: Boolean): Boolean {
        return isAnswerRight
    }
}