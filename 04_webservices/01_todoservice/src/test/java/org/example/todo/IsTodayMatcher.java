package org.example.todo;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class IsTodayMatcher extends TypeSafeMatcher<Date> {

	@Override
	protected boolean matchesSafely(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(LocalDate.now());
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("is today");
	}
}
