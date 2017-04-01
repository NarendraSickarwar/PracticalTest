package test.narendra.com.gopettingpractical.utils;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import io.realm.Realm;
import test.narendra.com.gopettingpractical.BuildConfig;
import test.narendra.com.gopettingpractical.database.GuideDataTable;
import test.narendra.com.gopettingpractical.eventbusmodal.CartCountEventBus;

/**
 * <h1>provide utilises for app </h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class Utils {


    private final static String TAG = "GoPetting";


    /***
     * showing message on Ui using snakbar
     *
     * @param view
     * @param text
     */
    public static void showSnackBar(View view, String text) {
        if (view.getContext() != null) {
            try {
                final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * print log
     *
     * @param message
     */
    public static void printLog(String message) {

        if (BuildConfig.DEBUG && message != null && !message.equals(""))
            Log.e(TAG, message);
    }


    /***
     * send event bus data for main activity
     */

    public static void sendEventBusData(int addTocartItem) {
        /***
         * send event to update budge on main activity
         */
        CartCountEventBus cartCountEventBus = new CartCountEventBus();
        cartCountEventBus.setmCount(addTocartItem);

        EventBus.getDefault().post(cartCountEventBus);
    }

    /***
     * Create Realm object save and update data in databse
     */
    public static void saveRealmDataBase(Realm realm, GuideDataTable dataTable) {
        realm.beginTransaction();

        realm.copyToRealm(dataTable);
        realm.commitTransaction();
    }

}

