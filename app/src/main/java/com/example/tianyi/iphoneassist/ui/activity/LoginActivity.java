package com.example.tianyi.iphoneassist.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tianyi.iphoneassist.R;
import com.example.tianyi.iphoneassist.bean.LoginBean;
import com.example.tianyi.iphoneassist.di.component.AppComponent;
import com.example.tianyi.iphoneassist.di.component.DaggerLoginComponent;
import com.example.tianyi.iphoneassist.di.module.LoginModule;
import com.example.tianyi.iphoneassist.presenter.LoginPresenter;
import com.example.tianyi.iphoneassist.presenter.contact.LoginContact;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;


/**
 * Created by Tianyi on 2017/11/20.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContact.View {
    @BindView(R.id.login_account)
    EditText mAccount;
    @BindView(R.id.login_textinput_account_layout)
    TextInputLayout mAccountInputLayout;
    @BindView(R.id.login_password)
    EditText mPassword;
    @BindView(R.id.login_button)
    Button btnLogin;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        InitialValueObservable<CharSequence> obAccount = RxTextView.textChanges(mAccount);
        InitialValueObservable<CharSequence> obPwd = RxTextView.textChanges(mPassword);
        Observable.combineLatest(obAccount, obPwd, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence mobile, CharSequence pass) throws Exception{

                return (isPhone(mobile) && isPwd(pass));
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(btnLogin).accept(aBoolean);
            }
        });
    }

    @Override
    public void setupAppComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.login_button)
    public void onCliick(View view){
        mPresenter.login(mAccount.getText().toString().trim(), mPassword.getText().toString().trim());
    }

    public boolean isPhone(CharSequence mobile){
        return mobile.length() == 11;
    }

    public boolean isPwd(CharSequence password){
        return password.length() > 6;
    }

    @Override
    public void loading() {

    }

    @Override
    public void dimissLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        this.finish();
    }

    @Override
    public void loginFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPhoneNumFailed() {
        mAccountInputLayout.setError("手机号码不存在");
    }

    @Override
    public void checkPhoneNumSuccess() {
        mAccountInputLayout.setError("");
        mAccountInputLayout.setErrorEnabled(false);
    }
}
