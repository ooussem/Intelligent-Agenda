package com.iaproject.miage.intelligentagenda.activity.authentification;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.iaproject.miage.intelligentagenda.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by OOussema on 20/03/2017.
 */
public class LoginActivityTest {
	@Rule
	public IntentsTestRule<LoginActivity> loginActivityRule = new IntentsTestRule<>(LoginActivity.class);
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void tesLogIn() {
		onView(withId(R.id.editTextEmail)).perform(typeText("oussema@oussema.com"), closeSoftKeyboard());
		onView(withId(R.id.editTextPassword)).perform(typeText("totototo"), closeSoftKeyboard());
		onView(withId(R.id.buttonSignin)).perform(click());
	}

	@After
	public void tearDown() throws Exception {
		loginActivityRule=null;
	}

}