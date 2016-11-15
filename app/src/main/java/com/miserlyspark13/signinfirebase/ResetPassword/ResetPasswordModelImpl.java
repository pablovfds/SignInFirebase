package com.miserlyspark13.signinfirebase.ResetPassword;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public class ResetPasswordModelImpl implements ResetPasswordModel {

    @Override
    public void resetPassword(String email, final OnResetPasswordFinishedListener listener, FirebaseAuth auth) {
        if (TextUtils.isEmpty(email)) {
            listener.onEmailError();
            return;
        } else {
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                listener.onSuccess();
                            } else {
                                listener.onFailedIntoReset();
                            }
                        }
                    });
        }
    }
}
