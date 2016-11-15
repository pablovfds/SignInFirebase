package com.miserlyspark13.signinfirebase.ResetPassword;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface ResetPasswordView {

    void showProgress();

    void hideProgress();

    void setEmailError();

    void setResetError();

    void navigateToLogin();
}
