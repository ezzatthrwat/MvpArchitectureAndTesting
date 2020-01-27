package com.example.e_commericemvp.Activities.registration.ui.SingUp_activity;


import android.text.TextUtils;

public class SingUpPresenter implements SignUpContract.SingUpPresenter {

    private SignUpContract.SignUpView signUpView ;
    private SignUpContract.insertUserDataInteractor insertUserDataInteractor ;

    public SingUpPresenter(SignUpContract.SignUpView signUpView, SignUpContract.insertUserDataInteractor insertUserDataInteractor) {
        this.signUpView = signUpView;
        this.insertUserDataInteractor = insertUserDataInteractor;
    }

    @Override
    public void OnSingUpButtonClick(String UserName, String Password) {

        if (signUpView != null){
            signUpView.showProgress();
        }

        if (validation(UserName , Password)) {
            insertUserDataInteractor.onInsertUserDataStart(UserName, Password, new SignUpContract.insertUserDataInteractor.OnFinishedListener() {
                @Override
                public void onInsertUserDataFinished(long inserted_UserID) {

                    if (inserted_UserID != -1 ){
                        signUpView.UserDataInsertedSuccessfully();
                        signUpView.hideProgress();
                    } else {
                        signUpView.UserDataInsertedFailure();
                        signUpView.hideProgress();
                    }
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
        insertUserDataInteractor = null ;
    }
}
