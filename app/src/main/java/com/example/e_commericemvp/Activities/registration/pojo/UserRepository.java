package com.example.e_commericemvp.Activities.registration.pojo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.e_commericemvp.database.AppDatabase;
import com.example.e_commericemvp.Activities.registration.ui.Login_activity.LoginContract;
import com.example.e_commericemvp.Activities.registration.ui.SingUp_activity.SignUpContract;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class UserRepository implements LoginContract.getUserDataInteractor , SignUpContract.insertUserDataInteractor {

    private static UserRepository ourInstance ;

    private AppDatabase mDb ;

    private Executor executor = Executors.newSingleThreadExecutor();

    private SharedPreferences pref ;

    public static UserRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new UserRepository(context);
        }
        return ourInstance;
    }

    private UserRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        pref = context.getSharedPreferences("MyPref", 0);
    }

    @Override
    public void onFetchingUserDataStart(final String Username, final String Password, final LoginContract.getUserDataInteractor.OnFinishedListener onFinishedListener) {

            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserModel User = mDb.UserDao().getUserDataFromDatabase(Username , Password);

                if(User != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("UserID", User.getUserID());
                    editor.apply();
                }

                onFinishedListener.onFetchingUserDataFinished(User);

            }
        } , 1500);
    }

    @Override
    public void onInsertUserDataStart(final String Username, final String Password, final SignUpContract.insertUserDataInteractor.OnFinishedListener onFinishedListener) {
        final UserModel User = mDb.UserDao().getUserDataFromDatabase(Username , Password) ;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (User==null){
                    long InsertedID = mDb.UserDao().insertNewUser(new UserModel(Username , Password , String.valueOf((int)Math.random()))) ;
                    onFinishedListener.onInsertUserDataFinished(InsertedID);
                }else{
                    onFinishedListener.UserIsExist();
                }

            }
        });

    }


}
