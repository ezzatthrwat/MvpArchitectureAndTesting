package com.example.e_commericemvp.ui.Activities.Login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_commericemvp.data.repository.AppRepository;
import com.example.e_commericemvp.ui.Activities.SingUp_activity.SignUpActivity;
import com.example.e_commericemvp.R;
import com.example.e_commericemvp.ui.Activities.product_activity.MainActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    LoginPresenter loginPresenter ;

    TextInputEditText Username_EditText , Password_EditText ;

    Button LoginBtn , SignUp ;

    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this , AppRepository.getInstance(this) );

        initWidget();
        LoginBtn_ClickListener();
        SignUp_ClickListener();
    }

    private void initWidget() {

        Username_EditText = findViewById(R.id.Username_editText);
        Password_EditText = findViewById(R.id.Password_editTex);
        LoginBtn = findViewById(R.id.LoginBtn_id);
        SignUp = findViewById(R.id.SingUpBtn_id);
        progressBar = findViewById(R.id.progressBar);

    }

    private void LoginBtn_ClickListener(){

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = Username_EditText.getText().toString() ;
                String Password = Password_EditText.getText().toString() ;

                loginPresenter.OnLoginButtonClick(Username , Password );
            }
        });


    }

    private void SignUp_ClickListener(){

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this , SignUpActivity.class));
            }
        });

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void UserDataIsExist(int userID) {
        Toast.makeText(this, ""+userID, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this , MainActivity.class));
    }

    @Override
    public void UserDataIsNotExist() {
        Toast.makeText(this, "UserData Is Not Exist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UserDataValidation(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.OnLoginActivityDestroy();
    }
}
