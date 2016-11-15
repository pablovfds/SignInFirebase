package com.miserlyspark13.signinfirebase.SignUp;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface SignupPresenter {
    void validateCredentials(String email, String password, FirebaseAuth firebaseAuth);

    void onDestroy();
}
