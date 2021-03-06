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
package org.leandreck.endpoints.processor.model;

import java.util.*;

/**
 * Created by Mathias Kowalzik (Mathias.Kowalzik@leandreck.org) on 27.08.2016.
 */
public class MethodNode {

    private final String name;
    private final String url;
    private final boolean ignored;
    private final TypeNode returnType;
    private final TypeNode requestBodyType;
    private final List<TypeNode> pathVariableTypes;
    private final List<String> httpMethods;
    private final Set<TypeNode> types;

    public MethodNode(final String name, final String url, final boolean ignored, final List<String> httpMethods, final TypeNode returnType) {
        this.name = name;
        this.url = url;
        this.ignored = ignored;
        this.returnType = returnType;
        this.httpMethods = httpMethods;
        requestBodyType = null;
        pathVariableTypes = Collections.emptyList();
        this.types = collectTypes();
    }

    public MethodNode(final String name, final String url, final boolean ignored, final List<String> httpMethods,
                      final TypeNode returnType, final TypeNode requestBodyType, final List<TypeNode> pathVariableTypes) {
        this.name = name;
        this.ignored = ignored;
        this.url = url;
        this.returnType = returnType;
        this.httpMethods = httpMethods;
        this.requestBodyType = requestBodyType;
        this.pathVariableTypes = pathVariableTypes;
        this.types = collectTypes();
    }

    private Set<TypeNode> collectTypes() {
        final Map<String, TypeNode> typeMap = new HashMap<>();
        if (returnType != null) {
            typeMap.put(returnType.getTypeName(), returnType);
        }
        if (requestBodyType != null) {
            typeMap.put(requestBodyType.getTypeName(), requestBodyType);
        }
        return new HashSet<>(typeMap.values());
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    public List<String> getHttpMethods() {
        return Collections.unmodifiableList(httpMethods);
    }

    public String getName() {
        return name;
    }

    public boolean isIgnored() {
        return ignored;
    }

    public String getUrl() {
        return url;
    }

    public TypeNode getRequestBodyType() {
        return requestBodyType;
    }

    public Set<TypeNode> getTypes() {
        return Collections.unmodifiableSet(types);
    }

    public List<TypeNode> getPathVariableTypes() {
        return Collections.unmodifiableList(pathVariableTypes);
    }
}
