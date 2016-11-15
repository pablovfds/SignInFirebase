package com.miserlyspark13.signinfirebase.ResetPassword;

/**
 * Created by PABLOVICTOR on 15/11/2016.
 */

public interface ResetPasswordPresenter {
    void validateCredentials(String email);

    void onDestroy();
}
