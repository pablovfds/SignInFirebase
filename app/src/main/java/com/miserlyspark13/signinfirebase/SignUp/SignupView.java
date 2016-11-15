package com.miserlyspark13.signinfirebase.SignUp;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface SignupView {
    void showProgress();

    void hideProgress();

    void setEmailError();

    void setPasswordError();

    void setServerError();

    void navigateToLogin();

    void setUserAlreadyExistError();
}
