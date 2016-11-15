package com.miserlyspark13.signinfirebase.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.miserlyspark13.signinfirebase.MainActivity;
import com.miserlyspark13.signinfirebase.R;
import com.miserlyspark13.signinfirebase.ResetPassword.ResetPasswordActivity;
import com.miserlyspark13.signinfirebase.SignUp.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private FirebaseAuth auth;

    private LoginPresenter presenter;

    @BindView(R.id.email) TextInputEditText inputEmail;

    @BindView(R.id.password) TextInputEditText inputPassword;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // set the view now
        setContentView(R.layout.activity_login);

        // set ButterKnife
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this, auth);

        presenter.userLoggedIn();

        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_signup)
    public void signUp(){
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

    @OnClick(R.id.btn_reset_password)
    public void resetPassword(){
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    @OnClick(R.id.btn_login)
    public void login (){
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        presenter.validateCredentials(email, password, this);
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
    public void setUsernameError() {
        inputEmail.setError(getString(R.string.error_invalid_email));
    }

    @Override
    public void setPasswordError() {
        inputPassword.setError(getString(R.string.error_invalid_password));
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setInvalidLengthPasswordError() {
        inputPassword.setError(getString(R.string.minimum_password));
    }

    @Override
    public void setLoginFailed() {
        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
    }
}