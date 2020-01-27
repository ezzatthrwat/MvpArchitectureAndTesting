package com.example.e_commericemvp.ui;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.e_commericemvp.Activities.registration.ui.Login_activity.LoginActivity;
import com.example.e_commericemvp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginActivityTest {


    @Rule public ActivityScenarioRule<com.example.e_commericemvp.Activities.registration.ui.Login_activity.LoginActivity>  rule = new ActivityScenarioRule<>(com.example.e_commericemvp.Activities.registration.ui.Login_activity.LoginActivity.class);



    @Test
    public void test_LoginActivityInView(){

       onView(ViewMatchers.withId(R.id.LoginActivity)).check(matches(isDisplayed()));
       onView(withId(R.id.LoginLogo_textView)).check(matches(withText(R.string.login_logo)));

    }

    @Test
    public void test_SignUpButtonClickMoveToSingUpActivityAndPerformBackToLoginAgain(){

        onView(withId(R.id.SingUpBtn_id))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.sign_up)))
                .perform(click());

        onView(withId(R.id.SingUp)).check(matches(isDisplayed()));

        pressBack();

        onView(withId(R.id.LoginActivity)).check(matches(isDisplayed()));

    }
}
