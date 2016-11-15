package com.miserlyspark13.signinfirebase.login;

import android.app.Activity;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password, Activity activity);

    void userLoggedIn();

    void onDestroy();
}
