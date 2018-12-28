package com.example.sekini.ui.login;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.example.sekini.R;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.remote.Token;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;

import javax.inject.Inject;


public class LoginViewModel extends FragmentBaseViewModel<ILoginNavigator> {


    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> passwordError = new MutableLiveData<>();
    public MutableLiveData<String> userNameError = new MutableLiveData<>();

    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IAppPref appPref;

    @Inject
    public IRepository repository;


    @Inject
    public LoginViewModel() {
        userName.setValue("beh_66@yahoo.com");
        password.setValue("bbcliqa");
    }


    public void connect() {

        if (!isUserValid(userName.getValue())) {
            userNameError.postValue(commonUtils.getString(R.string.error_invalid_user_name));
            return;
        }
        if (!isPasswordValid(password.getValue())) {
            passwordError.postValue(commonUtils.getString(R.string.error_invalid_password));
            return;
        }

        RunnableMethod<Object, RunnableModel<Token>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<Token> runnableModel = new RunnableModel<>();
            try {
                Token token = repository.connect(userName.getValue(), password.getValue());
                appPref.setToken(token.access_token);
                long time = System.currentTimeMillis();
                appPref.setTokenExpireTime(time + token.expires_in * 1000);
                appPref.setUserName(userName.getValue());
                appPref.setPassword(password.getValue());
                runnableModel.setModel(token);
            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;
        };

        RunnableIn<RunnableModel<Token>> post = (param) -> {
            if (param.hasError()) {
                getNavigator().showYesNoDialog(R.string.attention, R.string.error_login, null, null);
            } else {
                if (!TextUtils.isEmpty(param.getModel().access_token)) {
                    getNavigator().loginSuccessful();
                    getNavigator().refreshMenu();
                } else {
                    getNavigator().showYesNoDialog(R.string.attention, R.string.error_login, null, null);
                }
            }
            getNavigator().hideProgress();

        };

        if (commonUtils.isInternetOn()) {
            getNavigator().showProgress(true);
            runAsyncTaskWithOutException(runnableMethod, post);
        } else {
            Runnable runnable = this::init;
            getNavigator().snackBar(commonUtils.getString(R.string.no_internet_connection),
                    commonUtils.getString(R.string.try_sync), runnable);
        }
    }

    public void logout() {
        appPref.setToken("");
        appPref.setLife(0);
        appPref.setTokenExpireTime(0);
        appPref.setScore(0);
        appPref.setUserId(0);
        appPref.setUserName("");
        userName.setValue("");
        password.setValue("");
        getNavigator().refreshMenu();
    }

    private boolean isUserValid(String email) {
        return !TextUtils.isEmpty(email);
    }

    private boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password);
    }

    public void init() {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime()){
            getNavigator().loginSuccessful();
            getNavigator().refreshMenu();
        }
    }
}
