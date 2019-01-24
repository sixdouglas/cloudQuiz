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

package org.douglas.quiz.api.controller

import org.douglas.quiz.api.service.QuizService
import org.douglas.quiz.api.dto.Quiz
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.net.URI

@RestController
@RequestMapping("/quizzes")
class QuizController(val quizService: QuizService) {
    @GetMapping(value = arrayOf("/"), produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun getAll() : Flux<org.douglas.quiz.repository.model.Quiz> = quizService.findAll()

    @PostMapping(value = arrayOf("/"))
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody quiz: Quiz): Mono<org.douglas.quiz.repository.model.Quiz> {
        return quizService.save(quiz)
    }

    @DeleteMapping(value = arrayOf("/:quizId"))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove(@PathVariable("quizId") quizId: String) = quizService.deleteById(quizId)

    @PutMapping(value = arrayOf("/:quizId"))
    @ResponseStatus(HttpStatus.OK)
    fun update(@RequestBody quiz: Quiz) : Mono<ServerResponse> {
        return quizService.save(quiz)
                .flatMap { updatedQuiz -> ok().body(BodyInserters.fromObject(URI.create("/quizzes/" + updatedQuiz.id))) }
    }
}