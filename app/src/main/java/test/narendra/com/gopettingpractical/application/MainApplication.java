package test.narendra.com.gopettingpractical.application;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;
import test.narendra.com.gopettingpractical.BuildConfig;

/**
 * <h1>MainApplication, @{@link Application}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        /***
         * initialize stetho for debug logging
         */

        if (BuildConfig.DEBUG) {
            Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

            initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

            initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(getApplicationContext()));

            Stetho.Initializer initializer = initializerBuilder.build();

            Stetho.initialize(initializer);
        }

        /**
         * initialize realm db for first time
         */
        Realm.init(this);

    }

}
