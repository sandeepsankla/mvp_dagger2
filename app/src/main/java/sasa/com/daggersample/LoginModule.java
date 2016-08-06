package sasa.com.daggersample;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kabir on 04-08-2016.
 */
@Module
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView view) {
        this.loginView = view;
    }

    @Provides
     LoginPresenter provideLoginPresenter(LoginInteractor interactor) {

        return new LoginPresenterImpl(loginView,interactor);
    }

    @Provides
     LoginInteractor provideLoginInteractor() {
        return new LoginInteractorImpl();
    }
}
