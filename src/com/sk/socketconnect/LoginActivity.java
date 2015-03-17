package com.sk.socketconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sk.socketconnect.base.BaseActivity;
import com.sk.socketconnect.interf.OnRequestStateListener;
import com.sk.socketconnect.utils.Constant;

public class LoginActivity extends BaseActivity {

    private EditText main_act_username, main_act_password;
    private Button main_act_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.login_act_login:
            String loginMsg = main_act_username.getText().toString().trim() + "," + main_act_password.getText().toString().trim();
            String requestMsg = appendRequest(Constant.LOGIN, loginMsg);
            sendRequest(requestMsg, new OnRequestStateListener() {
                @Override
                public void onRequestSuccess(String result) {
                    Bundle pBundle = new Bundle();
                    pBundle.putString(Constant.LOGIN_RESULT, result);
                    openActivity(ControlActivity.class, pBundle);
                }

                @Override
                public void onRequestFailed() {
                    showLongToast("request failed");
                }
            });
            break;

        default:
            break;
        }
    }

    @Override
    protected void mFindViewByIdAndSetListener() {
        main_act_username = $(R.id.login_act_username);
        main_act_password = $(R.id.login_act_password);
        main_act_login = $(R.id.login_act_login);

        main_act_login.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

}