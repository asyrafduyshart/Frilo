package com.asyraf.frilo.ui.login;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.asyraf.frilo.R;
import com.asyraf.frilo.ui.base.BaseActivity;
import com.asyraf.frilo.ui.common.ErrorView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginMvpView, ErrorView.ErrorListener {

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.view_error)
    ErrorView mErrorView;
    @BindView(R.id.et_email)
    EditText emailInput;
    @BindView(R.id.et_password)
    EditText passwordInput;

    @BindView(R.id.b_login)
    Button loginButton;
    @BindView(R.id.b_register)
    Button registerButton;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mLoginPresenter.attachView(this);
        mErrorView.setErrorListener(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.b_login)
    public void attemptLogin(){
        String email = emailInput.getText().toString();
        String password =  passwordInput.getText().toString();
        mLoginPresenter.loginFirebaseEmail(email,password);

    }

    @Override
    public void onReloadData() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerFailed() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showError(Throwable error) {

    }
}
