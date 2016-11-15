package com.miserlyspark13.signinfirebase.SignUp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.miserlyspark13.signinfirebase.R;
import com.miserlyspark13.signinfirebase.ResetPassword.ResetPasswordActivity;
import com.miserlyspark13.signinfirebase.Login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity implements SignupView {

    private FirebaseAuth auth;

    private SignupPresenter signupPresenter;

    @BindView(R.id.email) TextInputEditText inputEmail;

    @BindView(R.id.password) TextInputEditText inputPassword;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        ButterKnife.bind(this);

        signupPresenter = new SignupPresenterImpl(this, this);
    }

    @OnClick(R.id.btn_reset_password)
    public void resetPassword() {
        startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
    }


    @OnClick(R.id.sign_in_button)
    public void login(){
        finish();
    }

    @OnClick(R.id.sign_up_button)
    public void signUp(){
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        Log.d("activity", "");

        signupPresenter.validateCredentials(email, password, auth);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideProgress();
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
    public void setEmailError() {
        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setServerError() {
        Toast.makeText(getApplicationContext(), "Failed into create user!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void setUserAlreadyExistError() {
        Toast.makeText(getApplicationContext(), "The email address is already in use by another account!", Toast.LENGTH_SHORT).show();
    }
}
