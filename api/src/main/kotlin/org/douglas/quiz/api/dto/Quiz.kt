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

package org.douglas.quiz.api.dto

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import java.util.*
import kotlin.collections.ArrayList

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Quiz(val id: String = ""
           , val name: String
           , val description: String
           , val createdDate: Date = Date()
           , val lastModifiedDate: Date = Date()
           , val questions: List<Question>
) {
    constructor() : this(name = "", description = "", questions = ArrayList())
}