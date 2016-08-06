package sasa.com.daggersample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Inject LoginPresenter presenter;

    @Bind(R.id.user_email) EditText userEmail;
    @Bind(R.id.user_password) EditText userPassword;
    @Bind(R.id.progress) ProgressBar progressBar;



    @OnClick(R.id.submit_btn) void submitCLicked() {
        if (presenter != null) {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            presenter.submitData();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                            .appComponent(DaggerApplication.get(this).getAppComponent())
                            .loginModule(new LoginModule(this))
                            .build()
                            .inject(this);



    }


    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override public String getUserEmailText() {
        return  userEmail.getText().toString();
    }

    @Override public String getUserPasswordText() {
        return userPassword.getText().toString();
    }

    @Override public void setEmailError() {
        userEmail.setError(getString(R.string.email_error));
    }

    @Override public void setPasswordError() {
        userPassword.setError(getString(R.string.password_error));
    }

    @Override public void showResult() {
        Toast.makeText(LoginActivity.this, R.string.success_result, Toast.LENGTH_SHORT).show();
    }

    @Override public void showFailure() {
        Toast.makeText(LoginActivity.this, R.string.login_error, Toast.LENGTH_SHORT).show();
    }


}
