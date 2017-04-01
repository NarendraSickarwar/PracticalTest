package test.narendra.com.gopettingpractical.utils;

import android.content.Context;

/**
 * <h1>Progress Dialog , {@link ProgressDialog} </h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class ProgressDialog {
    private String pDialogMessage = "Loading...";
    private android.app.ProgressDialog pDialog;

    public ProgressDialog(Context context) {
        init(context);
    }

    private void init(Context _context) {
        pDialog = new android.app.ProgressDialog(_context);
        pDialog.setMessage(pDialogMessage);
        pDialog.setCancelable(false);


    }

    public void showProgressDialog(String message) {

        pDialog.setMessage(message);
        pDialog.show();
    }

    public void hideProgressDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
}
