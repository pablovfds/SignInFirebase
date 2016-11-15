package com.miserlyspark13.signinfirebase.ResetPassword;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public class ResetPasswordPresenterImpl implements ResetPasswordPresenter, ResetPasswordModel.OnResetPasswordFinishedListener {

    private final FirebaseAuth firebaseAuth;
    private ResetPasswordView resetPasswordView;
    private ResetPasswordModelImpl resetPasswordModel;

    public ResetPasswordPresenterImpl(ResetPasswordView view, FirebaseAuth firebaseAuth) {
        this.resetPasswordView = view;
        this.resetPasswordModel = new ResetPasswordModelImpl();
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void validateCredentials(String email) {
        if (resetPasswordView != null){
            resetPasswordView.showProgress();
        }

        resetPasswordModel.resetPassword(email, this, firebaseAuth);
    }

    @Override
    public void onDestroy() {
        resetPasswordView = null;
    }

    @Override
    public void onEmailError() {
        if (resetPasswordView != null){
            resetPasswordView.hideProgress();
            resetPasswordView.setEmailError();
        }
    }

    @Override
    public void onSuccess() {
        if (resetPasswordView != null){
            resetPasswordView.hideProgress();
            resetPasswordView.navigateToLogin();
        }
    }

    @Override
    public void onFailedIntoReset() {
        if (resetPasswordView != null){
            resetPasswordView.hideProgress();
            resetPasswordView.setResetError();
        }
    }
}
