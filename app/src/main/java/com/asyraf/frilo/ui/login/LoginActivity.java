package com.asyraf.frilo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asyraf.frilo.R;
import com.asyraf.frilo.ui.base.BaseActivity;
import com.asyraf.frilo.ui.common.ErrorView;
import com.asyraf.frilo.ui.main.MainActivity;
import com.asyraf.frilo.ui.register.RegisterActivity;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import com.squareup.haha.perflib.Main;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends BaseActivity implements LoginMvpView, ErrorView.ErrorListener {

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.view_error)
    ErrorView mErrorView;

    @BindView(R.id.hicvp)
    HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;

    @BindView(R.id.b_get_started)
    Button getStartedButton;

    @BindView(R.id.progress)
    ProgressBar mProgress;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.login_layout)
    View mLoginLayout;

    public static int APP_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mLoginPresenter.attachView(this);
        mErrorView.setErrorListener(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);


        horizontalInfiniteCycleViewPager.setAdapter(new VerticalPagerAdapter(getApplicationContext()));

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.b_get_started)
    public void attemptLogin() {
        mLoginPresenter.isFacebookVerified();
    }

    @Override
    public void onReloadData() {

    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void loginFailed() {
        Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void needRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    public void alreadyFacebookVerified(boolean verified) {
        if (!verified){
            final Intent intent = new Intent(LoginActivity.this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                    new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN)
                            .setReadPhoneStateEnabled(true);

            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, APP_REQUEST_CODE);
        }

    }

    @Override
    public void showProgress(boolean show) {
        mErrorView.setVisibility(View.GONE);
        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        mLoginLayout.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        Snackbar.make(findViewById(android.R.id.content), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            mLoginPresenter.authResult(loginResult);

        }
    }

}
