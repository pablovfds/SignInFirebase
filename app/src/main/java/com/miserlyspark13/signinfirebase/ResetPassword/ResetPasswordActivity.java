package com.miserlyspark13.signinfirebase.ResetPassword;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.miserlyspark13.signinfirebase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordView {

    private FirebaseAuth auth;

    private ResetPasswordPresenter presenter;

    @BindView(R.id.email) TextInputEditText inputEmail;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        presenter = new ResetPasswordPresenterImpl(this, auth);

    }

    @OnClick(R.id.btn_back)
    public void backToLogin(){
        finish();
    }

    @OnClick(R.id.btn_reset_password)
    public void resetPassword() {
        String email = inputEmail.getText().toString().trim();

        presenter.validateCredentials(email);
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
        inputEmail.setError(getString(R.string.error_invalid_email));
    }

    @Override
    public void setResetError() {
        Snackbar.make(coordinatorLayout, getString(R.string.error_send_email), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void navigateToLogin() {
        Snackbar.make(coordinatorLayout, getString(R.string.sucess_send_email), Snackbar.LENGTH_LONG).show();
    }
}