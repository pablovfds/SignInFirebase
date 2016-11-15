package com.miserlyspark13.signinfirebase.Login;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void setInvalidLengthPasswordError();

    void setLoginFailed();

    void setAuthenticationFailed();
}
