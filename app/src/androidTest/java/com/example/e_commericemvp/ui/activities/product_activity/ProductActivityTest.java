package com.example.e_commericemvp.ui.activities.product_activity;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.e_commericemvp.ui.Activities.product_activity.MainActivity;
import com.example.e_commericemvp.R;
import com.example.e_commericemvp.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner.class)
public class ProductActivityTest {

    private int list_item_position = 4 ;
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource);
    }

    @After
    public void unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource);
    }

    @Test
    public void A_recyclerViewComeIntoView(){
        onView(ViewMatchers.withId(R.id.ProductActivity)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.RecyclerView_Products)).check(matches(isDisplayed()));
    }

    @Test
    public void B_TestOnClickAddToCartButtonInRecyclerViewListItem(){


        onView(withId(R.id.RecyclerView_Products)).perform(RecyclerViewActions.scrollToPosition(list_item_position));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPosition(list_item_position)).check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.AddToCart_id))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.AddToCart_id))
                .perform(click());

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.ProductQuantity_id))
                .check(matches(withText("1")));

    }

    @Test
    public void C_TestOnClickPlusButtonInRecyclerViewListItem(){

        onView(withId(R.id.RecyclerView_Products)).perform(RecyclerViewActions.scrollToPosition(list_item_position));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPosition(list_item_position))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.PlusIcon_id))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.PlusIcon_id))
                .perform(click());

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.ProductQuantity_id))
                .check(matches(withText("2")));
    }


    @Test
    public void D_TestOnClickMinusButtonInRecyclerViewListItem(){

        onView(withId(R.id.RecyclerView_Products))
                .perform(RecyclerViewActions.scrollToPosition(list_item_position));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPosition(list_item_position))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.MinusIcon_id))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.MinusIcon_id))
                .perform(click());

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.ProductQuantity_id))
                .check(matches(withText("1")));
    }

    @Test
    public void E_TestOnClickMinusButtonInRecyclerViewListItemWhenQuantityEqualOne(){

        onView(withId(R.id.RecyclerView_Products)).perform(RecyclerViewActions.scrollToPosition(list_item_position));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPosition(list_item_position))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.MinusIcon_id))
                .check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.MinusIcon_id))
                .perform(click());

        onView(withRecyclerView(R.id.RecyclerView_Products).atPositionOnView(list_item_position , R.id.AddToCart_id))
                .check(matches(isDisplayed()));
    }



    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}
