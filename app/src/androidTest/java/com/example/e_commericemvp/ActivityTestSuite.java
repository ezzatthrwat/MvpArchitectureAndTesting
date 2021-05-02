package com.example.e_commericemvp;


import com.example.e_commericemvp.ui.activities.Login_activity.LoginActivityTest;
import com.example.e_commericemvp.ui.activities.SingUp_activity.SignUpActivityTest;
import com.example.e_commericemvp.ui.activities.product_activity.ProductActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginActivityTest.class,
        SignUpActivityTest.class,
        ProductActivityTest.class
})
public class ActivityTestSuite {}
