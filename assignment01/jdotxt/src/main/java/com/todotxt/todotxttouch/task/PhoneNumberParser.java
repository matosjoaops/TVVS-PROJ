/**
 * This file is part of Todo.txt Touch, an Android app for managing your todo.txt file (http://todotxt.com).
 *
 * Copyright (c) 2009-2013 Todo.txt contributors (http://todotxt.com)
 *
 * LICENSE:
 *
 * Todo.txt Touch is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any
 * later version.
 *
 * Todo.txt Touch is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with Todo.txt Touch.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * @author Todo.txt contributors <todotxt@yahoogroups.com>
 * @license http://www.gnu.org/licenses/gpl.html
 * @copyright 2009-2013 Todo.txt contributors (http://todotxt.com)
 */
package com.todotxt.todotxttouch.task;

import java.util.Collections;
import java.util.List;

public class PhoneNumberParser {
	// private static final Pattern NUMBER_PATTERN = android.util.Patterns.
	private static final PhoneNumberParser INSTANCE = new PhoneNumberParser();

	private PhoneNumberParser() {
	}

	public static PhoneNumberParser getInstance() {
		return INSTANCE;
	}

	public List<String> parse(String inputText) {
		/*if (inputText == null) {
			return Collections.emptyList();
		} 
		
		// Only run the phone number parser if Android version is not Honeycomb
		// API level 11 - 13
		int sdk = Build.VERSION.SDK_INT;
		if (sdk >= 11 && sdk <= 13) {
			return Collections.emptyList();
		}

		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		Iterable<PhoneNumberMatch> numbersMatch = phoneUtil.findNumbers(
				inputText, Locale.getDefault().getCountry());
		ArrayList<String> numbers = new ArrayList<String>();
		for (PhoneNumberMatch number : numbersMatch) {
			numbers.add(phoneUtil.format(number.number(),
					PhoneNumberFormat.NATIONAL));

		}

		return numbers;*/
		return Collections.emptyList();
	}
}
