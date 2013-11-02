/*
 * Copyright 2012-2013 David Karnok
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

package hu.akarnokd.utils.generator;

import hu.akarnokd.reactive4java.base.Func0;
import hu.akarnokd.reactive4java.base.Func1;
import hu.akarnokd.reactive4java.base.Func2;

import java.util.concurrent.atomic.AtomicInteger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * Utility class to engineer certain classes.
 * @author akarnokd, 2013.11.02.
 */
public final class CodeCreator {
	/** Utility class. */
	private CodeCreator() { }
	/**
	 * Creates a function which creates a new instance of the class
	 * without reflection.
	 * @param <T> the type
	 * @param clazz the class
	 * @return the function
	 */
	@SuppressWarnings("unchecked")
	public static <T> Func0<T> createConstructor(Class<T> clazz) {
		try {
			clazz.getConstructor();
			
			ClassPool pool = ClassPool.getDefault();

			String classname = clazz.getName() + "$Constructor0";
			
			// generate once
			if (pool.getOrNull(classname) != null) {
				return (Func0<T>)Class.forName(classname).newInstance();
			}

			CtClass c = pool.makeClass(classname);
			c.setModifiers(Modifier.FINAL);
			c.setModifiers(Modifier.PUBLIC);
			c.addInterface(pool.get(Func0.class.getName()));
			
			StringBuilder b = new StringBuilder();
			b.append("\r\npublic final Object invoke() {\r\n");
			b.append("\treturn new ").append(clazz.getName()).append("();\r\n");
			b.append("}\r\n");
			
//			LOG.debug(b.toString());

			c.addMethod(CtMethod.make(b.toString(), c));
		
			return (Func0<T>)c.toClass().newInstance();
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	/**
	 * The class identifier used for creating unique constructor class names.
	 */
	private static final AtomicInteger CLASS_ID = new AtomicInteger();
	/**
	 * Creates a function which creates a new instance of the class
	 * without reflection.
	 * @param <T> the type
	 * @param <U> the first parameter type
	 * @param clazz the class
	 * @param param1 the first parameter type
	 * @return the function
	 */
	@SuppressWarnings("unchecked")
	public static <T, U> Func1<U, T> createConstructor(Class<T> clazz, Class<U> param1) {
		try {
			clazz.getConstructor(param1);
			
			ClassPool pool = ClassPool.getDefault();
			
			CtClass c = pool.makeClass(clazz.getName() + "$Constructor" + CLASS_ID.incrementAndGet());
			c.setModifiers(Modifier.FINAL);
			c.setModifiers(Modifier.PUBLIC);
			c.addInterface(pool.get(Func1.class.getName()));
			
			StringBuilder b = new StringBuilder();
			b.append("\r\npublic final Object invoke(Object p1) {\r\n");
			b.append("\treturn new ").append(clazz.getName()).append("(");
			b.append("(").append(param1.getName()).append(")p1");
			b.append(");\r\n");
			b.append("}\r\n");
			
//			LOG.debug(b.toString());

			c.addMethod(CtMethod.make(b.toString(), c));
		
			return (Func1<U, T>)c.toClass().newInstance();
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}
	/**
	 * Creates a function which creates a new instance of the class
	 * without reflection.
	 * @param <T> the type
	 * @param <U> the first parameter type
	 * @param <V> the second parameter type
	 * @param clazz the class
	 * @param param1 the first parameter type
	 * @param param2 the second parameter type
	 * @return the function
	 */
	@SuppressWarnings("unchecked")
	public static <T, U, V> Func2<U, V, T> createConstructor(Class<T> clazz, Class<U> param1, Class<V> param2) {
		try {
			clazz.getConstructor(param1);
			
			ClassPool pool = ClassPool.getDefault();
			
			CtClass c = pool.makeClass(clazz.getName() + "$Constructor" + CLASS_ID.incrementAndGet());
			c.setModifiers(Modifier.FINAL);
			c.setModifiers(Modifier.PUBLIC);
			c.addInterface(pool.get(Func2.class.getName()));
			
			StringBuilder b = new StringBuilder();
			b.append("\r\npublic final Object invoke(Object p1, Object p2) {\r\n");
			b.append("\treturn new ").append(clazz.getName()).append("(");
			b.append("(").append(param1.getName()).append(")p1");
			b.append(", (").append(param1.getName()).append(")p2");
			b.append(");\r\n");
			b.append("}\r\n");
			
//			LOG.debug(b.toString());

			c.addMethod(CtMethod.make(b.toString(), c));
		
			return (Func2<U, V, T>)c.toClass().newInstance();
		} catch (Exception ex) {
			throw new IllegalArgumentException(ex);
		}
	}
}
