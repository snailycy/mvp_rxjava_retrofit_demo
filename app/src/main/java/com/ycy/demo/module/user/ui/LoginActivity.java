package com.ycy.demo.module.user.ui;

import android.view.View;
import android.widget.EditText;

import com.ycy.demo.R;
import com.ycy.demo.base.BaseActivity;
import com.ycy.demo.module.user.presenter.LoginPresenter;
import com.ycy.demo.module.user.view.ILoginView;

/**
 * Created by YCY.
 * https://github.com/snailycy/mvp_rxjava_retrofit_demo
 */
public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {
    private LoginPresenter iLoginPresenter;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_user_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        findViewById(R.id.btn_login).setOnClickListener(this);

        iLoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected View getRootView() {
        return findViewById(R.id.root);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                iLoginPresenter.doUserLogin(etUsername.getText().toString(), etPassword.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iLoginPresenter.onDestroy();
    }
}
