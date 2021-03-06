package com.wxkj.readerfm.ui;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.kymjs.rxvolley.client.HttpCallback;
import com.wxkj.readerfm.AppConfig;
import com.wxkj.readerfm.AppContext;
import com.wxkj.readerfm.R;
import com.wxkj.readerfm.api.remote.FmApi;
import com.wxkj.readerfm.base.BaseBackActivity;
import com.wxkj.readerfm.utils.AccountValidatorUtil;
import com.wxkj.readerfm.utils.TDevice;
import com.wxkj.readerfm.utils.UIHelper;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.wxkj.readerfm.R.id.btn_login;
import static com.wxkj.readerfm.R.id.btn_register;

/**
 * Created by taosong on 17/8/2.
 */

public class LoginActivity extends BaseBackActivity implements View.OnClickListener {



    @Bind(R.id.et_username)
    AppCompatEditText mEtUserName;

    @Bind(R.id.et_password)
    AppCompatEditText mEtPassword;


    private String mUserName = "";
    private String mPassword = "";


    @Override
    protected int getContentView() {
        return R.layout.app_login;
    }


    @OnClick({btn_register, btn_login})
    @Override
    public void onClick(View view) {

        int vId  = view.getId();


        switch (vId){

            case btn_login:
                handleLogin();
                break;

            case btn_register:
                UIHelper.showRegisterActivity(LoginActivity.this, AppConfig.REQUEST_REGISTER_CODE);
                break;
        }
    }



    private void handleLogin() {

        if (prepareForLogin()) {
            return;
        }
        mUserName = mEtUserName.getText().toString();
        mPassword = mEtPassword.getText().toString();


        FmApi.login(mUserName, mPassword, mHandler);
    }



    private HttpCallback  mHandler = new HttpCallback() {

        @Override
        public void onPreStart() {
            super.onPreStart();
        }

        @Override
        public void onSuccess(Map<String, String> headers, byte[] t) {
            super.onSuccess(headers, t);
        }

        @Override
        public void onFailure(int errorNo, String strMsg) {
            super.onFailure(errorNo, strMsg);
        }

        @Override
        public void onFinish() {
            super.onFinish();
        }
    };




    private boolean prepareForLogin() {
        if (!TDevice.hasInternet()) {
            AppContext.showToastShort(R.string.tip_network_error);
            return true;
        }
        if (mEtUserName.length() == 0) {
            mEtUserName.setError("请输入手机号");
            mEtUserName.requestFocus();
            return true;
        }
        if (!AccountValidatorUtil.isMobile(mEtUserName.getText().toString())){
            mEtUserName.setError("请输入有效手机号");
            mEtUserName.requestFocus();
            return true;

        }



        if (mEtPassword.length() == 0) {
            mEtPassword.setError("请输入密码");
            mEtPassword.requestFocus();
            return true;
        }

        return false;
    }

}
