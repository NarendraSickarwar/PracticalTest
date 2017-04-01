package test.narendra.com.gopettingpractical.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;

import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.modal.SocialData;


/**
 * <h1> provide get request form activity and login on Google+ @{@link GoogleApiClient}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class GplusLoginHandler implements GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 1004;


    GoogleApiClient mGoogleApiClient;
    Context context;
    SocialLoginListner socialLoginListner;
    ProgressDialog mProgressDialog;

    public GplusLoginHandler(Context context) {

        this.context = context;
        mProgressDialog = new ProgressDialog(context);
        setSocialLoginListner((SocialLoginListner) context);
        gPlusInit();
    }


    public void setSocialLoginListner(SocialLoginListner socialLoginListner) {
        this.socialLoginListner = socialLoginListner;
    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    private void gPlusInit() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage((FragmentActivity) context, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .build();

    }

    public void gPlusSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((Activity) context).startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void gPlusSignOut() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {

                        }
                    });
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        mProgressDialog.showProgressDialog(context.getString(R.string.pleasewait));
        Utils.printLog("handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            if (acct != null) {
                SocialData socialData = new SocialData();
                socialData.setEmail(acct.getEmail());
                socialData.setFirstname(acct.getDisplayName());
                socialData.setId(acct.getId());
                socialData.setLoginfrom("G");

                Utils.printLog("Socialdata== " + socialData.toString());
                gPlusSignOut();

                if (socialLoginListner != null) {
                    mProgressDialog.hideProgressDialog();
                    socialLoginListner.socialLoginSuccess(socialData);
                }
            } else {
                mProgressDialog.hideProgressDialog();
                socialLoginListner.socialLoginError("Error in Profile getting\nGoogleSignInAccount is null");
            }

        } else {

            Status status = result.getStatus();
            status.getStatusMessage();
            mProgressDialog.hideProgressDialog();
            socialLoginListner.socialLoginError("Error in Profile getting\n" + status.getStatus().getStatusMessage());
        }
    }

    public void onActivityResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        handleSignInResult(result);

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Utils.printLog("onConnectionFailed()==" + connectionResult.getErrorMessage());


    }

    public interface SocialLoginListner {
        void socialLoginSuccess(SocialData socilaData);

        void socialLoginError(String msg);

        void socialLoginMsg(String msg);
    }

}
