package com.miserlyspark13.signinfirebase.SignUp;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

interface SignupModel {

    interface OnSignupFinishedListener {
        void onEmailError();

        void onPasswordError();

        void onSuccess();

        void onFailedToSignup();

        void onUserAlreadyExistError();
    }

    void signup(String email, String password,
                SignupModel.OnSignupFinishedListener listener, FirebaseAuth auth, Activity activity);
}
