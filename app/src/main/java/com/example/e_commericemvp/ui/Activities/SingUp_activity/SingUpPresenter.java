package com.example.e_commericemvp.ui.Activities.SingUp_activity;


import android.text.TextUtils;

import com.example.e_commericemvp.data.repository.AppRepository;

public class SingUpPresenter implements SignUpContract.SingUpPresenter {

    private SignUpContract.SignUpView signUpView ;
    private final AppRepository appRepository ;

    public SingUpPresenter(SignUpContract.SignUpView signUpView, AppRepository appRepository) {
        this.signUpView = signUpView;
        this.appRepository = appRepository;
    }

    @Override
    public void OnSingUpButtonClick(String UserName, String Password) {

        if (signUpView != null){
            signUpView.showProgress();
        }

        if (validation(UserName , Password)) {
            appRepository.onInsertUserDataStart(UserName, Password, new SignUpContract.insertUserDataInteractor.OnFinishedListener() {
                @Override
                public void onInsertUserDataFinished(long inserted_UserID) {

                    if (inserted_UserID != -1 ){
                        signUpView.UserDataInsertedSuccessfully();
                    } else {
                        signUpView.UserDataInsertedFailure();
                    }
                    signUpView.hideProgress();
                }

                @Override
                public void UserIsExist() {
                    signUpView.UserDataIsAlreadyExist();
                    signUpView.hideProgress();
                }
            });
        }else {
            signUpView.UserDataValidation("please enter Valid Username And Password! ");
            signUpView.hideProgress();
        }
    }


    private boolean validation(String UserName , String Password){
       return !TextUtils.isEmpty(Password.trim()) || !TextUtils.isEmpty(UserName.trim());
    }

    @Override
    public void OnSingUpActivityDestroy() {
        signUpView = null ;
    }

}
