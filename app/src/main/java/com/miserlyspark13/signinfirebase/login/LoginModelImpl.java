package com.miserlyspark13.signinfirebase.Login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public class LoginModelImpl implements LoginModel {

    private String TAG = "Google signin";

    @Override
    public void login(String username, String password, final OnLoginFinishedListener listener,
                      FirebaseAuth auth, Activity activity) {
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else if (password.length() < 6) {
            listener.onPasswordLengthError();
        } else {

            //authenticate user
            auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                // there was an error
                                listener.onLoginError();
                            } else {
                                listener.onSuccess();
                            }
                        }
                    });
        }
    }

    @Override
    public void userLoggedIn(FirebaseAuth auth, OnLoginFinishedListener listener) {
        if (auth.getCurrentUser() != null) {
            listener.onSuccess();
        }
    }

    @Override
    public void firebaseAuthWithGoogle(FirebaseAuth mAuth, GoogleSignInAccount acct,
                                       Activity activity, final OnLoginFinishedListener listener) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                           listener.onAuthenticationError();
                        } else {
                            listener.onSuccess();
                        }
                    }
                });
    }

}
