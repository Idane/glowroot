/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.jvm;

import java.lang.reflect.Method;

import org.checkerframework.checker.nullness.qual.Nullable;

import org.glowroot.common.Reflections;
import org.glowroot.common.Reflections.ReflectiveException;
import org.glowroot.markers.Static;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Trask Stalnaker
 * @since 0.5
 */
@Static
public class ClassLoaders {

    private ClassLoaders() {}

    public static Class<?> defineClass(String name, byte[] bytes, @Nullable ClassLoader loader)
            throws ReflectiveException {
        if (loader == null) {
            // this can be handled if needed, by using a private ClassLoader to generate classes
            // and using interfaces to access the generated classes (since then the generated
            // classes will be in a child or sibling class loader and will no longer be directly
            // accessible)
            throw new AssertionError("Glowroot was loaded by JVM bootstrap class loader");
        }
        Method defineClassMethod = Reflections.getDeclaredMethod(ClassLoader.class, "defineClass",
                String.class, byte[].class, int.class, int.class);
        Class<?> definedClass = (Class<?>) Reflections.invoke(defineClassMethod, loader, name,
                bytes, 0, bytes.length);
        checkNotNull(definedClass);
        return definedClass;
    }
}
