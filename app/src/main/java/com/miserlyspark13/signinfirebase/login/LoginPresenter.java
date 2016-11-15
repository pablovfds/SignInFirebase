package com.miserlyspark13.signinfirebase.Login;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password, Activity activity);

    void firebaseAuthWithGoogle(GoogleSignInAccount acct, Activity activity);

    void userLoggedIn();

    void onDestroy();
}
