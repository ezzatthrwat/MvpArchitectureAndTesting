package com.example.e_commericemvp.Activities.registration.ui.SingUp_activity;

public interface SignUpContract {


    interface SignUpView{
        void showProgress();
        void hideProgress();
        void UserDataIsAlreadyExist();
        void UserDataInsertedSuccessfully();
        void UserDataInsertedFailure();
        void UserDataValidation(String msg);
    }

    interface insertUserDataInteractor{

        interface OnFinishedListener {
            void onInsertUserDataFinished(long inserted_UserID);
            void UserIsExist();
        }

        void onInsertUserDataStart(String Username , String Password , OnFinishedListener onFinishedListener );

    }
    interface SingUpPresenter {

        void OnSingUpButtonClick(String UserName , String Password);

        void OnSingUpActivityDestroy();
    }


}
