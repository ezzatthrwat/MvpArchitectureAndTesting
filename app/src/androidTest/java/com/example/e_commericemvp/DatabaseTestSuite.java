package com.example.e_commericemvp;

import com.example.e_commericemvp.databases.CartTest;
import com.example.e_commericemvp.databases.ProductTest;
import com.example.e_commericemvp.databases.RegistrationDatabaseTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationDatabaseTest.class,
        ProductTest.class,
        CartTest.class
})
public class DatabaseTestSuite {}
