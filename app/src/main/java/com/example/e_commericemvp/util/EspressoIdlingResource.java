package com.example.e_commericemvp.util;

import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {

    private final static String RESOURCE = "GLOBAL" ;

    public static CountingIdlingResource countingIdlingResource = new CountingIdlingResource(RESOURCE);

    static public void increment(){

        countingIdlingResource.increment();
    }

    static public void decrement(){

        if (!countingIdlingResource.isIdleNow()){
            countingIdlingResource.decrement();
        }
    }

}
