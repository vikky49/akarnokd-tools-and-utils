/*
 * Copyright 2012-2014 David Karnok
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

package hu.akarnokd.utils.lang;

/**
 * A three parameter + exception functional interface.
 * @author akarnokd, 2013.11.01.
 * @param <T1> the first parameter
 * @param <T2> the second parameter
 * @param <T3> the third parameter
 * @param <E> the exception class
 */
public interface Action3E<T1, T2, T3, E extends Exception> {
	/**
	 * Invokes the action.
	 * @param t the first parameter
	 * @param u the second parameter
	 * @param v the third parameter
	 * @throws E the expection
	 */
	void invoke(T1 t, T2 u, T3 v) throws E;
}
