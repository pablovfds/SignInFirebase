package com.miserlyspark13.signinfirebase.SignUp;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

class SignupPresenterImpl implements SignupPresenter, SignupModel.OnSignupFinishedListener {

    private final Activity activitySignup;
    private SignupView signupView;

    private SignupModel signupModel;

    SignupPresenterImpl(SignupView view, Activity activity) {
        this.signupView = view;
        this.signupModel = new SignupModelImpl();
        this.activitySignup = activity;
    }

    @Override
    public void validateCredentials(String email, String password, FirebaseAuth firebaseAuth) {
        if (signupView != null) {
            signupView.showProgress();
        }
        Log.d("presenter", "");
        signupModel.signup(email, password, this, firebaseAuth, activitySignup);
    }

    @Override
    public void onDestroy() {
        signupView = null;
    }

    @Override
    public void onEmailError() {
        if (signupView != null) {
            signupView.hideProgress();
            signupView.setEmailError();
        }
    }

    @Override
    public void onPasswordError() {
        if (signupView != null) {
            signupView.hideProgress();
            signupView.setPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (signupView != null) {
            signupView.hideProgress();
            signupView.navigateToLogin();
        }
    }

    @Override
    public void onFailedToSignup() {
        if (signupView != null) {
            signupView.hideProgress();
            signupView.setServerError();
        }
    }

    @Override
    public void onUserAlreadyExistError() {
        if (signupView != null) {
            signupView.hideProgress();
            signupView.setUserAlreadyExistError();
        }
    }
}
