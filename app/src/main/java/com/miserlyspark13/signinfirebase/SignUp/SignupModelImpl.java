package com.miserlyspark13.signinfirebase.SignUp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public class SignupModelImpl implements SignupModel {

    @Override
    public void signup(String email, String password,
                       final OnSignupFinishedListener listener, FirebaseAuth auth, Activity activity) {
        Log.d("model", "");
        if (TextUtils.isEmpty(email)) {
            listener.onEmailError();
        } else if (TextUtils.isEmpty(password) || password.length() < 6) {
            listener.onPasswordError();
        } else {
            //create user
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                String userAlreadyExist = "The email address is already in use by another account.";
                                if (task.getException().getMessage().equals(userAlreadyExist)){
                                    listener.onUserAlreadyExistError();
                                }
                                Log.i("error", task.getException().getMessage());
                                listener.onFailedToSignup();
                            } else {
                                listener.onSuccess();
                            }
                        }
                    });
        }

    }
}
