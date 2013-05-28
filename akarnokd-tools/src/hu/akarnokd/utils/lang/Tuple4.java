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

package hu.akarnokd.utils.lang;

import java.util.Objects;

import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * A simple three-valued record.
 * @author akarnokd, 2013.05.28.
 * @param <A> the first element type
 * @param <B> the second element type
 * @param <C> the third element type
 * @param <D> the fourth element type
 */
public final class Tuple4<A, B, C, D> {
	/** The first element. */
	public final A a;
	/** The second element. */
	public final B b;
	/** The third element. */
	public final C c;
	/** The fourth element. */
	public final D d;
	/**
	 * Constructs this tuple.
	 * @param a the first element
	 * @param b the second element
	 * @param c the third element
	 * @param d the fourth element
	 */
	private Tuple4(A a, B b, C c, D d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	/**
	 * Constructs a tuple.
	 * @param <A> the first element type
	 * @param <B> the second element type
	 * @param <C> the third element type
	 * @param <D> the fourth element type
	 * @param a the first value
	 * @param b the second value
	 * @param c the third value
	 * @param d the fourth value
	 * @return the tuple
	 */
	@NonNull
	public static <A, B, C, D> Tuple4<A, B, C, D> of(A a, B b, C c, D d) {
		return new Tuple4<>(a, b, c, d);
	}
	@Override
	public String toString() {
		return "(" + a + ", " + b + ", " + c + ", " + d + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Tuple4<?, ?, ?, ?> other = (Tuple4<?, ?, ?, ?>)obj;
		return Objects.equals(a, other.a)
				&& Objects.equals(b, other.b)
				&& Objects.equals(c, other.c)
				&& Objects.equals(d, other.d)
				;
	}
	@Override
	public int hashCode() {
		int h = 17;
		h = h * 31 + (a != null ? a.hashCode() : 0);
		h = h * 31 + (b != null ? b.hashCode() : 0);
		h = h * 31 + (c != null ? c.hashCode() : 0);
		h = h * 31 + (d != null ? d.hashCode() : 0);
		return h;
	}
}
