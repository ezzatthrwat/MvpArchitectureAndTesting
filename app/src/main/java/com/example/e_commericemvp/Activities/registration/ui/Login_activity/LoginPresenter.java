package com.example.e_commericemvp.Activities.registration.ui.Login_activity;

import android.text.TextUtils;

import com.example.e_commericemvp.Activities.registration.pojo.UserModel;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView loginView ;
    private LoginContract.getUserDataInteractor getUserDataInteractor ;

    LoginPresenter(LoginContract.LoginView loginView, LoginContract.getUserDataInteractor getUserDataInteractor) {
        this.loginView = loginView;
        this.getUserDataInteractor = getUserDataInteractor;
    }

    @Override
    public void OnLoginButtonClick(final String UserName, final String Password) {

        if (loginView != null){
            loginView.showProgress();
        }
        getUserDataInteractor.onFetchingUserDataStart(UserName , Password , new LoginContract.getUserDataInteractor.OnFinishedListener() {
            @Override
            public void onFetchingUserDataFinished(UserModel user) {

                loginView.hideProgress();

                if (validation(UserName , Password)) {
                    if (user != null && user.getUsername().equals(UserName) && user.getPassword().equals(Password)) {
                        loginView.UserDataIsExist(user.getUserID());
                    } else {
                        loginView.UserDataIsNotExist();
                    }
                }else {
                    loginView.UserDataValidation("Incorrect Username Or Paswword");
                }
            }
        } );

    }

    private boolean validation(String UserName , String Password){
        return !TextUtils.isEmpty(UserName) || !TextUtils.isEmpty(Password);
    }

    @Override
    public void OnLoginActivityDestroy() {
        loginView= null ;
        getUserDataInteractor = null;
    }


}
