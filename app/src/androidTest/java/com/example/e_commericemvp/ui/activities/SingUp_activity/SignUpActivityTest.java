package com.example.e_commericemvp.ui.activities.SingUp_activity;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.e_commericemvp.ui.Activities.SingUp_activity.SignUpActivity;
import com.example.e_commericemvp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class SignUpActivityTest {

    private static final String USERNAME_STRING = "Ezzat";
    private static final String PASSWORD_STRING = "123456";


    @Rule
    public ActivityScenarioRule<SignUpActivity> rule = new ActivityScenarioRule<>(SignUpActivity.class);

    @Test
    public void test_SingUpActivityInView(){

        onView(ViewMatchers.withId(R.id.SingUp)).check(matches(isDisplayed()));
    }

    @Test
    public void test_UserName_EditText_Enter_Text(){

        onView(withId(R.id.Username_editText))
                .check(matches(isDisplayed()))
                .perform(typeText(USERNAME_STRING) ,  closeSoftKeyboard());

        onView(withId(R.id.Username_editText)).check(matches(withText(USERNAME_STRING)));


        onView(withId(R.id.Password_editTex))
                .check(matches(isDisplayed()))
                .perform(typeText(PASSWORD_STRING) , closeSoftKeyboard());

        onView(withId(R.id.Password_editTex)).check(matches(withText(PASSWORD_STRING)));
    }




}
