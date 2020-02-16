package com.example.e_commericemvp.ui.databases;

import android.util.Log;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

import org.junit.rules.TestRule;
import org.junit.runner.Description;

import org.junit.runners.model.Statement;

public class ProductModel_CustomRule implements TestRule {

    private static final String TAG = "ProductModel_CustomRule";

    ProductModel model ;

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                model = new ProductModel(1 , "ProductName" , 50 , "Image") ;

                try {
                    base.evaluate();
                }catch (Exception e){
                    Log.e(TAG, "evaluate: ",e );
                }
            }
        };
    }
}
