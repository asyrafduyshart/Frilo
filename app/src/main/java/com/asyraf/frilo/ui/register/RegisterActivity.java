package com.asyraf.frilo.ui.register;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.asyraf.frilo.R;
import com.asyraf.frilo.ui.base.BaseActivity;
import com.asyraf.frilo.ui.common.ErrorView;
import com.asyraf.frilo.ui.detail.DetailActivity;
import com.asyraf.frilo.ui.login.LoginActivity;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class RegisterActivity extends BaseActivity implements RegisterMvpView, ErrorView.ErrorListener {

    @Inject
    RegisterPresenter mRegisterPresenter;

    @BindView(R.id.view_error)
    ErrorView mErrorView;
    @BindView(R.id.layout_register)
    View mRegisterLayout;

    @BindView(R.id.progress)
    ProgressBar mProgress;

    @BindView(R.id.et_username)
    EditText usernameInput;
    @BindView(R.id.et_email)
    EditText emailInput;
    @BindView(R.id.et_license)
    EditText licenseInput;


    @BindView(R.id.b_register)
    Button registerButton;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    String user_kit_token;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mRegisterPresenter.attachView(this);
        mErrorView.setErrorListener(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        mRegisterPresenter.checkUserToken();

    }

    @OnClick(R.id.b_register)
    public void attempRegister(){
        String full_name = usernameInput.getText().toString();
        String email = emailInput.getText().toString();
        String license = licenseInput.getText().toString();
        mRegisterPresenter.registerUser(full_name,email,license,user_kit_token);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void onReloadData() {

    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this,"Register Success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFailed() {
        Toast.makeText(this,"Register Failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userToken(String kit_token) {
        if (kit_token == null){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }else{
            user_kit_token = kit_token;
        }
    }

    @Override
    public void showProgress(boolean show) {
        mErrorView.setVisibility(View.GONE);
        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        mRegisterLayout.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was a problem when register.....");
    }
}
