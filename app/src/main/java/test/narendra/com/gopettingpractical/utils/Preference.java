package test.narendra.com.gopettingpractical.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * <h1>Preference, {@link SharedPreferences} provide data save in SetPreferences</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */


public class Preference {

    private static final String FILE_NAME = "GoPettingPractical.pref";

    private static Preference mInstance = null;

    public static Preference getInstance() {
        if (null == mInstance) {
            mInstance = new Preference();
        }
        return mInstance;
    }

    public void put(Context context, String key, String value) {


        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, value);
        editor.commit();
    }

    public void put(Context context, String key, boolean value) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(Context context, String key, int value) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(key, value);
        editor.commit();
    }

    public void put(Context context, String key, long value) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putLong(key, value);
        editor.commit();
    }

    public String getValue(Context context, String key, String defaultValue) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getString(key, defaultValue);
        } catch (Exception e) {

            return defaultValue;
        }
    }


    public int getValue(Context context, String key, int defaultValue) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getInt(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getValue(Context context, String key, boolean defaultValue) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getBoolean(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public long getValue(Context context, String key, long defaultValue) {

        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getLong(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
