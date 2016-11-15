package com.miserlyspark13.signinfirebase.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface LoginModel {
    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onPasswordLengthError();

        void onSuccess();

        void onLoginError();
    }

    void login(String username, String password, OnLoginFinishedListener listener, FirebaseAuth auth, Activity activity);

    void userLoggedIn(FirebaseAuth auth, OnLoginFinishedListener listener);
}
