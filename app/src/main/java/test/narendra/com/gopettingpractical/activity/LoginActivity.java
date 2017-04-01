package test.narendra.com.gopettingpractical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.modal.SocialData;
import test.narendra.com.gopettingpractical.utils.AppConstants;
import test.narendra.com.gopettingpractical.utils.GplusLoginHandler;
import test.narendra.com.gopettingpractical.utils.Preference;
import test.narendra.com.gopettingpractical.utils.Utils;

/**
 * <h1>Login Activity, provides Login for a user</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GplusLoginHandler.SocialLoginListner {

    SignInButton sGoogleSignButton;
    GplusLoginHandler gplusLoginHandler;
    RelativeLayout activityLoginMainLayout;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inIt();
        /***
         * if user login for google+ then get value from set perferences
         * and redirect for main activity if user id not blank.
         */
        String userId = Preference.getInstance().getValue(LoginActivity.this, AppConstants.PREF_USER_ID, "");
        if (!userId.equals("")) {
            goToMainActivity();
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        setupGooglePlus();
    }

    private void inIt() {
        sGoogleSignButton = (SignInButton) findViewById(R.id.sign_in_button);
        activityLoginMainLayout = (RelativeLayout) findViewById(R.id.activity_login_main_layout);
        sGoogleSignButton.setOnClickListener(this);
    }

    private void setupGooglePlus() {
        gplusLoginHandler = new GplusLoginHandler(LoginActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                if (gplusLoginHandler != null) {

                    GoogleApiClient mGoogleApiClient = gplusLoginHandler.getmGoogleApiClient();

                    if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {

                        Utils.showSnackBar(activityLoginMainLayout, getString(R.string.google_not_connected));
                    } else {

                        gplusLoginHandler.gPlusSignIn();
                    }
                    break;
                }
                break;
        }
    }

    @Override
    public void socialLoginSuccess(SocialData socilaData) {
        Preference.getInstance().put(LoginActivity.this, AppConstants.PREF_USER_ID, socilaData.getId());
        goToMainActivity();
    }

    @Override
    public void socialLoginError(String msg) {
        Utils.showSnackBar(activityLoginMainLayout, msg);
    }

    @Override
    public void socialLoginMsg(String msg) {
        Utils.showSnackBar(activityLoginMainLayout, msg);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Utils.printLog("onActivityResult==" + "onActivityResult");

        if (requestCode == GplusLoginHandler.RC_SIGN_IN && resultCode == RESULT_OK) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statusCode = result.getStatus().getStatusCode();
            Utils.printLog("onActivityResultstatusCode==" + "onActivityResult" + statusCode);

            gplusLoginHandler.onActivityResult(data);
            return;
        }
    }
}
