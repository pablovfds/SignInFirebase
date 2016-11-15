package com.miserlyspark13.signinfirebase.ResetPassword;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface ResetPasswordModel {
    interface OnResetPasswordFinishedListener {
        void onEmailError();

        void onSuccess();

        void onFailedIntoReset();

    }

    void resetPassword(String username, ResetPasswordModel.OnResetPasswordFinishedListener listener, FirebaseAuth auth);
}
