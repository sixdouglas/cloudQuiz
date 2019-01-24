/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.douglas.quiz.api.service

import org.douglas.quiz.repository.model.Question
import org.douglas.quiz.repository.model.Quiz
import org.douglas.quiz.repository.repository.QuizRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
typealias QuizDto = org.douglas.quiz.api.dto.Quiz
typealias QuestionDto = org.douglas.quiz.repository.model.Question

@Service
class QuizService(val quizRepository: QuizRepository) {
    fun findAll(): Flux<Quiz> {
        return quizRepository.findAll()
    }

    fun save(quizDto: QuizDto): Mono<Quiz> {
        val questions = mutableListOf<Question>()
        for (dtoQuestion in quizDto.questions){
            questions.add(Question(dtoQuestion.id, dtoQuestion.questionText, dtoQuestion.multipleAnswers))
        }
        val quiz = Quiz(name = quizDto.name, description = quizDto.description, questions = questions, createdDate = null, lastModifiedDate = null)
        return quizRepository.save(quiz)
    }

    fun deleteById(quizId: String): Mono<Void> {
        return quizRepository.deleteById(quizId)
    }

}