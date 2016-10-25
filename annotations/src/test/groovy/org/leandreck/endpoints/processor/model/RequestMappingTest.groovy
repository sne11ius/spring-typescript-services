/**
 * Copyright © 2016 Mathias Kowalzik (Mathias.Kowalzik@leandreck.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.leandreck.endpoints.processor.model

import org.springframework.web.bind.annotation.RequestMethod
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by kowalzik on 21.10.2016.
 */
class RequestMappingTest extends Specification {

    @Unroll
    def "Method is always an Array #methods"() {
        given: "a RequestMapping"
        def reqMapping = new RequestMapping(methods, null, null)

        when: "method is called"
        def retVal = reqMapping.method()

        then: "retVal should be a Collection"
        retVal instanceof RequestMethod[]

        where: "possible values for methods are"
        methods << [[RequestMethod.GET], null]
    }

    @Unroll
    def "Produces is always an Array #produces"() {
        given: "a RequestMapping"
        def reqMapping = new RequestMapping(null, produces, null)

        when: "method is called"
        def retVal = reqMapping.produces()

        then: "retVal should be a Collection"
        retVal instanceof String[]

        where: "possible values for produces are"
        produces << [["some"], null]
    }

    @Unroll
    def "Value is always an Array #value"() {
        given: "a RequestMapping"
        def reqMapping = new RequestMapping(null, null, value)

        when: "method is called"
        def retVal = reqMapping.value()

        then: "retVal should be a Collection"
        retVal instanceof String[]

        where: "possible values for produces are"
        value << [["some"], null]
    }
}