package sasa.com.daggersample;

import android.util.Patterns;

/**
 * Created by Kabir on 04-08-2016.
 */
public class LoginPresenterImpl implements LoginPresenter,OnInfoCompleteListener {

    private LoginView  loginView;
    private LoginInteractor loginInteractor;



    public LoginPresenterImpl(LoginView view,LoginInteractor interactor) {
        this.loginView =  view;
        this.loginInteractor = interactor;
    }

    private boolean validateEmail() {
        return Patterns.EMAIL_ADDRESS.matcher(loginView.getUserEmailText()).matches();
    }

    private boolean validatePassword() {
        boolean isValidPass = false;
        if (loginView.getUserPasswordText().length() > 6)
            isValidPass = true;
        return isValidPass;
    }

    @Override public void submitData() {

        if (!validateEmail())
            loginView.setEmailError();
        else if (!validatePassword())
            loginView.setPasswordError();
        else{
            loginView.showProgress();
            loginInteractor.getUserCredentials(loginView.getUserEmailText(), loginView.getUserPasswordText(),this);
        }


    }

    @Override public void onSuccess() {
        loginView.hideProgress();
        loginView.showResult();
    }

    @Override public void onFailure() {
        loginView.hideProgress();
        loginView.showFailure();
    }
}
