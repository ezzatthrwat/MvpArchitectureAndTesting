package com.example.e_commericemvp.databases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationDatabaseTest.class,
        ProductTest.class,
        CartTest.class
})
public class DatabaseTestSuite {}
