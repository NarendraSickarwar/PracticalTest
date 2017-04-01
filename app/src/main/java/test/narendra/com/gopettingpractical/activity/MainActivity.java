package test.narendra.com.gopettingpractical.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.realm.Realm;
import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.database.GuideDataTable;
import test.narendra.com.gopettingpractical.eventbusmodal.CartCountEventBus;
import test.narendra.com.gopettingpractical.fragments.CartListFragment;
import test.narendra.com.gopettingpractical.fragments.GuideListFragment;
import test.narendra.com.gopettingpractical.utils.AppConstants;

/**
 * <h1>Main Activity, provides main part of app handle all type fragment and buduge <@{@link AppCompatActivity}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout activityMainLayout;
    TextView mBadgeCount;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inIt();
        // add first time fragment guide list
        addFragment(GuideListFragment.newInstance());
        // get realm object
        realm = Realm.getDefaultInstance();


    }

    private void inIt() {
        activityMainLayout = (RelativeLayout) findViewById(R.id.activity_main_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
    }

    public void addFragment(Fragment fragment) {

        FragmentTransaction guideFragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        guideFragmentTransaction.add(R.id.fragment_container, fragment,
                AppConstants.TAG_GUIDELIST_FRAGMENTS);
        guideFragmentTransaction.addToBackStack(AppConstants.TAG_GUIDELIST);
        guideFragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * add cart icon and budge image in toolbar
         */
        getMenuInflater().inflate(R.menu.main, menu);
        RelativeLayout badgeLayout = (RelativeLayout) menu.findItem(R.id.badge).getActionView();
        mBadgeCount = (TextView) badgeLayout.findViewById(R.id.actionbar_notifcation_textview);
        GuideDataTable modal = realm.where(GuideDataTable.class).findFirst();

        if (modal != null) {
            mBadgeCount.setText("" + modal.getTotal());
        }
        /**
         * add click listener on cart images
         */
        badgeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragmentMultiple(CartListFragment.newInstance());
                toolbar.setTitle(getString(R.string.cart_list));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(CartCountEventBus event) {
        mBadgeCount.setText("" + event.getmCount());
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void addFragmentMultiple(Fragment f) {
        // TODO Auto-generated method stub
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        removeBackstakfrag();
        ft.replace(R.id.fragment_container, f, AppConstants.TAG_CARTLIST_FRAGMENTS);
        ft.addToBackStack(AppConstants.TAG_CARTLIST);
        ft.commit();
    }

    public void removeBackstakfrag() {

        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStackImmediate();
            removeBackstakfrag();
        }

    }

    @Override
    public void onBackPressed() {
/**
 * handle back stack of  fragment
 */
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            try {
                super.onBackPressed();
            } catch (Exception e) {

                e.printStackTrace();
            }
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                toolbar.setTitle(getString(R.string.guide_list));

            }
        } else {

            finish();

        }
    }
}




