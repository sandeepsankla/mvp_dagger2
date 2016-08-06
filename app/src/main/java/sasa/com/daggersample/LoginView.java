package sasa.com.daggersample;

/**
 * Created by Kabir on 04-08-2016.
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    String getUserEmailText();
    String getUserPasswordText();
    void setEmailError();
    void setPasswordError();
    void showResult();
    void showFailure();
}
