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
        getNavigator().showProgress(true);
        RunnableMethod<Object, RunnableModel<Token>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<Token> runnableModel = new RunnableModel<>();
            try {
                Token token = repository.connect(userName.getValue(), password.getValue());
                appPref.setToken(token.access_token);
                long time = System.currentTimeMillis();
                appPref.setTokenExpireTime(time + token.expires_in * 1000);
                runnableModel.setModel(token);
            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;
        };

        RunnableIn<RunnableModel<Token>> post = (param) -> {
            if (param.hasError()) {
                getNavigator().handleError(param.getException());
            } else {
                if (!TextUtils.isEmpty(param.getModel().access_token)) {
                    getNavigator().loginSuccessful();
                } else {
                    getNavigator().showYesNoDialog(R.string.attention, R.string.error_login, null, null);
                }
            }
            getNavigator().hideProgress();

        };
        runAsyncTask(runnableMethod, post);
    }

    private boolean isUserValid(String email) {
        return !TextUtils.isEmpty(email);
    }

    private boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password);
    }

    public void init() {
        long time = System.currentTimeMillis();
        if (time < appPref.getTokenExpireTime())
            getNavigator().loginSuccessful();
    }
}
