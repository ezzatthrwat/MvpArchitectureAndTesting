package com.example.e_commericemvp.Activities.registration.ui.SingUp_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commericemvp.Activities.registration.pojo.UserRepository;
import com.example.e_commericemvp.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.SignUpView {


    SingUpPresenter singUpPresenter ;

    TextInputEditText Username_EditText , Password_EditText ;
    TextView statusTextView ;
    Button  SignUp ;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        singUpPresenter = new SingUpPresenter(this , UserRepository.getInstance(this)) ;
        initWidget();
        SignUpBtn_ClickListener();
    }


    private void initWidget() {

        Username_EditText = findViewById(R.id.Username_editText);
        Password_EditText = findViewById(R.id.Password_editTex);
        statusTextView = findViewById(R.id.StatusTextView_id);
        SignUp = findViewById(R.id.SingupBtn_id);
        progressBar = findViewById(R.id.progressBar2);

    }

    private void SignUpBtn_ClickListener(){
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUpPresenter.OnSingUpButtonClick(Username_EditText.getText().toString() ,Password_EditText.getText().toString() );
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

    @Override
    public void UserDataIsAlreadyExist() {
        statusTextView.setText("User is Already Exist!");
    }

    @Override
    public void UserDataInsertedSuccessfully() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SignUpActivity.this, "User Created Successfully ", Toast.LENGTH_SHORT).show();
                    }
                });
        finish();
    }

    @Override
    public void UserDataInsertedFailure() {
        statusTextView.setText("SomeThing Wrong Please try Again Later!");
    }

    @Override
    public void UserDataValidation(String msg) {
        statusTextView.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        singUpPresenter.OnSingUpActivityDestroy();
    }
}
