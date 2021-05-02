package com.example.e_commericemvp.ui.Activities.Login_activity;

import com.example.e_commericemvp.data.model.UserModel;

public interface LoginContract {

    interface LoginView{
         void showProgress();
         void hideProgress();
         void UserDataIsExist(int UserToken);
         void UserDataIsNotExist();
         void UserDataValidation(String msg);
    }

    interface getUserDataInteractor{

        interface OnFinishedListener {
            void onFetchingUserDataFinished(UserModel user);
        }

        void onFetchingUserDataStart(String Username , String Password ,OnFinishedListener onFinishedListener );

    }
    interface LoginPresenter{

         void OnLoginButtonClick(String UserName , String Password);

         void OnLoginActivityDestroy();
    }


}
