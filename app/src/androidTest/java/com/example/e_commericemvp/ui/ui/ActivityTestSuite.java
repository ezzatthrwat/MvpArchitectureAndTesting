package com.example.e_commericemvp.ui.ui;


import com.example.e_commericemvp.ui.ui.ProductList.ProductActivityTest;
import com.example.e_commericemvp.ui.ui.Registration.LoginActivityTest;
import com.example.e_commericemvp.ui.ui.Registration.SignUpActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginActivityTest.class,
        SignUpActivityTest.class,
        ProductActivityTest.class
})
public class ActivityTestSuite {}
