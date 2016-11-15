package com.miserlyspark13.signinfirebase.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginModel.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginModel loginModel;
    private FirebaseAuth auth;

    public LoginPresenterImpl(LoginView loginView, FirebaseAuth auth) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
        this.auth = auth;
    }

    @Override
    public void validateCredentials(String username, String password, Activity activity) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel.login(username, password, this,auth, activity);

    }

    @Override
    public void userLoggedIn() {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel.userLoggedIn(auth, this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordLengthError() {
        if (loginView != null) {
            loginView.setInvalidLengthPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }

    @Override
    public void onLoginError() {
        if (loginView != null) {
            loginView.setLoginFailed();
            loginView.hideProgress();
        }
    }
}
